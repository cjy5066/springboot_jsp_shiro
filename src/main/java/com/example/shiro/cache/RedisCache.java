package com.example.shiro.cache;

import com.example.utils.ApplicationContextUtils;

import com.example.utils.RedisUtils;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.Set;

//自定义redis缓存的实现
public class RedisCache<K,V> implements Cache<K,V> {

//    @Autowired
    private  RedisUtils redisUtils;

    @Override
    public V get(K k) throws CacheException {
        System.out.println("get k: "+k);
//        RedisTemplate redisTemplate = (RedisTemplate)ApplicationContextUtils.getBean("redisTemplate");
//        redisTemplate.setKeySerializer(new StringRedisSerializer());
        return (V) redisUtils.get(k.toString());
    }

    @Override
    public V put(K k, V v) throws CacheException {

        System.out.println("put  k: "+k);
        System.out.println("put  v: "+v);
        //使用工厂类获取
//        RedisTemplate redisTemplate = (RedisTemplate)ApplicationContextUtils.getBean("redisTemplate");
//        redisTemplate.setKeySerializer(new StringRedisSerializer());
//        redisTemplate.opsForValue().set(k.toString(),v);
        boolean set = redisUtils.set(k.toString(), v);
        return null;
    }

    @Override
    public V remove(K k) throws CacheException {
        return null;
    }

    @Override
    public void clear() throws CacheException {

    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public Set<K> keys() {
        return null;
    }

    @Override
    public Collection<V> values() {
        return null;
    }
}
