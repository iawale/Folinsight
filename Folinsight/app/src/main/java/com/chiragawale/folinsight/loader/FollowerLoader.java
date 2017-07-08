package com.chiragawale.folinsight.loader;

import android.content.AsyncTaskLoader;
import android.content.Context;

import com.chiragawale.folinsight.entity.Follower;
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

    public FollowerLoader(Context context, String url) {
        super(context);
        mFollowerDataUrl = url;
        mUserRecentMediaIdUrl = "";//API ENDPOINT TO GET MEDIA DATA
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





        return followerList;
    }
}
