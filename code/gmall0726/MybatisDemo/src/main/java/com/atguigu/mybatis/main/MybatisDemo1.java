package com.atguigu.mybatis.main;

import com.atguigu.mybatis.bean.Employee;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Smexy on 2021/11/26
 */
public class MybatisDemo1 {

    public static void main(String[] args) throws IOException {

        //1.基于配置的xml文件，创建一个sqlsessionfactory
        //指定配置文件的路径
        String resource = "config.xml";

        // 读取配置文件中的内容到一个输入流中
        InputStream inputStream = Resources.getResourceAsStream(resource);

        //基于读取的数据流创建一个SqlSessionFactory
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        System.out.println(sqlSessionFactory);

        // 2.构建 SqlSession，代表和数据库的一次连接
        SqlSession sqlSession = sqlSessionFactory.openSession();

        /*
        T selectOne(String statement, Object parameter);
            statement:  sql语句。 通过 namespace.id的方式找到sql

            parameter： 传入的参数
         */
        Object result = sqlSession.selectOne("facai.selectAnEmp", 1);

        Employee employee = (Employee) result;

        System.out.println(employee);

    }
}
