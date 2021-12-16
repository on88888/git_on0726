package com.phoenix.demo.thick;

import java.sql.*;

/**
 * @author leon
 * @ClassName MyPhoenixThickClient.java
 * @createTime 2021年09月01日 10:56:00
 */
public class MyPhoenixThickClient {
    public static void main(String[] args) throws SQLException {
        // 1. 获取连接对象
        Connection connection = DriverManager.getConnection("jdbc:phoenix:hadoop102:2181");
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
