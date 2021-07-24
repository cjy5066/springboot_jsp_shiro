package com.example.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

//权限表
@Data   //可以不写getset方法  tostring 、hashcode，等基本方法
@AllArgsConstructor      //创建声明的属性的有参构造方法
@NoArgsConstructor      //创建无参构造方法
public class Pres implements Serializable {
    private int id;
    private String name;    //权限标识符
    private String url;     //权限标识url


}
