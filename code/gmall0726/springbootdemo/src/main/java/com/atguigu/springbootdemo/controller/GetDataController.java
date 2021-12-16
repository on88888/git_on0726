package com.atguigu.springbootdemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Smexy on 2021/11/27
 */
@RestController
public class GetDataController {

    @RequestMapping(value = "/sendData")
    public Object handle1(String name,Integer age){

        System.out.println(name + ":" + age);

        return  "success";

    }

}
