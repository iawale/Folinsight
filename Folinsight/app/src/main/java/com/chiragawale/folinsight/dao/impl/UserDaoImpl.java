package com.chiragawale.folinsight.dao.impl;

import com.chiragawale.folinsight.dao.UserDao;
import com.chiragawale.folinsight.entity.Users;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chira on 7/13/2017.
 */

public class UserDaoImpl implements UserDao {
    List<Users> userList = new ArrayList<>();
    @Override
    public void setUserList(List<Users> userList) {
        this.userList  = userList;
    }

    @Override
    public List<Users> getUserList() {
        return userList;
    }

    @Override
    public void clearUserList() {
        userList.clear();
    }
}
