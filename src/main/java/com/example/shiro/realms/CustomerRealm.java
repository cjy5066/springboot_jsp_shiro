package com.example.shiro.realms;

import com.example.domain.Pres;
import com.example.domain.Role;
import com.example.domain.User;
import com.example.server.UserServer;
import com.example.utils.ApplicationContextUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.util.ObjectUtils;

import java.util.List;

//自定义的Realm
public class CustomerRealm extends AuthorizingRealm  {
    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String  primaryPrincipal = (String) principalCollection.getPrimaryPrincipal();
        System.out.println("授权用户： "+primaryPrincipal);

       /* if("cjy".equals(primaryPrincipal)){
            SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();

            //添加角色权限
            simpleAuthorizationInfo.addRole("root");

            //添加权限字符串
            *//*
                user：*：*

                user:表示contlloer中的类路径  /user
                第一个*：表示执行什么方法 如：/login
                第二个*：资源类型或资源实列
             *//*
            simpleAuthorizationInfo.addStringPermission("user:*:*");
            return simpleAuthorizationInfo;
        }*/


        //使用数据库
        UserServer userServer = (UserServer) ApplicationContextUtils.getBean("userServerImpl");
        //通过用户查询与之对应的角色信息
        User userRole = userServer.findRolebyusername(primaryPrincipal);
        if(userRole!=null){         //如果不为空，说明有角色。没有就不添加角色信息
            SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
            List<Role> role = userRole.getRole();
            for(Role r:role){
                //添加角色信息
                simpleAuthorizationInfo.addRole(r.getName());

                /*
                    通过角色id查询出对应的权限字符串，并且添加
                 */
                Role presbyrolename = userServer.findPresbyrolename(r.getId());
                List<Pres> pres = presbyrolename.getPres();
                for(Pres p:pres){
                    //添加权限字符串
                    simpleAuthorizationInfo.addStringPermission(p.getName());
                }

            }

            return simpleAuthorizationInfo;
        }
        return null;
    }

    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String  principal =(String) authenticationToken.getPrincipal();

        //在自定义工厂中获取service对象  当然也可以使用Autoword注入也可以使用service层
        UserServer userServer = (UserServer) ApplicationContextUtils.getBean("userServerImpl");
        User user = userServer.findByname(principal);
        if(!ObjectUtils.isEmpty(user)){
            System.out.println("认证：+++=="+user.getName());
            SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(user.getName(), user.getPassword(),ByteSource.Util.bytes(user.getSalt()),this.getName());
            return simpleAuthenticationInfo;
        }

        /*if("cjy".equals(principal)){

            SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(principal, "123",this.getName());
            return simpleAuthenticationInfo;
        }*/
        return null;
    }
}
