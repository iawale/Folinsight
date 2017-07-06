package com.chiragawale.folinsight.entity;

/**
 * Entity Class for Follower Details
 */
public class Follower {
    int followerID;
    String userName,fullName,profilePictureLink;
    String date_trackedFrom;
    boolean follow;

    public Follower(int followerID, String userName, String fullName, String profilePictureLink, String date_trackedFrom, boolean follow) {
        this.followerID = followerID;
        this.userName = userName;
        this.fullName = fullName;
        this.profilePictureLink = profilePictureLink;
        this.date_trackedFrom = date_trackedFrom;
        this.follow = follow;
    }

    public Follower() {
    }

    public int getFollowerID() {
        return followerID;
    }

    public void setFollowerID(int followerID) {
        this.followerID = followerID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getProfilePictureLink() {
        return profilePictureLink;
    }

    public void setProfilePictureLink(String profilePictureLink) {
        this.profilePictureLink = profilePictureLink;
    }

    public String getDate_trackedFrom() {
        return date_trackedFrom;
    }

    public void setDate_trackedFrom(String date_trackedFrom) {
        this.date_trackedFrom = date_trackedFrom;
    }

    public boolean isFollow() {
        return follow;
    }

    public void setFollow(boolean follow) {
        this.follow = follow;
    }
}
