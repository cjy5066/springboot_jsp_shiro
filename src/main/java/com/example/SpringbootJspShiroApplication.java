package com.example;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class SpringbootJspShiroApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootJspShiroApplication.class, args);
        System.out.println("启动成功！！");
    }

}
