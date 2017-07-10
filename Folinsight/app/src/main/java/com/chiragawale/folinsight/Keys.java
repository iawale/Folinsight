package com.chiragawale.folinsight;

/**
 * Created by chira on 7/9/2017.
 */

public class Keys {
    private static String ACCESS_TOKEN = "";
    private final static String REDIRECT_URI = "http://localhost/";
    private final static String CLIENT_ID = "97084514ee1c49e98560e34670d574a8";
    private static final String BASE_FOR_REQUEST_URI = "https://api.instagram.com/oauth/authorize/";


    public static String getAccessToken() {
        return ACCESS_TOKEN;
    }

    public static void setAccessToken(String accessToken)
    {
        ACCESS_TOKEN = accessToken;

    }

    public static String getAccessRequestUri() {
        return  BASE_FOR_REQUEST_URI +
                "?client_id="+CLIENT_ID+
                "&redirect_uri="+REDIRECT_URI+
                "&response_type=token"+
                "&scope=likes+comments+follower_list";
    }

    public static String getRedirectUri() {
        return REDIRECT_URI;
    }

    public static String getClientId() {
        return CLIENT_ID;
    }
}
