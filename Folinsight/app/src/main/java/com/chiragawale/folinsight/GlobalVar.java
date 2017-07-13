package com.chiragawale.folinsight;

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
    public static  final String FOLLOWED_BY_DATA_URL = "https://api.instagram.com/v1/users/self/followed-by?access_token=" + Keys_Access.getAccessToken();
    //API ENDPOINT TO GET MEDIA DATA
    public static  final String RECENT_MEDIA_URL = "https://api.instagram.com/v1/users/self/media/recent/?access_token=" + Keys_Access.getAccessToken();
    //API TO GET FOLLOWED DATA
    public static final String FOLLOWS_DATA_URL = "https://api.instagram.com/v1/users/self/follows?access_token=" + Keys_Access.getAccessToken();

}
