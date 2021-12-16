package com.atguigu.springbootdemo.service;

import com.atguigu.springbootdemo.bean.Employee;

import java.util.List;

/**
 * Created by Smexy on 2021/11/27
 */
public interface EmployeeService {

    Employee getEmployeeById(Integer id);

    void insertEmployee(Employee employee);

    void updateEmployee(Employee employee);

    void deleteEmployeeById(Integer id);

    List<Employee> getAll();
}
