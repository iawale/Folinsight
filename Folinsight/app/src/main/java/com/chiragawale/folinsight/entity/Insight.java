package com.chiragawale.folinsight.entity;

/**
 * Created by chira on 7/18/2017.
 */

public class Insight {
    private int userId,totalPosts,totalLikes,totalComments,
            fanCount,followsCount, mutualCount,
            fanLikes, fanComments,
            followsLikes,followsComments,
            mutualLikes,mutualCommets,date;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getTotalPosts() {
        return totalPosts;
    }

    public void setTotalPosts(int totalPosts) {
        this.totalPosts = totalPosts;
    }

    public int getTotalLikes() {
        return totalLikes;
    }

    public void setTotalLikes(int totalLikes) {
        this.totalLikes = totalLikes;
    }

    public int getTotalComments() {
        return totalComments;
    }

    public void setTotalComments(int totalComments) {
        this.totalComments = totalComments;
    }

    public int getFanCount() {
        return fanCount;
    }

    public void setFanCount(int fanCount) {
        this.fanCount = fanCount;
    }

    public int getFollowsCount() {
        return followsCount;
    }

    public void setFollowsCount(int followsCount) {
        this.followsCount = followsCount;
    }

    public int getMutualCount() {
        return mutualCount;
    }

    public void setMutualCount(int mutualCount) {
        this.mutualCount = mutualCount;
    }

    public int getFanLikes() {
        return fanLikes;
    }

    public void setFanLikes(int fanLikes) {
        this.fanLikes = fanLikes;
    }

    public int getFanComments() {
        return fanComments;
    }

    public void setFanComments(int fanComments) {
        this.fanComments = fanComments;
    }

    public int getFollowsLikes() {
        return followsLikes;
    }

    public void setFollowsLikes(int followsLikes) {
        this.followsLikes = followsLikes;
    }

    public int getFollowsComments() {
        return followsComments;
    }

    public void setFollowsComments(int followsComments) {
        this.followsComments = followsComments;
    }

    public int getMutualLikes() {
        return mutualLikes;
    }

    public void setMutualLikes(int mutualLikes) {
        this.mutualLikes = mutualLikes;
    }

    public int getMutualCommets() {
        return mutualCommets;
    }

    public void setMutualCommets(int mutualCommets) {
        this.mutualCommets = mutualCommets;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }
}
