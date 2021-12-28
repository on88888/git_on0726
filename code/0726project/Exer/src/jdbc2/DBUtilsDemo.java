package jdbc2;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

public class DBUtilsDemo {
    @Test
    public void test01() throws SQLException {

        QueryRunner qr = new QueryRunner();
        int i = qr.update(JDBCUtils.getConnection(), "insert into student(id,name) values(?,?)", 4, "da huang");
        System.out.println("i = " + i);
    }

    @Test
    public void test02() throws SQLException {
        QueryRunner qr = new QueryRunner();
        List<Student> list = qr.query(JDBCUtils.getConnection(), "select id," +
                "name name,age from student", new BeanListHandler<Student>(Student.class));
        for (Student student : list) {
            System.out.println(student);
        }


    }

}
