package com.example.server;

import com.example.SpringbootJspShiroApplication;
import com.example.domain.Role;
import com.example.domain.User;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SpringbootJspShiroApplication.class)
class UserServerTest {

    @Autowired
    private UserServer us;
    @Test
    void save() {
        User user = new User();
        user.setPassword("123");
        user.setName("cjy");
        us.save(user);
    }

    @Test
    void findbyusername(){
       User user = us.findRolebyusername("admin");
        List<Role> role = user.getRole();
        for(Role e:role){
            System.out.println(e.getName());
        }
    }

    @Test
    void findPresbyrolename(){
        Role presbyrolename = us.findPresbyrolename(1);
        System.out.println(presbyrolename);
    }
}