package com.chiragawale.folinsight.dao;

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
}
