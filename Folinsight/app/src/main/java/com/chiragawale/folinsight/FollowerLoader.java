package com.chiragawale.folinsight;

import android.content.AsyncTaskLoader;
import android.content.Context;

import com.chiragawale.folinsight.entity.Follower;
import com.chiragawale.folinsight.util.NetworkUtil;


import java.util.List;

/**
 * Created by chira on 7/7/2017.
 */

public class FollowerLoader extends AsyncTaskLoader<List<Follower>> {
    /*
    Url passed from the main activity to connect to
     */
    private String mUrl;

    public FollowerLoader(Context context, String url) {
        super(context);
        mUrl = url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public List<Follower> loadInBackground() {
        if(mUrl == null){
            return null;
        }
        List<Follower> followerList = NetworkUtil.fetchFollowerList(mUrl);
        return followerList;
    }
}
