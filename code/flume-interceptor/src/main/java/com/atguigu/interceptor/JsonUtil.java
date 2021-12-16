package com.atguigu.interceptor;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class JsonUtil {

    public static void main(String[] args) {

        Person person = new Person();
        person.setName("lisi");
        person.setAge(20);

        //将对象转成json字符串
        String json = JSON.toJSONString(person);
        System.out.println(json);

        //json转对象
        String js = "{ \"name\":\"wangwu\",\"age\":100 }";
        JSONObject jsonObject = JSON.parseObject(js);
        String name = jsonObject.getString("name");
        Integer age = jsonObject.getInteger("age");
        System.out.println(name);
        System.out.println(age);

        Person person1 = JSON.parseObject(js, Person.class);
        System.out.println(person1.getName());
        System.out.println(person1.getAge());
    }
}
