package com.lucky.util;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ResultSetUtil<T> {

    public static <T> T eachOne(ResultSet rs,Class<T> clazz){

        T obj=null;
        try {
            if(rs.next()){
                //通过反射的方式获取实例化对象
                obj = clazz.newInstance();
                Field[] fields = clazz.getDeclaredFields();
                for (Field item : fields) {
                    //打开访问私有属性的开关
                    item.setAccessible(true);
                    item.set(obj,rs.getObject(item.getName()));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        return obj;
    }
}
