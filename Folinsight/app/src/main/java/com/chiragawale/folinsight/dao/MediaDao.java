package com.chiragawale.folinsight.dao;

/**
 * Created by chira on 7/16/2017.
 */

public interface MediaDao {
    void resetValues();

    void setFansCount(int count);
    int getFansCount();
    void setMutualCount(int count);
    int getMutualCount();
    void setFollowsCount(int count);
    int getFollowsCount();

    //For Total
    void setTotalPosts(int posts);
    int getTotalPosts();
    void setTotalLikes(int likes);
    int getTotalLikes();
    void setTotalComments(int comments);
    int getTotalComments();

    //For Mutual
    void setMutualLikes(int likes);
    int getMutualLikes();
    void setMutualComments(int comments);
    int getMutualComments();

    //For Fans
    void setFanLikes(int likes);
    int getFanLikes();
    void setFanComments(int comments);
    int getFanComments();

    //For Follows
    void setFollowsLikes(int likes);
    int getFollowsLikes();
    void setFollowsComments(int comments);
    int getFollowsComments();

    //For Strangers
    void setStrangerLikes(int likes);
    int getStrangerLikes();
    void setStrangerComments(int comments);
    int getStrangerComments();

    }
