package com.example.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

//角色表
@Data   //可以不写getset方法  tostring 、hashcode，等基本方法
@AllArgsConstructor      //创建声明的属性的有参构造方法
@NoArgsConstructor      //创建无参构造方法
public class Role implements Serializable {

    private int id;
    private String name;        //角色名

    //一个角色也有多个权限
    private List<Pres> pres;
}
