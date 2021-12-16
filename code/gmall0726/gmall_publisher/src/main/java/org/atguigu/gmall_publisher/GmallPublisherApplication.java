package org.atguigu.gmall_publisher;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "org.atguigu.gmall_publisher.mappers")
public class GmallPublisherApplication {

    public static void main(String[] args) {
        SpringApplication.run(GmallPublisherApplication.class, args);
    }

}
