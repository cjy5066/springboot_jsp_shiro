package com.example.utils;

import org.apache.shiro.crypto.hash.Md5Hash;

import java.util.Random;

//生成盐的工具类
public class SaltUtils {

    public static String getSalt(int i){
        char[] chars = "QWERTYUIOPASDFGHJKLZXCVBNMqwertyuiopasdfghjklzxcvbnm1234567890".toCharArray();
        StringBuffer stringBuffer = new StringBuffer();
        for(int i1=0;i1<8;i1++){
            Random random = new Random();
            int i2 = random.nextInt(chars.length);
            char c=chars[i2];
            stringBuffer.append(c);
        }
        return stringBuffer.toString();
    }

    public static void main(String[] args) {
        String salt = getSalt(8);
        System.out.println(salt);
    }
}
