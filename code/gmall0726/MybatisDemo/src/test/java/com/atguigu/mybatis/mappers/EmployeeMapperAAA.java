package com.atguigu.mybatis.mappers;

import com.atguigu.mybatis.bean.Employee;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

import static org.junit.Assert.*;

/**
 * Created by Smexy on 2021/11/26
 */
public class EmployeeMapperAAA {

    private  SqlSessionFactory sqlSessionFactory;

    {
        //1.基于配置的xml文件，创建一个sqlsessionfactory
        //指定配置文件的路径
        String resource = "config.xml";

        // 读取配置文件中的内容到一个输入流中
        InputStream inputStream = null;
        try {
            inputStream = Resources.getResourceAsStream(resource);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //基于读取的数据流创建一个SqlSessionFactory
         sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    }

    @org.junit.Test
    public void getEmployeeById() {

        //每个方法创建一个当前方法使用的对象，不能共享
        SqlSession session = sqlSessionFactory.openSession();

        try {
            // do work
            EmployeeMapper mapper = session.getMapper(EmployeeMapper.class);

            Employee employee = mapper.getEmployeeById(1);

            System.out.println(employee);
        } finally {
            session.close();
        }

    }

    // mvn test(运行test包下的名称为xxxTest的类)   mvn compile 运行
    @org.junit.Test
    public void insertEmployee() {

        SqlSession session = sqlSessionFactory.openSession(true);

        try {
            // do work
            EmployeeMapper mapper = session.getMapper(EmployeeMapper.class);

            Employee e = mapper.getEmployeeById(3);

            e.setLastName("tom");

            mapper.insertEmployee(e);

        } finally {
            session.close();
        }
    }

    @org.junit.Test
    public void updateEmployee() {

        //自动提交
        SqlSession session = sqlSessionFactory.openSession(true);

        try {
            // do work
            EmployeeMapper mapper = session.getMapper(EmployeeMapper.class);

            Employee e = mapper.getEmployeeById(2);

            e.setEmail("xxxxxx");

            mapper.updateEmployee(e);

        } finally {
            session.close();
        }
    }

    // 增删改都属于写操作，必须提交事务！ SqlSession不会自动提交事务！
    @org.junit.Test
    public void deleteEmployeeById() {

        SqlSession session = sqlSessionFactory.openSession();

        try {
            // do work
            EmployeeMapper mapper = session.getMapper(EmployeeMapper.class);

            mapper.deleteEmployeeById(1);

            //手动提交
            session.commit();
        } finally {
            session.close();
        }
    }

    @org.junit.Test
    public void getAll() {

        SqlSession session = sqlSessionFactory.openSession();

        try {
            // Mybatis提供了动态代理技术，可以自动为接口创建一个实现类
            EmployeeMapper mapper = session.getMapper(EmployeeMapper.class);

            //com.sun.proxy.$Proxy5  implements EmployeeMapper
            System.out.println(mapper.getClass().getName());

            Class<?>[] interfaces = mapper.getClass().getInterfaces();

            for (Class<?> anInterface : interfaces) {

                System.out.println(anInterface.getSimpleName());

            }

            System.out.println(mapper.getAll());


        } finally {
            session.close();
        }
    }
}