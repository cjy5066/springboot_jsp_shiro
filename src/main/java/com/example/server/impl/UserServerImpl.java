package com.example.server.impl;

import com.example.dao.UserDao;
import com.example.domain.Role;
import com.example.domain.User;
import com.example.server.UserServer;
import com.example.utils.SaltUtils;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("userServerImpl")
@Transactional
public class UserServerImpl implements UserServer {
    @Autowired
    private UserDao userDao;

    //用户注册，将明文用md5加密+盐+hash散列
    @Override
    public void save(User user) {
        String salt = SaltUtils.getSalt(8);
        user.setSalt(salt);

        Md5Hash md5Hash = new Md5Hash(user.getPassword(),salt,1024);
        String hex = md5Hash.toHex();

        user.setPassword(hex);
        userDao.save(user);
    }

    @Override
    public User findByname(String name) {
        return userDao.findByname(name);
    }

    @Override
    public User findRolebyusername(String name) {
        return userDao.findRolebyusername(name);
    }

    @Override
    public Role findPresbyrolename(int id) {
        return userDao.findPresbyrolename(id);
    }


}
