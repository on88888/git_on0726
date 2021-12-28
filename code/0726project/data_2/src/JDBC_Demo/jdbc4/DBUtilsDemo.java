package JDBC_Demo.jdbc4;


import JDBC_Demo.jdbc2.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

public class DBUtilsDemo {
    /*
        通过DBUtils实现对数据库的增，删 ，改的操作
     */
    @Test
    public void test() throws SQLException {
        //1.创建QueryRunner对象
        QueryRunner qr = new QueryRunner();
        //2.添加数据
        /*
         update(Connection conn, String sql, Object... params)
         conn : 数据库连接对象
         sql : sql语句
         params : 给占位符赋值的内容
         */
        int result = qr.update(JDBCUtils.getConnection(),
                "insert into student(id,name) values(?,?)"
                , 4, "wen ge");

        System.out.println("有" + result + "条数据受到影响");
    }

    /*
        对数据库进行查找
     */
    @Test
    public void test2() throws SQLException {
        //1.创建QueryRunner对象
        QueryRunner qr = new QueryRunner();
        //2.查找数据
        /*
        query(Connection conn, String sql, ResultSetHandler<T> rsh,
            Object... params)
         conn : 数据库连接对象
         sql : sql语句
         ResultSetHandler ：用来决定返回结果的类型（一条数据就是单个对象，多条数据就返回List结合等）
         params ：给占位符赋值的内容
         */
        //Student student = qr.query(JDBCUtils.getConnection(), "select * from student where id=?",
                //new BeanHandler<Student>(Student.class), 1);

        /*
            注意：表中字段的名字要和类中字段的名字相同。如果不相同那么需要通过别名的方式处理。
         */
        List<Student> list = qr.query(JDBCUtils.getConnection(), "select id," +
                "name sname,age from student", new BeanListHandler<Student>(Student.class));

        for (Student student : list) {
            System.out.println(student);
        }

    }
}
