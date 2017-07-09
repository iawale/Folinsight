package com.chiragawale.folinsight.util;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by chira on 7/9/2017.
 */

public class CommentDataUtil extends NetworkUtil {
    /**
     *
     * @param request_url Url to request data from
     * @return LIST OF USER IDS of users who commented on  recent self posts
     */
    public static List<Integer> fetchUsersWhoCommentedData (String request_url){
        URL usersWhoCommentedDataUrl =  createUrl(request_url);
        String jsonResponse = "";
        try {
            jsonResponse = makeHttpRequest(usersWhoCommentedDataUrl);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return extractUserListFromJsonResponse(jsonResponse);
    }
    private static List<Integer> extractUserListFromJsonResponse(String jsonResponse){
        List<Integer> usersWhoCommentedDataUrl = new ArrayList<>();

        try {
            JSONObject root = new JSONObject(jsonResponse);
            JSONArray dataArray = root.getJSONArray("data");
            for(int i = 0; i < dataArray.length();i++) {
                JSONObject currentUserData = dataArray.getJSONObject(i);
                JSONObject from = currentUserData.getJSONObject("from");
                 int id = from.getInt("id");
                usersWhoCommentedDataUrl.add(id);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }


        return usersWhoCommentedDataUrl;
    }
}




