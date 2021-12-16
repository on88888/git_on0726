package com.phoenix.demo.thick;

import java.sql.*;
import java.util.Properties;

/**
 * @author leon
 * @ClassName PhoenixThickClient.java
 * @createTime 2021年09月01日 11:00:00
 */
public class PhoenixThickClient {
    public static void main(String[] args) throws SQLException {
        String url = "jdbc:phoenix:hadoop102:2181";
        Properties props = new Properties();
        props.put("phoenix.schema.isNamespaceMappingEnabled","true");
        // 1. 获取连接对象
        Connection connection = DriverManager.getConnection(url, props);
        // 2. 编写SQL
        String sql = "select * from \"student\"";
        // 3. 预编译
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        // 4. 执行
        ResultSet resultSet = preparedStatement.executeQuery();
        // 5. 解析结果集
        while (resultSet.next()){
            String id = resultSet.getString(1);
            String age = resultSet.getString(2);
            System.out.println("数据："+id+", "+age);
        }
        // 6. 关闭资源
        preparedStatement.close();
        connection.close();

    }
}
