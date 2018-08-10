package com.lucky.service.user;

import com.lucky.bean.Users;
import com.lucky.dao.user.UserDao;
import com.lucky.dao.user.UserDaoImpl;
import com.lucky.service.user.UserService;
import com.lucky.util.PageUtil;

import java.io.Serializable;
import java.util.List;

public class UserServiceImpl implements UserService {

    private UserDao userDao=new UserDaoImpl();


    @Override
    public int add(Users users) {
        return userDao.add(users);
    }

    @Override
    public int deleteByCondition(Serializable s) {
        return 0;
    }

    @Override
    public int update(Users users) {
        return 0;
    }

    @Override
    public Users findByCondition(Serializable s) {
        return null;
    }

    @Override
    public List<Users> findAll() {
        return null;
    }

    @Override
    public int findRownum() {
        return 0;
    }

    @Override
    public List<Users> findAllByPage(PageUtil pageUtil, Object... params) {
        return null;
    }


    @Override
    public String validateName(String userName) {
        return userDao.validateName(userName);
    }

    @Override
    public Users login(String loginName, String password) {
        return userDao.login(loginName,password);
    }
}
