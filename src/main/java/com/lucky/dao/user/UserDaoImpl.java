package com.lucky.dao.user;

import com.lucky.bean.Users;
import com.lucky.util.BaseDao;
import com.lucky.util.PageUtil;
import com.lucky.util.ResultSetUtil;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserDaoImpl extends BaseDao implements UserDao {

    /**
     * 注册方法
     * @param users
     * @return
     */
    @Override
    public int add(Users users) {
        String sql="INSERT INTO users (userNickName,userPassword,userRealName,userType)  VALUE (?,?,?,?);";
        Object[] params={users.getUserNickName(),users.getUserPassword(),users.getUserRealName(),users.getUserType()};
        return executeUpdate(sql,params);
    }

    /**
     *ajax 验证用户名是否正确
     */
    @Override
    public String validateName(String userName) {
        String sql="SELECT userPassword FROM users WHERE userNickName=?";
        rs = executeQuery(sql, userName);
        String password=null;
        try {
            if(rs.next()){
                password=rs.getString("userPassword");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return password;
    }

    /**
     * 登录  检索登录的用户信息
     * @param loginName
     * @param password
     * @return
     */
    @Override
    public Users login(String loginName, String password) {
        String sql="SELECT userId,userNickName,userPassword,userRealName,userType FROM users WHERE userNickName=? AND userPassword=?";
        Object[] params={loginName,password};
        rs = executeQuery(sql, params);
        Users users = ResultSetUtil.eachOne(rs, Users.class);


        return users;
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


}
