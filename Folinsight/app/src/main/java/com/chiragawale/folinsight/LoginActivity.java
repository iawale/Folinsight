package com.chiragawale.folinsight;

import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;

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



        //Web View for logging in to Instagram to obtain access token
        GlobalVar.webView = new WebView(this);
        GlobalVar.webView .setVerticalScrollBarEnabled(false);
        setContentView(GlobalVar.webView );
        GlobalVar.webView .setHorizontalScrollBarEnabled(false);
        GlobalVar.webView .setWebViewClient(new AuthWebViewClient());
        GlobalVar.webView .getSettings().setJavaScriptEnabled(true);
        GlobalVar.webView.loadUrl(Keys_Access.getAccessRequestUri());


    }

}
