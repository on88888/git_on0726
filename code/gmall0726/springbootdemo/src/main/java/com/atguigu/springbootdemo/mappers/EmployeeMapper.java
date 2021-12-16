package com.atguigu.springbootdemo.mappers;


import com.atguigu.springbootdemo.bean.Employee;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Smexy on 2021/11/26
 * @Repository的作用：
 *  *              ①给自己看的，同行看到某个类标识了当前注解，他知道这个类是一个Dao
 *  *              ②springboot如果扫描到了这个注解所标识的类，自动为这个类在容器中创建一个对象
 *
 *                  ③当前是接口，无法实例化，需要告诉容器，请使用mybatis提供的动态代理技术，为当前类创建一个对象
 */
@Repository
public interface EmployeeMapper {

    Employee getEmployeeById(Integer id);

    void insertEmployee(Employee employee);

    void updateEmployee(Employee employee);

    void deleteEmployeeById(Integer id);

    List<Employee> getAll();

}
