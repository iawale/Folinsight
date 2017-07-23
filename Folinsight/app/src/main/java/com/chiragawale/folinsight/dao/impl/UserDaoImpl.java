package com.chiragawale.folinsight.dao.impl;

import com.chiragawale.folinsight.keys.GlobalVar;
import com.chiragawale.folinsight.dao.UserDao;
import com.chiragawale.folinsight.entity.Details_ig;
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
    private List<Details_ig> dataList = new ArrayList<>();
    private List<Users> searchResultList = new ArrayList<>();




    //Sets up the lists with data loaded from Instagram Api
    @Override
    public void setUpUserLists(List<Users> userList) {
        this.userList = userList;
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

    @Override
    public List<Details_ig> getDataList() {
        dataList.clear();
        fillUpDataList();
        return dataList;
    }

    @Override
    public List<Users> getSearchResultList(String query) {
        //Processes the list
        setUpSearchResultList(query);
        return searchResultList;
    }

    //Calculates averages and stores them for displaying
    public void fillUpDataList() {
        double totalPosts = GlobalVar.mediaDao.getTotalPosts();
        Details_ig data = new Details_ig(
                GlobalVar.POSTS_CODE,
                GlobalVar.mediaDao.getTotalLikes() / totalPosts,
                GlobalVar.mediaDao.getTotalComments() / totalPosts);
        dataList.add(data);

        data = new Details_ig(
                GlobalVar.FOLLOWER_CODE,
                (GlobalVar.mediaDao.getFanLikes() + GlobalVar.mediaDao.getMutualLikes()) / totalPosts,
                (GlobalVar.mediaDao.getFanComments() + GlobalVar.mediaDao.getMutualComments()) / totalPosts);
        dataList.add(data);

        data = new Details_ig(
                GlobalVar.MUTUAL_CODE,
                GlobalVar.mediaDao.getMutualLikes() / totalPosts,
                GlobalVar.mediaDao.getMutualComments() / totalPosts);
        dataList.add(data);

        data = new Details_ig(
                GlobalVar.FAN_CODE,
                GlobalVar.mediaDao.getFanLikes() / totalPosts,
                GlobalVar.mediaDao.getFanComments() / totalPosts);
        dataList.add(data);

        data = new Details_ig(
                GlobalVar.FOLLOWS_CODE,
                GlobalVar.mediaDao.getFollowsLikes() / totalPosts,
                GlobalVar.mediaDao.getFollowsComments() / totalPosts);
        dataList.add(data);

        data = new Details_ig(
                GlobalVar.STRANGER_CODE,
                (GlobalVar.mediaDao.getTotalLikes() - (
                        GlobalVar.mediaDao.getFanLikes()
                                + GlobalVar.mediaDao.getFollowsLikes()
                                + GlobalVar.mediaDao.getMutualLikes())) / totalPosts,
                (GlobalVar.mediaDao.getTotalComments() - (
                        GlobalVar.mediaDao.getFollowsComments()
                                + GlobalVar.mediaDao.getMutualComments()
                                + GlobalVar.mediaDao.getFanComments())) / totalPosts);
        dataList.add(data);

    }

    //Clears lists
    @Override
    public void clearUserList() {
        userList.clear();
        followsList.clear();
        followedByList.clear();
        mutualList.clear();
    }

    //Sets up the lists with their respective set of uses
    void setUpSpecificLists() {
        for (Users u : userList) {
            if (u.isFollows() && u.isFollowedBy()) {
                mutualList.add(u);
                //Counting likes and comments
                GlobalVar.mediaDao.setMutualLikes(GlobalVar.mediaDao.getMutualLikes() + u.getLikesPosted());
                GlobalVar.mediaDao.setMutualComments(GlobalVar.mediaDao.getMutualComments() + u.getCommentsPosted());
            } else if (u.isFollowedBy()) {
                followedByList.add(u);
                //Counting likes and comments
                GlobalVar.mediaDao.setFanLikes(GlobalVar.mediaDao.getFanLikes() + u.getLikesPosted());
                GlobalVar.mediaDao.setFanComments(GlobalVar.mediaDao.getFanComments() + u.getCommentsPosted());

            } else if (u.isFollows()) {
                followsList.add(u);
                //Counting likes and comments
                GlobalVar.mediaDao.setFollowsLikes(GlobalVar.mediaDao.getFollowsLikes() + u.getLikesPosted());
                GlobalVar.mediaDao.setFollowsComments(GlobalVar.mediaDao.getFollowsComments() + u.getCommentsPosted());
            }
        }
        //Stranger likes and comments = total - fans - follows -mutual
        GlobalVar.mediaDao.setStrangerLikes(GlobalVar.mediaDao.getTotalLikes()
                - GlobalVar.mediaDao.getFanLikes()
                - GlobalVar.mediaDao.getFollowsLikes()
                - GlobalVar.mediaDao.getMutualLikes());
        GlobalVar.mediaDao.setStrangerComments(GlobalVar.mediaDao.getTotalComments()
                - GlobalVar.mediaDao.getFanComments()
                - GlobalVar.mediaDao.getFollowsComments()
                - GlobalVar.mediaDao.getMutualComments());

    }

    void setUpSearchResultList(String query){
        searchResultList.clear();
        for(Users user: userList){
            if(user.getUserName().contains(query)){
                searchResultList.add(user);
            }
        }
    }

}
