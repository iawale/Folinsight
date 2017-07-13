package com.chiragawale.folinsight;

import com.chiragawale.folinsight.dao.UserDao;
import com.chiragawale.folinsight.dao.impl.UserDaoImpl;

/**
 * Created by chira on 7/13/2017.
 */

public class GlobalVar {
    public static final UserDao userDao = new UserDaoImpl();

}
