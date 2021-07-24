package com.example.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/order")
public class OrderController {

    //代码判断是否有权限
    @RequestMapping("/add")
    @RequiresRoles(value={"admin","root"})    //注解判断权限,必须同时具有value里面所有的值；没有权限不会进入方法
    @RequiresPermissions("user:add:*")      //用资源权限进行
    public String add(){
        Subject subject = SecurityUtils.getSubject();

        boolean root = subject.hasRole("root");
        if(root){
            System.out.println("有权限执行业务！");
            return "index";
        }else {
            System.out.println("没有权限");
            return "403";
        }

    }
}
