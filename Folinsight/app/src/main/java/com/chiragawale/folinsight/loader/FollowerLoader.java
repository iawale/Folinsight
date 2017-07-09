package com.chiragawale.folinsight.loader;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.util.Log;

import com.chiragawale.folinsight.Keys;
import com.chiragawale.folinsight.entity.Follower;
import com.chiragawale.folinsight.util.CommentDataUtil;
import com.chiragawale.folinsight.util.FollowerDataUtil;
import com.chiragawale.folinsight.util.LikeDataUtil;
import com.chiragawale.folinsight.util.NetworkUtil;
import com.chiragawale.folinsight.util.SelfRecentMediaUtil;


import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

/**
 * Created by chira on 7/7/2017.
 */

public class FollowerLoader extends AsyncTaskLoader<List<Follower>> {
    /*
     URL TO CONNECT TO, TO GET FOLLOWER DATA
      */
    //API ENDPOINT TO GET FOLLOWER DATA
    private final String FOLLOWER_DATA_URL = "https://api.instagram.com/v1/users/self/followed-by?access_token=" + Keys.ACCESS_TOKEN;
    //API ENDPOINT TO GET MEDIA DATA
    private final String RECENT_MEDIA_URL = "https://api.instagram.com/v1/users/self/media/recent/?access_token=" + Keys.ACCESS_TOKEN;
    ;


    private List<Follower> followerList;

    public FollowerLoader(Context context) {
        super(context);
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public List<Follower> loadInBackground() {
        if (FOLLOWER_DATA_URL == null) {
            return null;
        }
        //Returns list of followers
        followerList = FollowerDataUtil.fetchFollowerList(FOLLOWER_DATA_URL);
        //Returns List of recent-self posted media Ids
        List<String> userRecentMediaIdList = SelfRecentMediaUtil.fetechRecentMediaIdList(RECENT_MEDIA_URL);
        //Scans for likes and comments from followers and adds them to their respective follower objects respectively
        scanMediaForLikesAndComments(userRecentMediaIdList);
        //Returns Fully processed follower list


        return followerList;
    }

    //Processes like and comments from the each post and updates their respective follower object data
    private void scanMediaForLikesAndComments(List<String> userRecentMediaList) {
        String mUserWhoLikedDataUrl;//For url for getting likes data from instagram api
        String mUserWhoCommentedUrl;//For url for getting comment data from instagram api

        //Goes through every post user has posted to scan for likes and comments from followers
        for (int i = 0; i < userRecentMediaList.size(); i++) {

            //Url according to each post with media id
            mUserWhoLikedDataUrl = "https://api.instagram.com/v1/media/" + userRecentMediaList.get(i) +
                    "/likes?access_token=" + Keys.ACCESS_TOKEN;
            //get the list of users who commented
            List<Integer> usersWhoLikedList = LikeDataUtil.fetchUsersWhoLikedData(mUserWhoLikedDataUrl);

            //Builds url for each post with media id
            mUserWhoCommentedUrl = "https://api.instagram.com/v1/media/" + userRecentMediaList.get(i) +
                    "/comments?access_token=" + Keys.ACCESS_TOKEN;

            //Get the list of users who commented
            List<Integer> usersWhoCommentedList = CommentDataUtil.fetchUsersWhoCommentedData(mUserWhoCommentedUrl);


            //Processes likes and comments and adds number of likes and comments to their respective follower objects
            processLikesAndComments(usersWhoLikedList, usersWhoCommentedList);
        }
    }

    private void processLikesAndComments(List<Integer> usersWhoLikedList, List<Integer> usersWhoCommentedList) {
        //Compares the user ID of the users who liked the post to see if he/she is in follower list and adds likes to follower object
        for (int i = 0; i < usersWhoLikedList.size(); i++) {
            for (int j = 0; j < followerList.size(); j++) {
                if (usersWhoLikedList.get(i) == followerList.get(j).getFollowerID()) {
                    followerList.get(j).setLikesPosted(followerList.get(j).getLikesPosted() + 1);
                }
            }
        }

        //Compares the user ID of the users who commented on the posts to see if he/she is in follower list and adds comment number to follower object
        for (int i = 0; i < usersWhoCommentedList.size(); i++) {
            for (int j = 0; j < followerList.size(); j++) {
                if (usersWhoCommentedList.get(i) == followerList.get(j).getFollowerID()) {
                    followerList.get(j).setCommentsPosted(followerList.get(j).getCommentsPosted() + 1);
                }
            }
        }
    }
}
