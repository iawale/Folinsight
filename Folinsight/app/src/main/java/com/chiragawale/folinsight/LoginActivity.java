package com.chiragawale.folinsight;

import android.content.Intent;

import android.support.v7.app.AppCompatActivity;

import android.net.Uri;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;
import java.util.concurrent.Callable;


/**
 * A login screen that offers login via multiple providers.
 */
public class LoginActivity extends AppCompatActivity {

    private static final int RC_SIGN_IN = 9001;
    private Button mInstagramSignInButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        checkForInstagramData();
        setContentView(R.layout.activity_login);

        //Finding button and setting up on click listeners
        mInstagramSignInButton = (Button)findViewById(R.id.instagram_sign_in_button);
        mInstagramSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                signInWithInstagram();
            }
        });
    }

    //Send a intent to open a browser
    private void signInWithInstagram() {

        final Intent browser = new Intent(Intent.ACTION_VIEW, Uri.parse(Keys_Access.getAccessRequestUri()));
        startActivity(browser);
    }


    private void checkForInstagramData() {
        final Uri data = this.getIntent().getData();
        if(data != null && data.toString().startsWith("http://localhost") && data.getFragment() != null) {
            final String accessToken = data.getFragment().replaceFirst("access_token=", "");
            if (accessToken != null) {
                handleSignInResult(new Callable<Void>() {
                    @Override
                    public Void call() throws Exception {
                        return null;
                    }
                },accessToken);
            } else {
                handleSignInResult(null,accessToken);
            }
        }
    }
    //Sign in result
    private void handleSignInResult(Callable<Void> logout, String accessToken) {
        if(logout == null) {
            /* Login error */
            Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
        } else {
            /* Login success */
            Keys_Access.setAccessToken(accessToken);
            Application.getInstance().setLogoutCallable(logout);
            startActivity(new Intent(this, MainActivity.class));
        }
    }
}
