package com.chiragawale.folinsight.dao.impl;

import com.chiragawale.folinsight.dao.DataDao;
import com.chiragawale.folinsight.entity.Details_ig;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chira on 7/19/2017.
 */

public class DataDaoImpl implements DataDao {
    List<Details_ig> followsDataList = new ArrayList<>();
    List<Details_ig> strangerDataList = new ArrayList<>();
    List<Details_ig> followerDataList = new ArrayList<>();
    List<Details_ig> fanDataList = new ArrayList<>();
    List<Details_ig> mutualDataList = new ArrayList<>();
    List<Details_ig> postDataList = new ArrayList<>();

    @Override
    public List<Details_ig> getFollowsDataList() {
        return followsDataList;
    }

    @Override
    public void setFollowsDataList(List<Details_ig> followsDataList) {
        this.followsDataList = followsDataList;
    }

    @Override
    public List<Details_ig> getStrangerDataList() {
        return strangerDataList;
    }

    @Override
    public void setStrangerDataList(List<Details_ig> strangerDataList) {
        this.strangerDataList = strangerDataList;
    }

    @Override
    public List<Details_ig> getFollowerDataList() {
        return followerDataList;
    }

    @Override
    public void setFollowerDataList(List<Details_ig> followerDataList) {
        this.followerDataList = followerDataList;
    }

    @Override
    public List<Details_ig> getFanDataList() {
        return fanDataList;
    }

    @Override
    public void setFanDataList(List<Details_ig> fanDataList) {
        this.fanDataList = fanDataList;
    }

    @Override
    public List<Details_ig> getMutualDataList() {
        return mutualDataList;
    }

    @Override
    public void setMutualDataList(List<Details_ig> mutualDataList) {
        this.mutualDataList = mutualDataList;
    }

    @Override
    public List<Details_ig> getPostDataList() {
        return postDataList;
    }

    @Override
    public void clearLists() {
        followerDataList.clear();
        followsDataList.clear();
        mutualDataList.clear();
        fanDataList.clear();
        followsDataList.clear();
        postDataList.clear();
    }

    @Override
    public void setPostDataList(List<Details_ig> postDataList) {
        this.postDataList = postDataList;
    }
}
