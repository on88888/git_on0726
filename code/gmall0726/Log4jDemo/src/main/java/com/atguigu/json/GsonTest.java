package com.atguigu.json;

import com.atguigu.bean.Employee;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

/**
 * Created by Smexy on 2021/11/27
 */
public class GsonTest {

    public static void main(String[] args) {

        Gson gson = new Gson();

        Employee employee2 = new Employee(1, "jack", "aaa", "bbb");
        Employee employee1 = new Employee(1, "jack1", "aaa", "bbb");

        ArrayList<Employee> employees = new ArrayList<>();

        employees.add(employee1);
        employees.add(employee2);

        String s1 = gson.toJson(employee1);

        String s2 = gson.toJson(employees);

        System.out.println(s1);
        System.out.println(s2);


        System.out.println("--------------------------------");


        Employee employee = gson.fromJson(s1, Employee.class);

        System.out.println(employee);

        /*
                public <T> T fromJson(String json, Type typeOfT)

                    public class TypeToken<T> {
                        final Class<? super T> rawType;
                        final Type type;

                        new TypeToken().getType()


                 自己写一个子类，子类继承TypeToken

                 new 子类().getType()


         */
        ArrayList<Employee> emps= gson.fromJson(s2,new TypeToken<ArrayList<Employee>>(){}.getType());

        System.out.println(emps);

    }
}
