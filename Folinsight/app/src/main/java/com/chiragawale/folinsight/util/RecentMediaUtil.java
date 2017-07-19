package com.chiragawale.folinsight.util;

import com.chiragawale.folinsight.keys.GlobalVar;

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

public class RecentMediaUtil extends NetworkUtil {
    private RecentMediaUtil() {
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
        int totalLikes = 0,totalComments = 0;
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
                JSONObject likes = currentMedia.getJSONObject("likes");
                int count_likes = likes.getInt("count");
                totalLikes+=count_likes;
                JSONObject comments = currentMedia.getJSONObject("comments");
                int count_comments = comments.getInt("count");
                totalComments += count_comments;
                recentMediaIdList.add(id);
                //Gets the user id of the owner of access token
                if(GlobalVar.USER_ID == 0){
                    JSONObject user = currentMedia.getJSONObject("user");
                    GlobalVar.USER_ID = user.getInt("id");
                }
            }

            //Storing total likes, comments and posts
            GlobalVar.mediaDao.setTotalLikes(totalLikes);
            GlobalVar.mediaDao.setTotalComments(totalComments);
            GlobalVar.mediaDao.setTotalPosts(recentMediaIdList.size());


        } catch (JSONException e) {
            e.printStackTrace();
        }

        return recentMediaIdList;

    }


}
