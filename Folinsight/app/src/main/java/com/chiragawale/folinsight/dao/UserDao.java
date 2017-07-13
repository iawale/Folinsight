package com.chiragawale.folinsight.dao;

import com.chiragawale.folinsight.entity.Users;

import java.util.List;

/**
 * Created by chira on 7/13/2017.
 */

public interface UserDao {
    void setUserList(List<Users> userList);
    List<Users> getUserList();
    void clearUserList();
}
