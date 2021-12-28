package JDBC_Demo.jdbc2;

import org.junit.Before;
import org.junit.Test;

import java.io.FileInputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/*
    通过代码对数据库进行增，删，改，查的操作
 */
public class CRUDDemo {

    /*
    CREATE TABLE student(
    id INT,
    NAME VARCHAR(20),
    age INT
    );
        向表中插入数据
     */
    @Test
    public void insert() throws SQLException {
        //1.获取数据库连接对象
        Connection connection = JDBCUtils.getConnection();
        //2.写sql语句 :
        // ? : 占位符
        String sql = "insert into student(id,name,age) values(?,?,?)";
        //3.预编译
        PreparedStatement ps = connection.prepareStatement(sql);
        //4.给占位符赋值
        /*
            setInt(int parameterIndex, int x)
            parameterIndex : 第几个占位符、
            x : 给占位符赋值的内容
         */
        ps.setInt(1,3);
        ps.setString(2,"xiaolongge");
        ps.setInt(3,18);
        //5.执行sql语句
        //返回值 ：有几条数据受到影响
        int result = ps.executeUpdate();//该方法用来执行增，删，改的语句
        System.out.println("有" + result + "条数据受到影响");
        //6.关闭资源
       JDBCUtils.close(ps,connection);
    }

    /*
        删除数据
     */
    @Test
    public void delete() throws SQLException {
        //1.获取数据库连接对象
        Connection connection = JDBCUtils.getConnection();
        //2.SQL语句
        String sql = "delete from student where id=?";
        //3.预编译
        PreparedStatement ps = connection.prepareStatement(sql);
        //4.给占位符赋值
        ps.setInt(1,3);
        //5.执行sql
        int result = ps.executeUpdate();
        System.out.println("有" + result + "条数据受到影响");
        //6.关闭资源
        JDBCUtils.close(ps,connection);
    }

    /*
        修改表中的数据
     */
    @Test
    public void update() throws SQLException {
        //1.获取数据库连接对象
        Connection connection = JDBCUtils.getConnection();
        //2.SQL语句
        String sql = "update student set name=?,age=? where id=?";
        //3.预编译
        PreparedStatement ps = connection.prepareStatement(sql);
        //4.给占位符赋值
        ps.setString(1,"wei ge");
        ps.setInt(2,39);
        ps.setInt(3,3);
        //5.执行sql语句
        int result = ps.executeUpdate();
        System.out.println("有" + result + "条数据受到影响");
        //6.关闭资源
        JDBCUtils.close(ps,connection);
    }

    /*
        查询表中的数据
     */
    @Test
    public void query() throws SQLException {
        //1.获取数据库连接对象
        Connection connection = JDBCUtils.getConnection();
        //2.SQL语句
        String sql = "select id,name,age from student where id=?";
        //3.预编译
        PreparedStatement ps = connection.prepareStatement(sql);
        //4.给占位符赋值
        ps.setInt(1,3);
        //5.执行sql
        ResultSet rs = ps.executeQuery();//executeQuery() ：执行select语句
        //6.遍历结果集
        while (rs.next()){//next()：是否有下一条数据
            //7.获取数据
            /*
            getInt(String columnLabel) ：根据字段名获取对应的数据
            columnLabel : 字段名
             */
            int id = rs.getInt("id");
            String name = rs.getString("name");
            int age = rs.getInt("age");
            System.out.println(id + " " + name + " " + age);
        }
        //7.关闭资源
        JDBCUtils.close(ps,connection,rs);
    }

    /*
        通过调用一个方法返回所有的数据
     */
    @Test
    public void query2() throws SQLException {
        List<Student> students = getStudents();
        for (Student s:students) {
            System.out.println(s);
        }
    }

    /*
        封装（设计方法）
     */
    public List<Student> getStudents() throws SQLException {
        //集合用来存放所有的数据
        List<Student> students = new ArrayList<>();
        //1.获取数据库连接对象
        Connection connection = JDBCUtils.getConnection();
        //2.SQL语句
        String sql = "select id,name,age from student";
        //3.预编译
        PreparedStatement ps = connection.prepareStatement(sql);

        //5.执行sql
        ResultSet rs = ps.executeQuery();//executeQuery() ：执行select语句
        //6.遍历结果集
        while (rs.next()){//next()：是否有下一条数据
            //7.获取数据
            /*
            getInt(String columnLabel) ：根据字段名获取对应的数据
            columnLabel : 字段名
             */
            int id = rs.getInt("id");
            String name = rs.getString("name");
            int age = rs.getInt("age");
            Student student = new Student(id, name, age);
            //将对象存放到集合中
            students.add(student);
        }
        //7.关闭资源
        JDBCUtils.close(ps,connection,rs);
        //8.返回集合
        return students;
    }
}
