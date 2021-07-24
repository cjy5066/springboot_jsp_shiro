package com.example.server;

import com.example.domain.Role;
import com.example.domain.User;

import java.util.List;


public interface UserServer {

    public void save(User user);

    public User findByname(String name);

    //根据用户名查询所有的角色
    public User findRolebyusername (String name);

    //根据角色查询权限
    public Role findPresbyrolename(int id);


}
