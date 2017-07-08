package com.chiragawale.folinsight.loader;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.content.Intent;

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
    Url passed from the main activity to connect to
     */
    private String mFollowerDataUrl;
    private String mUserRecentMediaIdUrl;
    private String mUserWhoLikedDataUrl;

    public FollowerLoader(Context context, String url) {
        super(context);
        mFollowerDataUrl = url;
        //API ENDPOINT TO GET MEDIA DATA
        mUserRecentMediaIdUrl = "";
        //API END POINT TO GET LIKE DATA
        mUserWhoLikedDataUrl ="";
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public List<Follower> loadInBackground() {
        if(mFollowerDataUrl == null){
            return null;
        }
        List<Follower> followerList = NetworkUtil.fetchFollowerList(mFollowerDataUrl);
        List<String> userRecentMediaIdList = SelfRecentMediaUtil.fetechRecentMediaIdList(mUserRecentMediaIdUrl);
        List<Integer> usersWhoLikedList = LikeDataUtil.fetchUsersWhoLikedData(mUserWhoLikedDataUrl);


        return followerList;
    }
}
