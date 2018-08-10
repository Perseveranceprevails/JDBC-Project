package com.lucky.service.user;

import com.lucky.bean.Users;
import com.lucky.service.IBaseService;

public interface UserService extends IBaseService<Users> {

    String validateName(String userName);

    Users login(String loginName, String password);
}
