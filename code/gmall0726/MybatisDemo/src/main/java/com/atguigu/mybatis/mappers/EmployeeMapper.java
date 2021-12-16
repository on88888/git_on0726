package com.atguigu.mybatis.mappers;

import com.atguigu.mybatis.bean.Employee;

import java.util.List;

/**
 * Created by Smexy on 2021/11/26
 */
public interface EmployeeMapper {

    Employee getEmployeeById(Integer id);

    void insertEmployee(Employee employee);

    void updateEmployee(Employee employee);

    void deleteEmployeeById(Integer id);

    List<Employee> getAll();

}
