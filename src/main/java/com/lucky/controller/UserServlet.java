package com.lucky.controller;

import com.lucky.bean.Users;
import com.lucky.service.ServiceFactory;
import com.lucky.service.user.UserService;
import com.lucky.util.Md5Encrypt;
import com.lucky.util.ResultUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

@WebServlet("/login")
public class UserServlet extends BaseServlet{

    //不实例化service层对象  让工厂去实例化
    private UserService userService;

    private ResultUtil resultUtil=new ResultUtil();

    //当用户访问我们这个servlet的时候  先执行init
    @Override
    public void init() throws ServletException {
        userService= (UserService) ServiceFactory.getServiceImpl("userService");
    }


    @Override
    public Class getServletClass() {
        return UserServlet.class;
    }


    /**
     * 注册方法
     * @param request
     * @param response
     * @return
     */
    public  String register(HttpServletRequest request, HttpServletResponse response){
        System.out.println("register");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        Users users=new Users();
        users.setUserNickName(username);
        try {

            users.setUserPassword(Md5Encrypt.getEncryptedPwd(password));

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        users.setUserType(0);
        int count = userService.add(users);
        if (count>0){
            return "main";
        }else{
            return "register";
        }
    }

    /**
     * ajax 验证用户名是否存在
     * @param request
     * @param response
     * @return
     */
    public ResultUtil validateName(HttpServletRequest request, HttpServletResponse response){
        System.out.println("sadasdas");
        String userName=request.getParameter("userName");
        String passwordInDB = userService.validateName(userName);
        System.out.println(passwordInDB+"*******");
        if(passwordInDB==null){
            resultUtil.resultSuccess();
        }else{
            resultUtil.resultFail("该昵称已被占用");
        }
        return resultUtil;
    }

    /**
     * 登录 方法
     */
    public String login(HttpServletRequest request, HttpServletResponse response){
        String loginName=request.getParameter("username");
        String password=request.getParameter("password");

        String passwordInDB=userService.validateName(loginName);

        if(passwordInDB!=null){

            try {
                if(Md5Encrypt.validPassword(password,passwordInDB)){

                    Users users = userService.login(loginName,password);
                    request.getSession().setAttribute("loginUser",users);
                    return "main";
                }else{
                    System.out.println("密码不正确!!!");
                    return "login";
                }
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

        }else{
            System.out.println("用户名不存在！！！");
            return "login";
        }

        return "login";
    }


}
