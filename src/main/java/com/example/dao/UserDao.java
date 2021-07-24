package com.example.dao;

import com.example.domain.Role;
import com.example.domain.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserDao {

    public void save(User user);

    public User findByname(String name);

    //根据用户名查询所有的角色
    public User findRolebyusername (String name);

    //根据角色查询权限
    public Role findPresbyrolename(int id);
}
