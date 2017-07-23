package com.chiragawale.folinsight.dao;

import com.chiragawale.folinsight.entity.Details_ig;
import com.chiragawale.folinsight.entity.Users;

import java.util.List;

/**
 * Created by chira on 7/13/2017.
 */

public interface UserDao {
    void setUpUserLists(List<Users> userList);
    void clearUserList();
    List<Users> getUserList();
    List<Users> getFollowsList();
    List<Users> getFollowedByList();
    List<Users> getMutualList();
    List<Details_ig> getDataList();
    List<Users> getSearchResultList(String query);

}
