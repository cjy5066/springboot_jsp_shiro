package com.example.shiro.session;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.CachingSessionDAO;
import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;

import java.io.Serializable;

//针对自定义的ShiroSession的db操作
public class ShiroSessionDao extends EnterpriseCacheSessionDAO {

    //这个父类的构造方法加入了缓存
    public ShiroSessionDao(){
        //添加缓存
        //setCacheManager();
    }
    //当修改的时候做什么
    @Override
    protected void doUpdate(Session session) {

    }

    //当删除的时候做什么
    @Override
    protected void doDelete(Session session) {

    }

    //当创建做什么
    @Override
    protected Serializable doCreate(Session session) {
        //生成会话唯一 ID
        Serializable sessionId = this.generateSessionId(session);
        this.assignSessionId(session, sessionId);
        return sessionId;
    }

    //当读的时候
    @Override
    protected Session doReadSession(Serializable serializable) {
        return null;
    }
}
