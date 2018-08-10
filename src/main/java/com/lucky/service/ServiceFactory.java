package com.lucky.service;


import com.lucky.service.user.UserServiceImpl;

/**
 * 什么时候需要工厂
 * 01.需要实例化很多对象
 * 02.这些对象有着共同的父类或接口
 *
 */
public class ServiceFactory {

    private static ServiceFactory factory;

    private ServiceFactory(){}

    static {
        if(factory==null){
            synchronized(ServiceFactory.class){
                if(factory==null){
                    factory=new ServiceFactory();
                }
            }
        }
    }

    public static IBaseService getServiceImpl(String ServiceName){
        IBaseService service=null;
        switch (ServiceName){
            case "userService":
                service=new UserServiceImpl();
                break;
            default:
                break;
        }
        return service;
    }
}
