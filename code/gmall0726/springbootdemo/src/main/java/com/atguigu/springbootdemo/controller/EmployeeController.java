package com.atguigu.springbootdemo.controller;

import com.atguigu.springbootdemo.bean.Employee;
import com.atguigu.springbootdemo.service.EmployeeService;
import com.atguigu.springbootdemo.service.EmployeeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Smexy on 2021/11/27
 */
@RestController
public class EmployeeController {

    //Controller调用service  employeeService= xxxx(EmployeeService对象)
    @Autowired
    private EmployeeService employeeService;

        @RequestMapping(value = "/getAll")
        public Object handle1(){


            List<Employee> all = employeeService.getAll();

            return all;

        }

    @RequestMapping(value = "/emp")
    public Object handle2(String op,String name,String gender,String email,Integer id){

        Employee employee = new Employee(id, name, gender, email);

        switch (op){

            case "select":  if (id != null){

                Employee e = employeeService.getEmployeeById(id);

                return e == null ? "查无此人" : e;
            }else {
                return "必须传入id!";
            }

            case "insert": employeeService.insertEmployee(employee);
                return "success";

            case "update": employeeService.updateEmployee(employee);
                return "success";

            case "delete":  if (id != null){

                employeeService.deleteEmployeeById(id);

                return "success";
            }else {
                return "必须传入id!";
            }

            default: return "success!";

        }


    }


}
