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
        WebView webView = new WebView(this);
        webView.setVerticalScrollBarEnabled(false);
        setContentView(webView);
        webView.setHorizontalScrollBarEnabled(false);
        webView.setWebViewClient(new AuthWebViewClient());
        webView.getSettings().setJavaScriptEnabled(true);
        //Logs out of instagram first
        //webView.loadUrl(Keys_Access.getAccessRequestUri());//(THIS IS FOR TESTING)
        webView.loadUrl("https://www.instagram.com/accounts/logout");


    }

}
