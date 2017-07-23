package com.chiragawale.folinsight;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.Toast;

import com.chiragawale.folinsight.keys.GlobalVar;
import com.chiragawale.folinsight.keys.Keys_Access;


/**
 * A login screen that offers login via multiple providers.
 */
public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        GlobalVar.webView = new WebView(LoginActivity.this);
        GlobalVar.webView .setVerticalScrollBarEnabled(false);
        GlobalVar.webView .setHorizontalScrollBarEnabled(false);
        GlobalVar.webView .setWebViewClient(new AuthWebViewClient());


        Button sign_in_btn = (Button) findViewById(R.id.sign_in_with_instagram);
        sign_in_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Get a reference to the ConnectivityManager to check state of network connectivity
                ConnectivityManager connMgr = (ConnectivityManager)
                        getSystemService(Context.CONNECTIVITY_SERVICE);

                // Get details on the currently active default data network
                NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

                // If there is a network connection, fetch data
                if (networkInfo != null && networkInfo.isConnected()) {
                    //If there is connection:
                    //Web View for logging in to Instagram to obtain access token

                    setContentView(GlobalVar.webView );
                    GlobalVar.webView.loadUrl(Keys_Access.getAccessRequestUri());

                } else {
                    // Otherwise, display error
                    Toast.makeText(LoginActivity.this, "No network connection.", Toast.LENGTH_SHORT).show();
                }
            }
        });



    }

}
