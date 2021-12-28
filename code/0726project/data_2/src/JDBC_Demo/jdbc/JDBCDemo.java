package JDBC_Demo.jdbc;

import org.junit.Test;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/*

    JDBC:Java提供的一套用来操作数据库的接口

    Java程序操作数据库准备工作：
    1.保证数据库可以正常使用（比如数据库的服务是否开启）
    2.保证数据库的账户和密码正确
    3.保证驱动包的版本没有问题
    4.导包-将驱动包添加到工程中
        ①在工程中创建一个目录libs
        ②将jar包拷贝过来
        ③在jar包上右键 -> add as library
 */

public class JDBCDemo {
    /*
        方式三 （终级版）：通过读取配置文件的方式获取数据库连接对象
     */
    @Test
    public void test4() throws Exception {
        //创建Properties对象
        Properties properties = new Properties();
        //创建流
        FileInputStream fis = new FileInputStream("src\\JDBC_Demo\\jdbc.properties");
        //加载流
        properties.load(fis);
        //读取配置文件中的内容
        String className = properties.getProperty("className");
        String url= properties.getProperty("url");
        String usernmae= properties.getProperty("usernmae");
        String password= properties.getProperty("password");
        //关流
        fis.close();

        System.out.println(className + " " + url + " " + usernmae + " " + password);

        //1.让静态代码块执行
        Class.forName(className);
        //2.获取数据库连接对象
        Connection connection = DriverManager.getConnection(url, usernmae, password);
        System.out.println(connection);
    }
    /*
        方式二的优化
     */
    @Test
    public void test3() throws Exception {
        //在驱动包中的Driver类中的静态代码块 ：①创建Driver对象 ②通过DriverManager注册Driver
        //1.让静态代码块执行
        Class.forName("com.mysql.jdbc.Driver");
        //3.获取数据库连接对象
        String url = "jdbc:mysql://localhost:3306/db3";
        Connection connection = DriverManager.getConnection(url, "root", "123456");
        System.out.println(connection);
    }

    /*
        方式二 ：通过DriverManager获取数据库连接对象
     */
    @Test
    public void test2() throws Exception {
        //1.创建Driver对象
        Driver driver = new com.mysql.jdbc.Driver();
        //2.通过DriverManager注册Driver
        DriverManager.registerDriver(driver);
        //3.通过DriverManager获取数据库连接对象
        /*
         getConnection(String url,String user, String password)
         url : 数据库连接对象
         user : mysql账号
         password : mysql密码
         */
        String url = "jdbc:mysql://localhost:3306/db3";
        Connection connection = DriverManager.getConnection(url, "root", "123456");
        System.out.println(connection);
    }
    /*
        方式一 ： 通过Driver获取数据库连接对象
     */
    @Test
    public void test() throws SQLException {
        //多态
        Driver driver = new com.mysql.jdbc.Driver();
        /*
            connect(String url, java.util.Properties info)
            url : 数据库连接地址
                jdbc:mysql://localhost:3306/atguigu
                jdbc:mysql ：协议
                localhost（本机-127.0.0.1） ：mysql的地址
                3306 ： 端口号
                atguigu ：数据库的名字

            info : 该集合中用来存放Mysql的账号和密码
         */
        //String url = "jdbc:mysql://localhost:3306/db4";
        String url = "jdbc:mysql://localhost:3306/myemployees";
        Properties properties = new Properties();
        properties.setProperty("user","root");//mysql账号
        properties.setProperty("password","123456");//mysql密码
        Connection connect = driver.connect(url, properties);
        System.out.println(connect);
    }


}
