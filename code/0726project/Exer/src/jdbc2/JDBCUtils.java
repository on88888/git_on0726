package jdbc2;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class JDBCUtils {
    private static String className;
    private static String url;
    private static String usernmae;
    private static String password;

    /*
        读取配置文件中的内容
     */
    static {
        FileInputStream fis = null;
        try {
            //创建Properties对象
            Properties properties = new Properties();
            //创建流
            fis = new FileInputStream("E:\\0726project\\Exer\\src\\jdbc2\\jdbc.properties");
            //加载流
            properties.load(fis);
            //读取配置文件中的内容
            className = properties.getProperty("className");
            url = properties.getProperty("url");
            usernmae = properties.getProperty("usernmae");
            password = properties.getProperty("password");
        }catch (Exception e){
            //终止程序的运行
            //将编译时异常转为运行时异常
            throw new RuntimeException(e.getMessage());
        }finally {
            //关流
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    /*
        返回数据库连接对象
     */
    public static Connection getConnection(){
        try {
            //1.让静态代码块执行
            Class.forName(className);
            //2.获取数据库连接对象
            return DriverManager.getConnection(url, usernmae, password);
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    /*
        关闭资源
     */
    public static void close(PreparedStatement ps, Connection connection) {
        if (ps != null){
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(connection != null){
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void close(PreparedStatement ps, Connection connection, ResultSet rs) {
        close(ps,connection);
        if (rs != null){
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }
}
