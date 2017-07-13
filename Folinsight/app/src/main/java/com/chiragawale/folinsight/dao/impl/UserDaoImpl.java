package com.chiragawale.folinsight.dao.impl;

import com.chiragawale.folinsight.dao.UserDao;
import com.chiragawale.folinsight.entity.Users;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chira on 7/13/2017.
 */

public class UserDaoImpl implements UserDao {

    private List<Users> userList = new ArrayList<>();
    private List<Users> followsList = new ArrayList<>();
    private List<Users> followedByList = new ArrayList<>();
    private List<Users> mutualList = new ArrayList<>();

    //Sets up the lists with data loaded from Instagram Api
    @Override
    public void setUpUserLists(List<Users> userList) {
        this.userList  = userList;
        setUpSpecificLists();
    }

    //Returns the whole user List (includes both follows and followed by [users])
    @Override
    public List<Users> getUserList() {
        return userList;
    }

    //Returns list of users followed by the user
    @Override
    public List<Users> getFollowsList() {
        return followsList;
    }

    //Returns list of users who follow the user
    @Override
    public List<Users> getFollowedByList() {
        return followedByList;
    }

    //Return mutual follow list
    @Override
    public List<Users> getMutualList() {
        return mutualList;
    }

    //Clears lists
    @Override
    public void clearUserList() {
        userList.clear();
        followsList.clear();
        followedByList.clear();
    }

    //Sets up the lists with their respective set of uses
    void setUpSpecificLists(){
        for(Users u : userList){
            if(u.isFollows() && u.isFollowedBy()){
                followedByList.add(u);
                followsList.add(u);
                mutualList.add(u);
            }else if(u.isFollowedBy()){
                followedByList.add(u);
            }else if(u.isFollows()){
                followsList.add(u);
            }
        }
    }
}
