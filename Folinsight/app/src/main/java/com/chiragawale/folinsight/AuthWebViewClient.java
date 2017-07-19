package com.chiragawale.folinsight;

import android.content.Intent;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.chiragawale.folinsight.keys.GlobalVar;
import com.chiragawale.folinsight.keys.Keys_Access;

/**
 * Created by chira on 7/10/2017.
 */

public class AuthWebViewClient extends WebViewClient {
    private String request_token = null;

    //Overrides what the webview does when loading a url ( To obtain access token redirected by instagram api)
    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        view.clearCache(true);
        Log.e("AuthWebView","=========================================================");
        //Extracts access token from the redirected URI
        if (url.startsWith(Keys_Access.getRedirectUri())) {
            System.out.println(url);
            String parts[] = url.split("=");
            request_token = parts[1];
            //Sets the access token to a global variable for use later
            Keys_Access.setAccessToken(request_token);
            //Set the endpoint urls according to access token
            GlobalVar.setUpUrls();
            if (request_token != null) {
                //Opens the main activity
                Intent mainActivity = new Intent(view.getContext(), MainActivity.class);
                view.getContext().startActivity(mainActivity);
            }
            return true;
        }

        return false;
    }

    @Override
    public void onPageFinished(WebView view, String url) {
        //Extracts access token from the redirected URI
        //If the user is not logged in, it redirects to login page
        if (!url.contains("token")) {
            view.loadUrl(Keys_Access.getAccessRequestUri());
        }
    }
}
