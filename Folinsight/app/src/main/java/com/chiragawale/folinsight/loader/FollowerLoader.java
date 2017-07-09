package com.chiragawale.folinsight.loader;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.chiragawale.folinsight.Keys;
import com.chiragawale.folinsight.entity.Follower;
import com.chiragawale.folinsight.util.LikeDataUtil;
import com.chiragawale.folinsight.util.NetworkUtil;
import com.chiragawale.folinsight.util.SelfRecentMediaUtil;


import java.util.List;

/**
 * Created by chira on 7/7/2017.
 */

public class FollowerLoader extends AsyncTaskLoader<List<Follower>> {
    /*
     URL TO CONNECT TO, TO GET FOLLOWER DATA
      */
    //API ENDPOINT TO GET FOLLOWER DATA
    private final String FOLLOWER_DATA_URL = "https://api.instagram.com/v1/users/self/followed-by?access_token="+Keys.ACCESS_TOKEN;
    //API ENDPOINT TO GET MEDIA DATA
    private final String RECENT_MEDIA_URL = "https://api.instagram.com/v1/users/self/media/recent/?access_token="+ Keys.ACCESS_TOKEN;;


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
        followerList = NetworkUtil.fetchFollowerList(FOLLOWER_DATA_URL);

        List<String> userRecentMediaIdList = SelfRecentMediaUtil.fetechRecentMediaIdList(RECENT_MEDIA_URL);

        scanMediaForLikes(userRecentMediaIdList);

        return followerList;
    }

    private void scanMediaForLikes(List<String> userRecentMediaList){
        String mUserWhoLikedDataUrl;
        for(int i = 0; i < userRecentMediaList.size();i++) {
            mUserWhoLikedDataUrl = "https://api.instagram.com/v1/media/"+userRecentMediaList.get(i)+
            "/likes?access_token="+Keys.ACCESS_TOKEN;
            List<Integer> usersWhoLikedList = LikeDataUtil.fetchUsersWhoLikedData(mUserWhoLikedDataUrl);
            processLikes(usersWhoLikedList);
        }
    }

    private void processLikes(List<Integer> usersWhoLikedList) {
        //Compares the user ID of the users who liked the post to see if he/she is in follower list and adds follower list data
        for (int i = 0; i < usersWhoLikedList.size(); i++) {
            for (int j = 0; j < followerList.size(); j++) {
                if (usersWhoLikedList.get(i)==followerList.get(j).getFollowerID()) {
                    followerList.get(j).setLikesPosted(followerList.get(j).getLikesPosted()+1);
                }
            }
        }
    }
}