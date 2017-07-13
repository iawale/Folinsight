package com.chiragawale.folinsight.loader;


import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;

import com.chiragawale.folinsight.Keys_Access;
import com.chiragawale.folinsight.entity.Users;
import com.chiragawale.folinsight.util.CommentDataUtil;
import com.chiragawale.folinsight.util.UserDataUtil;
import com.chiragawale.folinsight.util.LikeDataUtil;
import com.chiragawale.folinsight.util.RecentMediaUtil;


import java.util.List;

/**
 * Created by chira on 7/7/2017.
 */

public class UserLoader extends AsyncTaskLoader<List<Users>> {
    /*
     URL TO CONNECT TO, TO GET FOLLOWER DATA
      */
    //API ENDPOINT TO GET FOLLOWER DATA
    private final String FOLLOWED_BY_DATA_URL = "https://api.instagram.com/v1/users/self/followed-by?access_token=" + Keys_Access.getAccessToken();
    //API ENDPOINT TO GET MEDIA DATA
    private final String RECENT_MEDIA_URL = "https://api.instagram.com/v1/users/self/media/recent/?access_token=" + Keys_Access.getAccessToken();
    //API TO GET FOLLOWED DATA
    private final String FOLLOWS_DATA_URL = "https://api.instagram.com/v1/users/self/follows?access_token=" + Keys_Access.getAccessToken();

    private List<Users> userList;

    public UserLoader(Context context) {
        super(context);

    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public List<Users> loadInBackground() {
        Log.e("Loader called ", "=======================================================");

        //Returns List of recent-self posted media Ids
        List<String> userRecentMediaIdList = RecentMediaUtil.fetechRecentMediaIdList(RECENT_MEDIA_URL);
        //Scans for likes and comments from followers and adds them to their respective follower objects respectively

        //Returns list of users followed by the owner of access token
        List<Users> followedByUserList = UserDataUtil.fetchUserList(FOLLOWED_BY_DATA_URL);
        userList = followedByUserList;

        //Returns list of followers
        List<Users> followsUserList = UserDataUtil.fetchUserList(FOLLOWS_DATA_URL);

        //For checking duplicate values
        boolean isDuplicate = false;
        for(int i = 0; i < followsUserList.size();i++){
            isDuplicate = false;
            for(int j = 0; j < userList.size();j++) {
                if ( followsUserList.get(i).getFollowerID() == userList.get(j).getFollowerID()) {
                    isDuplicate = true;
                    break;
                }
            }
            if(!isDuplicate) {
                userList.add(followsUserList.get(i));
            }
        }


        scanMediaForLikesAndComments(userRecentMediaIdList);
        //Returns Fully processed follower list


        return userList;
    }

      /*=====================================================/
    For prcessing information about users who follow the owner of access Token */
    /*=====================================================*/

    //Processes like and comments from the each post and updates their respective follower object data
    private void scanMediaForLikesAndComments(List<String> userRecentMediaList) {
        String mUserWhoLikedDataUrl;//For url for getting likes data from instagram api
        String mUserWhoCommentedUrl;//For url for getting comment data from instagram api

        //Goes through every post user has posted to scan for likes and comments from followers
        for (int i = 0; i < userRecentMediaList.size(); i++) {

            //Url according to each post with media id
            mUserWhoLikedDataUrl = "https://api.instagram.com/v1/media/" + userRecentMediaList.get(i) +
                    "/likes?access_token=" + Keys_Access.getAccessToken();
            //get the list of users who commented
            List<Integer> usersWhoLikedList = LikeDataUtil.fetchUsersWhoLikedData(mUserWhoLikedDataUrl);

            //Builds url for each post with media id
            mUserWhoCommentedUrl = "https://api.instagram.com/v1/media/" + userRecentMediaList.get(i) +
                    "/comments?access_token=" + Keys_Access.getAccessToken();

            //Get the list of users who commented
            List<Integer> usersWhoCommentedList = CommentDataUtil.fetchUsersWhoCommentedData(mUserWhoCommentedUrl);


            //Processes likes and comments and adds number of likes and comments to their respective follower objects
            processLikesAndComments(usersWhoLikedList, usersWhoCommentedList);
        }
    }

    private void processLikesAndComments(List<Integer> usersWhoLikedList, List<Integer> usersWhoCommentedList) {
        //Compares the user ID of the users who liked the post to see if he/she is in follower list and adds likes to follower object
        for (int i = 0; i < usersWhoLikedList.size(); i++) {
            for (int j = 0; j < userList.size(); j++) {
                if (usersWhoLikedList.get(i) == userList.get(j).getFollowerID()) {
                    userList.get(j).setLikesPosted(userList.get(j).getLikesPosted() + 1);
                }
            }
        }

        //Compares the user ID of the users who commented on the posts to see if he/she is in follower list and adds comment number to follower object
        for (int i = 0; i < usersWhoCommentedList.size(); i++) {
            for (int j = 0; j < userList.size(); j++) {
                if (usersWhoCommentedList.get(i) == userList.get(j).getFollowerID()) {
                    userList.get(j).setCommentsPosted(userList.get(j).getCommentsPosted() + 1);
                }
            }
        }
    }

}
