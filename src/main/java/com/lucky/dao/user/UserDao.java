package com.lucky.dao.user;

import com.lucky.bean.Users;
import com.lucky.dao.IBaseDao;

/**
 * 书写自己特有的操作
 *
 */

public interface UserDao extends IBaseDao<Users> {
    String validateName(String userName);


    Users login(String loginName, String password);
}
