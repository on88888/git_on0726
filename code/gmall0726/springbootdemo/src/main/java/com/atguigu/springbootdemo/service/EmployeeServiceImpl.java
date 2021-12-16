package com.atguigu.springbootdemo.service;

import com.atguigu.springbootdemo.bean.Employee;
import com.atguigu.springbootdemo.mappers.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Smexy on 2021/11/27
 *
 *      所有的业务方法都需要安装业务规范编写！
 *
 *      @Service的作用：
 *  *              ①给自己看的，同行看到某个类标识了当前注解，他知道这个类是一个业务模型
 *  *              ②springboot如果扫描到了这个注解所标识的类，自动为这个类在容器中创建一个对象
 */
@Service
public class EmployeeServiceImpl implements EmployeeService{

    //service通过调用Dao进行数据的读和写
    // 从容器中把已经创建好的EmployeeMapper类型的对象取出来，赋值给dao变量    dao = xxxx(容器已经创建好了)
    @Autowired
    private EmployeeMapper dao;

    @Override
    public Employee getEmployeeById(Integer id) {

        System.out.println("查询员工之前做的事情....");

        Employee employee = dao.getEmployeeById(id);

        System.out.println("查询员工之后做的事情....");

        return employee;
    }

    @Override
    public void insertEmployee(Employee employee) {

        System.out.println("新增员工之前做的事情....");

       dao.insertEmployee(employee);

        System.out.println("新增员工之后做的事情....");

    }

    @Override
    public void updateEmployee(Employee employee) {

        System.out.println("更新员工之前做的事情....");

        dao.updateEmployee(employee);

        System.out.println("更新员工之后做的事情....");

    }

    @Override
    public void deleteEmployeeById(Integer id) {

        System.out.println("删除员工之前做的事情....");

        dao.deleteEmployeeById(id);

        System.out.println("删除员工之后做的事情....");

    }

    @Override
    public List<Employee> getAll() {

        System.out.println("查询员工之前做的事情....");

        List<Employee> all = dao.getAll();

        System.out.println("查询员工之后做的事情....");
        return all;
    }
}
