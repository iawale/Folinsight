package com.chiragawale.folinsight.keys;

import android.webkit.WebView;

import com.chiragawale.folinsight.dao.DataDao;
import com.chiragawale.folinsight.dao.MediaDao;
import com.chiragawale.folinsight.dao.UserDao;
import com.chiragawale.folinsight.dao.impl.DataDaoImpl;
import com.chiragawale.folinsight.dao.impl.MediaDaoImpl;
import com.chiragawale.folinsight.dao.impl.UserDaoImpl;
import com.chiragawale.folinsight.keys.Keys_Access;

/**
 * Created by chira on 7/13/2017.
 */

public class GlobalVar {
    public static boolean stateSave = false;

    public final static int POSTS_CODE = 100;
    public final static int FOLLOWER_CODE = 101;
    public final static int MUTUAL_CODE = 102;
    public final static int FAN_CODE = 103;
    public final static int FOLLOWS_CODE = 104;
    public final static int STRANGER_CODE = 105;

    //For checking error response code 429
    public static boolean error429;
    //User id of the owner of access token
    public static int USER_ID = 0;

    //Controls data storing
    public static final UserDao userDao = new UserDaoImpl();
    public static final MediaDao mediaDao = new MediaDaoImpl();
    public static final DataDao dataDao = new DataDaoImpl();
    //For fragment adapter
    public static final int FOLLOWED_BY_FRAGMENT = 0;
    public static final int FOLLOWS_FRAGMENT = 1;
    public static final int MUTUAL_FRAGMENT = 2;

    //BASE API ENDPOINT TO GET FOLLOWER DATA
    private static  final String BASE_FOLLOWED_BY_DATA_URL = "https://api.instagram.com/v1/users/self/followed-by?access_token=";
    //BASE API ENDPOINT TO GET MEDIA DATA
    private static   String BASE_RECENT_MEDIA_URL = "https://api.instagram.com/v1/users/self/media/recent/?access_token=";
    //BASE API TO GET FOLLOWED DATA
    private static final String BASE_FOLLOWS_DATA_URL = "https://api.instagram.com/v1/users/self/follows?access_token=";

    //API ENDPOINT TO GET FOLLOWER DATA
    private static   String FOLLOWED_BY_DATA_URL;
    //API ENDPOINT TO GET MEDIA DATA
    private static   String RECENT_MEDIA_URL;
    //API TO GET FOLLOWED DATA
    private static  String FOLLOWS_DATA_URL;
    //For controlling the web view behavior
    public static WebView webView;
    //returns followed-by url
    public static String getFollowedByDataUrl() {
        return FOLLOWED_BY_DATA_URL;
    }
    //returns recent media url
    public static String getRecentMediaUrl() {
        return RECENT_MEDIA_URL;
    }
    //returns follows data url
    public static String getFollowsDataUrl() {
        return FOLLOWS_DATA_URL;
    }
    //Sets up the URLs
    public static void setUpUrls(){
        FOLLOWED_BY_DATA_URL =BASE_FOLLOWED_BY_DATA_URL+ Keys_Access.getAccessToken();
        FOLLOWS_DATA_URL =BASE_FOLLOWS_DATA_URL+ Keys_Access.getAccessToken();
        RECENT_MEDIA_URL =BASE_RECENT_MEDIA_URL+ Keys_Access.getAccessToken();
    }
}
