package com.chiragawale.folinsight;

import android.content.Intent;

import android.support.v7.app.AppCompatActivity;

import android.net.Uri;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.Toast;

import java.util.concurrent.Callable;


/**
 * A login screen that offers login via multiple providers.
 */
public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Web View for logging in to Instagrm to obtain access token
        GlobalVar.webView = new WebView(this);
        GlobalVar.webView .setVerticalScrollBarEnabled(false);
        setContentView(GlobalVar.webView );
        GlobalVar.webView .setHorizontalScrollBarEnabled(false);
        GlobalVar.webView .setWebViewClient(new AuthWebViewClient());
        GlobalVar.webView .getSettings().setJavaScriptEnabled(true);

        GlobalVar.webView.loadUrl(Keys_Access.getAccessRequestUri());//(THIS IS FOR TESTING)

        //Logs out of instagram first
       // GlobalVar.webView.loadUrl("https://www.instagram.com/accounts/logout");


    }

}
