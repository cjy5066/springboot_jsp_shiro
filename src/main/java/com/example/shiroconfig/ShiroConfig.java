package com.example.shiroconfig;

//import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import com.example.shiro.cache.RedisCacheManager;
import com.example.shiro.realms.CustomerRealm;
import com.example.shiro.session.ShiroSessionDao;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;

//用来整合shiro框架相关的配置类
@Configuration
public class ShiroConfig {

    //1，创建shiroFilter  负责拦截所有请求
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(DefaultWebSecurityManager defaultWebSecurityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();

        //给filter设置安全管理器
        shiroFilterFactoryBean.setSecurityManager(defaultWebSecurityManager);

        //配置系统受限资源
        HashMap<String, String> map = new HashMap<>();
        map.put("/user/login","anon");   //anon公共资源  要放在上面  authc
        map.put("/register.jsp","anon");   //anon公共资源  要放在上面  authc
        map.put("/user/register","anon");   //anon公共资源  要放在上面  authc
        map.put("/static","user");      //静态资源放行

        map.put("/**","authc");  //authc  请求这个资源需要认证和授权
        map.put("/**","user");   //记住我之后，这个路径可以使用
        //默认认证界面路径  默认是这个路径，可以更改
        shiroFilterFactoryBean.setLoginUrl("/login.jsp");
        //配置系统受限资源
        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);
        return shiroFilterFactoryBean;
    }

    //2.创建安全管理器
    @Bean
    public DefaultWebSecurityManager getDefaultWebSecutityManager(@Qualifier("getRealm") Realm realm  , DefaultWebSessionManager sessionManager){
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();

        //给安全管理器设置realm
        defaultWebSecurityManager.setRealm(realm);

        //加入缓存管理器
        defaultWebSecurityManager.setCacheManager(getCacheShiroManager());

        //加入会话管理器
        defaultWebSecurityManager.setSessionManager(sessionManager);

        //配置记住我
        defaultWebSecurityManager.setRememberMeManager(getCookieRememberMeManager());
        return defaultWebSecurityManager;
    }

    //3.创建自定义realm
    @Bean
    public Realm getRealm(CacheManager ehcache){
        CustomerRealm customerRealm = new CustomerRealm();

        //修改凭证校验匹配器
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();

        //设置加密算法为md5
        hashedCredentialsMatcher.setHashAlgorithmName("md5");

        //设置散列算法
        hashedCredentialsMatcher.setHashIterations(1024);

        customerRealm.setCredentialsMatcher(hashedCredentialsMatcher);



         /*
            使用使用shiro中自带EhCacheManager缓存管理器  开启缓存管理
          */
        //customerRealm.setCacheManager(ehcache);

        /*
             使用redis来缓存，实现CacheManager管理器  有问题
         */
        //customerRealm.setCacheManager(new RedisCacheManager());


//        customerRealm.setCachingEnabled(true);      //开启全局缓存
//        customerRealm.setAuthenticationCachingEnabled(true);    //认证缓存
//        customerRealm.setAuthenticationCacheName("authentication");         //自定义一个认证缓存的名字，可以不写。有默认的
//        customerRealm.setAuthorizationCachingEnabled(true);     //授权缓存
//        customerRealm.setAuthorizationCacheName("authorization");           //自定义一个授权缓存的名字，可以不写。有默认的
        return customerRealm;
    }

    /*
        配置方言标签
            可以使用thymeleaf
     */
//    @Bean
//    public ShiroDialect shiroDialect(){
//        return  new ShiroDialect();
//    }

    //缓存管理器 使用Ehcache实现
    @Bean
    public CacheManager getCacheShiroManager() {
        EhCacheManager ehCacheManager = new EhCacheManager();
        ehCacheManager.setCacheManagerConfigFile("classpath:ehcache.xml");
        return ehCacheManager;
    }


    //配置会话管理器
    @Bean
    public DefaultWebSessionManager defaultWebSessionManager(EhCacheManager cacheManager){
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        //加入缓存
        sessionManager.setCacheManager(cacheManager);
        //过期时间
        sessionManager.setGlobalSessionTimeout(15*1000);      //15miao
        //配置自定义会话Dao
        sessionManager.setSessionDAO(sessionDAO());

        return sessionManager;
    }

    //配置指定sessionDao
    @Bean
    public SessionDAO sessionDAO(){
        ShiroSessionDao shiroSessionDao = new ShiroSessionDao();
        return shiroSessionDao;
    }

    //配置记住我
    public CookieRememberMeManager getCookieRememberMeManager(){
        CookieRememberMeManager manager = new CookieRememberMeManager();
        SimpleCookie simpleCookie = new SimpleCookie("rememberme");
        //cookie记住时间
        simpleCookie.setMaxAge(60);//三分钟
        manager.setCookie(simpleCookie);
        return manager;
    }
    //Shiro生命周期处理器
    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }


    //开启aop对shiro的bean的动态代理
    @Bean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator(){
        DefaultAdvisorAutoProxyCreator creator = new DefaultAdvisorAutoProxyCreator();
        return creator;
    }
}
