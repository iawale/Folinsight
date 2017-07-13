package com.chiragawale.folinsight.entity;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;

/**
 * Entity Class for Users Details
 */
public class Users {
    private int followerID;
    private String userName, fullName, profilePictureLink;
    private String date_trackedFrom, profileLink;
    private int likesPosted, commentsPosted;
    private boolean followedBy;
    private boolean follows;


    public Users(int followerID, String userName, String fullName, String profilePictureLink, String date_trackedFrom, boolean followedBy, boolean follows) {
        this.followerID = followerID;
        this.userName = userName;
        this.fullName = fullName;
        this.profilePictureLink = profilePictureLink;
        this.date_trackedFrom = date_trackedFrom;
        this.followedBy = followedBy;
        this.profileLink = "https://www.instagram.com/" + userName;
        this.follows = follows;
    }


    public Users() {
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

    public boolean isFollowedBy() {
        return followedBy;
    }

    public void setFollowedBy(boolean followedBy) {
        this.followedBy = followedBy;
    }

    public String getProfileLink() {
        return profileLink;
    }

    public int getLikesPosted() {
        return likesPosted;
    }

    public void setLikesPosted(int likesPosted) {
        this.likesPosted = likesPosted;
    }

    public int getCommentsPosted() {
        return commentsPosted;
    }

    public void setCommentsPosted(int commentsPosted) {
        this.commentsPosted = commentsPosted;
    }

    public void setProfileLink(String profileLink) {
        this.profileLink = profileLink;
    }

    public boolean isFollows() {
        return follows;
    }

    public void setFollows(boolean follows) {
        this.follows = follows;
    }
}
