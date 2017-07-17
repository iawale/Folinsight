package com.chiragawale.folinsight.util;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;


public  class NetworkUtil {
    private final static String LOG_TAG = NetworkUtil.class.getSimpleName();


    /**
     *
     * @param request_url Url in the form of string
     * @return  Actual URL needed to connect to the server that is generated from the passed string
     */
    public static URL createUrl(String request_url){
        URL url = null;
        try {
            url = new URL(request_url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    }

    /**
     *
     * @param requestDataUrl url to connect to
     * @return JSON Reponse from server
     */
    public static String makeHttpRequest(URL requestDataUrl) throws IOException {
        String jSONResponse = "";
        if(requestDataUrl == null){
            return null;
        }
        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;

        try {
            urlConnection =(HttpURLConnection) requestDataUrl.openConnection();
            urlConnection.setReadTimeout(15000 /*millisec*/);
            urlConnection.setConnectTimeout(15000 /*millisec*/);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            // If the request was successful (response code 200),
            // then read the input stream and parse the response.
            if (urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                jSONResponse = readFromStream(inputStream);
            } else {
                Log.e(LOG_TAG, "Error response code: " + urlConnection.getResponseCode());
            }
        }catch (IOException i){
            i.printStackTrace();
        }finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                // Closing the input stream could throw an IOException, which is why
                // the makeHttpRequest(URL url) method signature specifies than an IOException
                // could be thrown.
                inputStream.close();
            }
        }
        return jSONResponse;
    }
    //Returns the string created from data extracted from the input stream
    public static String readFromStream(InputStream inputStream) throws IOException {
        if(inputStream == null){
            return "";
        }
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        StringBuilder jsonResponse = new StringBuilder();
        String line = bufferedReader.readLine();
        while(line != null){
            jsonResponse.append(line);
            line = bufferedReader.readLine();
        }
     return jsonResponse.toString();
    }

}
