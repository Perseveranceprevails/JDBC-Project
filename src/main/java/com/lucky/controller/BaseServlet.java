package com.lucky.controller;

import com.alibaba.fastjson.JSON;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * BaseServlet是所有子servlet的父类
 * 这个类些什么？
 *   所有的请求都会经过此类！
 *   获取请求，根据请求分发到各个子servlet！
 *   我们怎么知道用户访问那个子servlet？
 *   就算我们找到了servlet还要确定需要执行的方法？
 *   方法返回的数据有几种类型？
 *     01.json类型的数据
 *     02.普通字符串==》页面名称
 *   返回方式？
 *     01.转发
 *     02.重定向
 *     03.不反悔==》ajax
 *
 *   实现servlet的三种方式
 *     01.实现servlet接口 重写5个方法
 *     02.继承GenericServlet  重新service方法
 *     03.继承HttpServlet  重新doGet和doPost方法
 */

public abstract class BaseServlet extends HttpServlet{

    /**
     * 所有子Servlet都需要继承这个BaseServlet
     * @return
     */
    public abstract Class getServletClass();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取用户的请求  请求中必须携带一个参数===》方法名称  methodName
        String methodName = req.getParameter("methodName");

        //根据用户传递的参数  确定执行那个子Servlet中这个methodName方法
        //子servlet中的方法
        Method method=null;
        //执行方法的返回值
        Object result=null;
        if(methodName==null || "".equals(methodName)){//判断方法是否为空
            //统一返回到 主页面
            result=execute(req,resp);
        }else{//方法名不为空

            try {
                //找到方法
                method = getServletClass().getDeclaredMethod(methodName,HttpServletRequest.class,HttpServletResponse.class);
                //执行方法
                result = method.invoke(this,req,resp);

            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }catch (IllegalAccessException e) {
                e.printStackTrace();
            }

        }
        toView( req, resp, result);
    }

    /**
     *
     * @param req
     * @param resp
     * @param result
     */
    public void toView(HttpServletRequest req,HttpServletResponse resp,Object result){
        System.out.println();
        if(result==null){
            //没有返回值
        }else{//要么json 要么字符串

            if(result instanceof String){//返回值字符串
                String viewName=result.toString()+".jsp";

                try {
                    req.getRequestDispatcher(viewName).forward(req,resp);
                } catch (ServletException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }else{//返回值的json
                String resultJson= JSON.toJSONString(result);
                System.out.println(resultJson+"7777777777777");
                PrintWriter pw= null;
                try {
                    pw = resp.getWriter();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                pw.write(resultJson);
                pw.flush();
                pw.close();

            }
        }
    }

    private Object execute(HttpServletRequest req, HttpServletResponse resp) {
        return "main";
    }
}
