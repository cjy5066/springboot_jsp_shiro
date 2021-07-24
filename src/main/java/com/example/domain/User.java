package com.example.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

//用户表
@Data   //可以不写getset方法  tostring 、hashcode，等基本方法
@AllArgsConstructor      //创建声明的属性的有参构造方法
@NoArgsConstructor      //创建无参构造方法
public class User implements Serializable {
    private int id;
    private String name;
    private String password;
    private String salt;    //盐


    //一个用户可能有多个角色
    private List<Role> role;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", salt='" + salt + '\'' +
                ", role=" + role +
                '}';
    }



}
