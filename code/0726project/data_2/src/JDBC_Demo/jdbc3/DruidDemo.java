package JDBC_Demo.jdbc3;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.alibaba.druid.pool.DruidPooledConnection;
import org.junit.Test;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/*
    数据库连接池
 */
public class DruidDemo {
    /*
        在代码中配置
     */
    @Test
    public void test() throws SQLException {
        //1.创建数据源（数据库连接池）对象
        DruidDataSource source = new DruidDataSource();
        //2.设置一些配置信息
        source.setDriverClassName("com.mysql.jdbc.Driver");//Driver类的全类名
        source.setUrl("jdbc:mysql://localhost:3306/db3");//数据库连接地址
        source.setUsername("root");//数据库的用户名
        source.setPassword("123456");//数据库的密码
        //3.获取数据库连接对象
        Connection connection = source.getConnection();
        System.out.println(connection);
        //4.关闭（还回去）
        connection.close();
    }

    /*
        在配置文件中配置
     */
    @Test
    public void test2() throws Exception {
        //创建Properties对象
        Properties properties = new Properties();
        //加载流
        /*
            注意：
                1.配置文件的名字可以瞎写-因为配置文件由我们决定
                2.配置文件中的key值不能瞎写，因为该值是由框架（Druid）读取的。
         */
        properties.load(new FileInputStream("E:\\0726project\\data_2\\src\\JDBC_Demo\\druid.properties"));


        //1.创建DataSource对象
        DataSource dataSource = DruidDataSourceFactory.createDataSource(properties);
        //2.获取数据库连接对象
        Connection connection = dataSource.getConnection();
        System.out.println(connection);
        //3.关闭资源（还回去）
        connection.close();
    }
}
