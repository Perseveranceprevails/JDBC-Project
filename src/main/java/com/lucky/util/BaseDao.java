package com.lucky.util;

import java.sql.*;

public class BaseDao {

    private Connection con;
    private PreparedStatement ps;
    public ResultSet rs;

    /**
     * 01.获取连接数据库对象
     * @return
     */
    public boolean getConnection(){
        try {
            Class.forName(ConfigManager.getValue("jdbc.driver"));
            con= DriverManager.getConnection(ConfigManager.getValue("jdbc.url"),
                    ConfigManager.getValue("jdbc.username"),
                    ConfigManager.getValue("jdbc.password"));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return  false;
        }catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return  true;
    }

    /**
     * 02.增删改方法
     */
    public int executeUpdate(String sql,Object... params){
        int count=0;
        if(getConnection()){
            try {
                ps = con.prepareStatement(sql);
                if(params!=null){
                    for (int i = 0; i <params.length ; i++) {
                        ps.setObject(i+1,params[i]);
                    }
                }
                count = ps.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }finally {
                CloseAll();
            }
        }
        return count;
    }


    /**
     * 0.查询方法
     */
    public ResultSet executeQuery(String sql,Object... params){
        if(getConnection()){
            try {
                ps = con.prepareStatement(sql);
                if(params!=null){
                    for (int i = 0; i <params.length ; i++) {
                        ps.setObject(i+1,params[i]);
                    }
                }
                rs = ps.executeQuery();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return rs;
    }

    /**
     * 04.释放资源
     *
     */
    public void CloseAll(){
        try {

            if(rs!=null){
                rs.close();
            }
            if(ps!=null){
                ps.close();
            }
            if(con!=null){
                con.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
