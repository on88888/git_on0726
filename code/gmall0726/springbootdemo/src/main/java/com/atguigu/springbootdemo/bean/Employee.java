package com.atguigu.springbootdemo.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by Smexy on 2021/11/26
 *      标准的javabean:
 *              ①空参构造器
 *              ②为私有属性提供公有的访问方法
 */
@Data // 提供getter,setter,toString
@NoArgsConstructor
@AllArgsConstructor
public class Employee {

    private Integer id;
    private String lastName;
    private String gender;
    private String email;

   /* public void setLast_name(String name){

        this.lastName = name;

    }*/

    public static void main(String[] args) {

        Employee employee = new Employee();

        Employee employee1 = new Employee(1, "jack", "aaa", "bbb");



    }
}
