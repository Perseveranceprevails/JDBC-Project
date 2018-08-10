package com.lucky.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 一个单例类
 * 因为用户访问我们项目 无外乎就是访问对应的数据库
 * 数据库连接的四要素  我们让用户共享一个足矣
 *
 */
public class ConfigManager {

    //01.创建需要单例类的静态变量
    private static ConfigManager configManager;

    //创建Properties对象  专门解析properties文件
    private static Properties properties;

    //02.静态代码块  不用实例化对象 就能使用
    static {
        String path="jdbc.properties";

        properties = new Properties();//实例化对象

        InputStream is = ConfigManager.class.getClassLoader().getResourceAsStream(path);

        try {
            //加载 Properties文件
            properties.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                //关闭流
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //03.创建对外访问的接口
    public static synchronized ConfigManager getInstance(){
        return  configManager;
    }

    /**
     * Properties对象已经有值了 Properties文件已经进入内存
     * 我们可以通过Key获取value
     */
    public static String getValue(String key){
        return properties.getProperty(key);
    }
}
