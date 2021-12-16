package com.atguigu.json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.atguigu.bean.Employee;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Smexy on 2021/11/27
 *
 *      核心类：
 *              JSON
 *
 *              对象转 string :   JSON.toJSONString(对象)
 *
 *              string转对象：   JSON.parseXxx()
 *
 *                  []:         JSON.parseArray()
 *                  {}:          JSON.parseObject()
 *
 *
 *              JSONObject: 代表一个 {}字符串转换后的Java对象，本质是Map
 *
 *              JSONArray: 代表一个 []字符串转换后的Java对象，本质是List
 *
 *
 *
 */
public class FastJsonTest {

    public static void main(String[] args) {

        Employee employee2 = new Employee(1, "jack", "aaa", "bbb");
        Employee employee1 = new Employee(1, "jack1", "aaa", "bbb");

        ArrayList<Employee> employees = new ArrayList<>();

        employees.add(employee1);
        employees.add(employee2);

        //{"email":"bbb","gender":"aaa","id":1,"lastName":"jack"}
        String s = JSON.toJSONString(employee1);

        System.out.println(s);

        //[{"email":"bbb","gender":"aaa","id":1,"lastName":"jack"},{"email":"bbb","gender":"aaa","id":1,"lastName":"jack"}]
        String s1 = JSON.toJSONString(employees);

        System.out.println(s1);

        System.out.println("-------------{}str 转java对象----------------");

        JSONObject jsonObject = JSON.parseObject(s);

        String lastName = jsonObject.getString("lastName");

        System.out.println(lastName);


        Employee employee = JSON.parseObject(s, Employee.class);

        System.out.println(employee);


        System.out.println("-------------[]str 转java对象----------------");

        JSONArray jsonArray = JSON.parseArray(s1);

        Employee e1 = jsonArray.getObject(0, Employee.class);

        System.out.println(e1);

        List<Employee> employees1 = JSON.parseArray(s1, Employee.class);

        System.out.println(employees1);


    }

}
