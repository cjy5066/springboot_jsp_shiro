package com.example.controller;

import com.example.domain.User;
import com.example.server.UserServer;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
class UserLoginController {

    @Autowired
    private UserServer userServer;
    /*
        用户登录
     */
    @RequestMapping("/login")
    public String login(String name,String password ,boolean me){
        System.out.println("记住我："+me);
        /*
        在之前第一步是创建安全管理器，这里是直接使用utils。
            因为springboot继承shiro，所以在配置文件中已经先将安全管理器给了
         */
        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken token = new UsernamePasswordToken(name, password);
        //设置记住我
       token.setRememberMe(me);
        try{
        subject.login(token);
        return "index";
        }catch(UnknownAccountException e){
            System.out.println("用户名出错~！");
            return "login";
        }catch(IncorrectCredentialsException e){
            System.out.println("密码错误~！");
            return "login";
        }

    }

    //退出登录
    @RequestMapping("/out")
    public String out(){
        Subject subject = SecurityUtils.getSubject();
        subject.logout();   //退出登录
        return "login";
    }

    //注册
    @RequestMapping("/register")
    public String register(User user){

        try{
            userServer.save(user);
            return "login";
        }catch (Exception e){
            return "register";
        }
    }
}
