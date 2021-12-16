package com.atguigu.springbootdemo.controller;

import com.atguigu.springbootdemo.bean.Employee;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Smexy on 2021/11/27
 *
 *      @Controller的作用：
 *              ①给自己看的，同行看到某个类标识了当前注解，他知道这个类是一个控制器
 *              ②springboot如果扫描到了这个注解所标识的类，自动为这个类在容器中创建一个对象
 *
 *
 *              容器： 肉眼不可见
 *              springboot会扫描哪些地方？
 *                      springboot默认扫描SpringbootdemoApplication(主启动类)所在包及其子包
 *
 *
 *
 *        如果一个Controller所有的方法都是返回数据，而不是返回页面，可以直接使用@RestController
 *
 *          @RestController =  @Controller的作用 + 为当前注解标识的类的所有方法，自动添加 @ResponseBody
 */
//@Controller
    @RestController
public class HelloController {

    /*
            @RequestMapping 声明当前方法，可以处理哪个路径的请求

            <a href="/myapp/hello" >  完整路径：  http://主机名:端口号/ myapp/hello

                             在.java文件中，/解析为                    http://主机名:端口号/ myapp/

     */
    @RequestMapping(value = "/hello")
    public Object handle(){

        System.out.println("处理了Hello请求");

        //处理完成后，让浏览器收到success.html页面
        return "/success.html";
    }

    //响应一份数据
    @ResponseBody //直接将返回值放入响应体，写给浏览器
    @RequestMapping(value = "/hello1")
    public Object handle1(){

        System.out.println("处理了Hello1请求");

        return "/success.html";

    }

    //响应一份数据
    @ResponseBody //直接将返回值放入响应体，写给浏览器
    @RequestMapping(value = "/hello2")
    public Object handle2(){

        System.out.println("处理了Hello2请求");

        Employee employee1 = new Employee(1, "jack", "aaa", "bbb");

        return employee1;

    }
}
