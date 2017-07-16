package com.chiragawale.folinsight.dao.impl;

import com.chiragawale.folinsight.dao.MediaDao;

/**
 * Created by chira on 7/16/2017.
 */

public class MediaDaoImpl implements MediaDao {
    private int fansCount,mutualCount,followsCount,totalPosts,totalLikes,totalComments,
            fanLikes, fanComments, followsLikes, followsComments,
            strangerLikes,strangerComments,mutualLikes,mutualComments;

   public void resetValues(){
       this.fansCount = 0;
       this.mutualCount = 0;
       this.followsCount = 0;
       this.totalPosts = 0;
       this.totalLikes = 0;
       this.totalComments = 0;
       this.fanLikes = 0;
       this.fanComments = 0;
       this.followsLikes = 0;
       this.followsComments = 0;
       this.strangerLikes = 0;
       this.strangerComments = 0;
       this.mutualLikes = 0;
       this.mutualComments = 0;
   }

    @Override
    public int getMutualLikes() {
        return mutualLikes;
    }

    @Override
    public void setMutualLikes(int mutualLikes) {
        this.mutualLikes = mutualLikes;
    }

    @Override
    public int getMutualComments() {
        return mutualComments;
    }

    @Override
    public void setMutualComments(int mutualComments) {
        this.mutualComments = mutualComments;
    }

    @Override
    public int getFansCount() {
        return fansCount;
    }

    @Override
    public void setFansCount(int fansCount) {
        this.fansCount = fansCount;
    }

    @Override
    public int getMutualCount() {
        return mutualCount;
    }

    @Override
    public void setMutualCount(int mutualCount) {
        this.mutualCount = mutualCount;
    }

    @Override
    public int getFollowsCount() {
        return followsCount;
    }

    @Override
    public void setFollowsCount(int followsCount) {
        this.followsCount = followsCount;
    }

    @Override
    public int getTotalPosts() {
        return totalPosts;
    }

    @Override
    public void setTotalPosts(int totalPosts) {
        this.totalPosts = totalPosts;
    }

    @Override
    public int getTotalLikes() {
        return totalLikes;
    }

    @Override
    public void setTotalLikes(int totalLikes) {
        this.totalLikes = totalLikes;
    }

    @Override
    public int getTotalComments() {
        return totalComments;
    }

    @Override
    public void setTotalComments(int totalComments) {
        this.totalComments = totalComments;
    }

    @Override
    public int getFanLikes() {
        return fanLikes;
    }

    @Override
    public void setFanLikes(int fanLikes) {
        this.fanLikes = fanLikes;
    }

    @Override
    public int getFanComments() {
        return fanComments;
    }

    @Override
    public void setFanComments(int fanComments) {
        this.fanComments = fanComments;
    }

    @Override
    public int getStrangerLikes() {
        return strangerLikes;
    }

    @Override
    public void setStrangerLikes(int strangerLikes) {
        this.strangerLikes = strangerLikes;
    }

    @Override
    public int getStrangerComments() {
        return strangerComments;
    }

    @Override
    public void setStrangerComments(int strangerComments) {
        this.strangerComments = strangerComments;
    }

    @Override
    public int getFollowsLikes() {
        return followsLikes;
    }

    @Override
    public void setFollowsLikes(int followsLikes) {
        this.followsLikes = followsLikes;
    }

    @Override
    public int getFollowsComments() {
        return followsComments;
    }

    @Override
    public void setFollowsComments(int followsComments) {
        this.followsComments = followsComments;
    }
}
