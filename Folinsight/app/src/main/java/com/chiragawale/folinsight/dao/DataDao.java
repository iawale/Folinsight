package com.chiragawale.folinsight.dao;

import com.chiragawale.folinsight.entity.Details_ig;

import java.util.List;

/**
 * Created by chira on 7/19/2017.
 */

public interface DataDao {
//    void setDataList(List<Details_ig> dataList);
//    List<Details_ig> getDataList();
    void clearLists();
    void setPostDataList(List<Details_ig> postDataList);
    void setFollowerDataList(List<Details_ig> followerDataList);
    void setFollowsDataList(List<Details_ig> followsDataList);
    void setMutualDataList(List<Details_ig> mutualDataList);
    void setFanDataList(List<Details_ig> fanDataList);
    void setStrangerDataList(List<Details_ig> strangerDataList);

    List<Details_ig> getPostDataList();
    List<Details_ig> getFollowerDataList();
    List<Details_ig>  getMutualDataList();
    List<Details_ig> getFanDataList();
    List<Details_ig> getFollowsDataList();
    List<Details_ig> getStrangerDataList();
}
