package com.chiragawale.folinsight.util;

import com.chiragawale.folinsight.entity.Follower;

import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

/**
 * Created by chira on 7/6/2017.
 */

public class NetworkUtil {
    /**
     *
     * @param url The url that returns the JSON data of the users follower list
     * @return  list of followers extracted from the response from the server
     */
    List<Follower> fetchFollowerList(String url){
        URL followerDataUrl = createUrl(url);
        return null;
    }

    /**
     *
     * @param url Url in the form of string
     * @return  Actual URL needed to connect to the server that is generated from the passed string
     */
    URL createUrl(String url){
        URL followerDataUrl = null;
        try {
            followerDataUrl = new URL(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return followerDataUrl;
    }

    /**
     *
     * @param followerDataUrl url to connect to
     * @return JSON Reponse from server
     */
    String makeHttpRequest(URL followerDataUrl){
        String jSONResponse = "";
        if(followerDataUrl == null){
            return null;
        }
        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;

        try {
            urlConnection =(HttpURLConnection) followerDataUrl.openConnection();
            urlConnection.setReadTimeout(15000 /*millisec*/);
            urlConnection.setConnectTimeout(15000 /*millisec*/);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            inputStream = urlConnection.getInputStream();
            jSONResponse = readData(inputStream);
        }catch (IOException i){
            i.printStackTrace();
        }
        return "";
    }
}
