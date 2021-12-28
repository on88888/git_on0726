package JDBC_Demo.exer;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class JDBCTest {

    @Test
    public void test04() throws IOException, ClassNotFoundException, SQLException {

        Properties properties = new Properties();
        FileInputStream fis = new FileInputStream("src\\JDBC_Demo\\jdbc.properties");
        properties.load(fis);
        String className = properties.getProperty("className");
        String url= properties.getProperty("url");
        String usernmae= properties.getProperty("usernmae");
        String password= properties.getProperty("password");
        fis.close();
        System.out.println(className + " " + url + " " + usernmae + " " + password);
        Class.forName(className);
        Connection connection = DriverManager.getConnection(url, usernmae, password);
        System.out.println(connection);

    }

    @Test
    public void test03() throws ClassNotFoundException, SQLException {

        Class<?> aClass = Class.forName("com.mysql.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/myemployees";
        Connection connection = DriverManager.getConnection(url, "root", "123456");
        System.out.println(connection);
        System.out.println(aClass);

    }

    @Test
    public void test02() throws SQLException {

        com.mysql.jdbc.Driver driver = new com.mysql.jdbc.Driver();
        DriverManager.registerDriver(driver);
        String url = "jdbc:mysql://localhost:3306/myemployees";
        Connection connection = DriverManager.getConnection(url, "root", "123456");
        System.out.println("connection = " + connection);


    }

    @Test
    public void test() throws SQLException {

        Driver driver = new com.mysql.jdbc.Driver();

        String url = "jdbc:mysql://localhost:3306/myemployees";
        Properties properties = new Properties();
        properties.setProperty("user", "root");
        properties.setProperty("password", "123456");
        Connection connect = driver.connect(url, properties);
        System.out.println(connect);


    }

}
