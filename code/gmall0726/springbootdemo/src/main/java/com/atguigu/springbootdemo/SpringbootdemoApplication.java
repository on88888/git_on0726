package com.atguigu.springbootdemo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// webapp的主程序
@SpringBootApplication
// 指定Mybatis中Mapper接口所在的包，容器会自动在这些包下，为包下所有标注了@Repository注解的类，使用动态代理技术创建对象
@MapperScan(basePackages = "com.atguigu.springbootdemo.mappers")
public class SpringbootdemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootdemoApplication.class, args);
    }

}
