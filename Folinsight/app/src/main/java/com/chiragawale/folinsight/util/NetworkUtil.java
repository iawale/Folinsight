package com.chiragawale.folinsight.util;

import android.util.Log;

import com.chiragawale.folinsight.entity.Follower;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;


public  class NetworkUtil {
    private final static String LOG_TAG = NetworkUtil.class.getSimpleName();
    /**
     *
     * @param url The url that returns the JSON data of the users follower list
     * @return  list of followers extracted from the response from the server
     */
    public static List<Follower> fetchFollowerList(String url){
        URL followerDataUrl = createUrl(url);
        String jsonResponse = "";
        try {
             jsonResponse = makeHttpRequest(followerDataUrl);
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<Follower> followerList = getFollowerList(jsonResponse);

        return followerList;
    }

    /**
     *
     * @param url Url in the form of string
     * @return  Actual URL needed to connect to the server that is generated from the passed string
     */
    public static URL createUrl(String url){
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
    public static String makeHttpRequest(URL followerDataUrl) throws IOException {
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
    //Returns the list with follower objects created from data exraceted from JSON response
    private static List<Follower> getFollowerList(String jsonResponse){
        List<Follower> followerList = new ArrayList<>();

        try {
            JSONObject root = new JSONObject(jsonResponse);
            JSONArray data = root.getJSONArray("data");
            for(int i = 0; i < data.length();i++) {
                JSONObject currentFollower = data.getJSONObject(i);
                String username = currentFollower.getString("username");
                String full_name = currentFollower.getString("full_name");
                String profilePicture_link= currentFollower.getString("profile_picture");
                int id = currentFollower.getInt("id");
                Follower currentFollowerObject = new Follower(id,username,full_name,profilePicture_link,"",true);
                followerList.add(currentFollowerObject);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return followerList;
    }
}
