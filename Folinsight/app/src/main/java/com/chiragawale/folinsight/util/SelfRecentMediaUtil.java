package com.chiragawale.folinsight.util;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by chira on 7/8/2017.
 */

public class SelfRecentMediaUtil extends NetworkUtil {
    private SelfRecentMediaUtil() {
    }
    //Returns the ids of Users recet posts
    public static List<String>  fetechRecentMediaIdList (String request_url){
        URL recentMediaDataUrl= createUrl(request_url);
        String jsonResponse = "";
        try {
             jsonResponse = makeHttpRequest(recentMediaDataUrl);
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<String> recentMediaIdList = extractDataFromJsonResponse(jsonResponse);
        return recentMediaIdList;
    }

    //Extracts the ids from the json response and returns the list
    private static List<String> extractDataFromJsonResponse(String jsonResponse){
        if(jsonResponse == null){
            return null;
        }

        List<String> recentMediaIdList = new ArrayList<>();
        try {
            JSONObject root = new JSONObject(jsonResponse);
            JSONArray dataArray = root.getJSONArray("data");

            for(int i = 0; i < dataArray.length();i++){
                JSONObject currentMedia = dataArray.getJSONObject(i);
                String id = currentMedia.getString("id");
                recentMediaIdList.add(id);
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }

        return recentMediaIdList;

    }


}
