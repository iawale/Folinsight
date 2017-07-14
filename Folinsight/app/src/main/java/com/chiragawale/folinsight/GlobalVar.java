package com.chiragawale.folinsight;

import android.webkit.WebView;

import com.chiragawale.folinsight.dao.UserDao;
import com.chiragawale.folinsight.dao.impl.UserDaoImpl;

/**
 * Created by chira on 7/13/2017.
 */

public class GlobalVar {
    public static final UserDao userDao = new UserDaoImpl();
    public static final int FOLLOWED_BY_FRAGMENT = 0;
    public static final int FOLLOWS_FRAGMENT = 1;
    public static final int MUTUAL_FRAGMENT = 2;

    //API ENDPOINT TO GET FOLLOWER DATA
    private static   String FOLLOWED_BY_DATA_URL = "https://api.instagram.com/v1/users/self/followed-by?access_token=";
    //API ENDPOINT TO GET MEDIA DATA
    private static   String RECENT_MEDIA_URL = "https://api.instagram.com/v1/users/self/media/recent/?access_token=";
    //API TO GET FOLLOWED DATA
    private static  String FOLLOWS_DATA_URL = "https://api.instagram.com/v1/users/self/follows?access_token=";

    public static WebView webView;

    public static String getFollowedByDataUrl() {
        return FOLLOWED_BY_DATA_URL;
    }

    public static String getRecentMediaUrl() {
        return RECENT_MEDIA_URL;
    }

    public static String getFollowsDataUrl() {
        return FOLLOWS_DATA_URL;
    }

    public static void setUpUrls(){
        FOLLOWED_BY_DATA_URL += Keys_Access.getAccessToken();
        FOLLOWS_DATA_URL += Keys_Access.getAccessToken();
        RECENT_MEDIA_URL += Keys_Access.getAccessToken();
    }
}
