package com.example.shiro.cache;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;

//自定义shiro缓存管理器
public class RedisCacheManager implements CacheManager {

    /**
     *
     * @param s         在shiroconfig类，自定义授权或者认证缓存的名字
     * @param <K>
     * @param <V>
     * @return
     * @throws CacheException
     */
    @Override
    public <K, V> Cache<K, V> getCache(String s) throws CacheException {
        System.out.println("这个s是： "+s);
        return new RedisCache<K,V>();
    }
}
