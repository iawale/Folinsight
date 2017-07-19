package com.chiragawale.folinsight.loader;


import android.content.AsyncTaskLoader;
import android.content.Context;

import com.chiragawale.folinsight.keys.GlobalVar;
import com.chiragawale.folinsight.keys.Keys_Access;
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
        //Returns List of recent-self posted media Ids
        List<String> userRecentMediaIdList = RecentMediaUtil.fetechRecentMediaIdList(GlobalVar.getRecentMediaUrl());


        //Returns list of users followed by the owner of access token
        userList = UserDataUtil.fetchUserList(GlobalVar.getFollowedByDataUrl());


        //Returns list of followers
        List<Users> followsUserList = UserDataUtil.fetchUserList(GlobalVar.getFollowsDataUrl());

        //For checking duplicate values
       boolean duplicate = false;

        for(Users followsUser : followsUserList){
            for(Users followedUser: userList){
                if(followsUser.getFollowerID() == followedUser.getFollowerID()){
                    followedUser.setFollows(true);
                    duplicate = true;
                }
            }
            if(!duplicate){
                userList.add(followsUser);
            }

        }

        //Scans for likes and comments from followers and users followed by owner of access token and adds them to their respective follower objects respectively
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
