package com.phoenix.demo.thin;

import java.sql.*;

/**
 * @author leon
 * @Discribe  : JDBC:  1. 驱动类的加载 2. 获取连接对象 3. 编写SQL  4. 预编译  5. 执行  6 解析结果集 7. 关闭资源
 * @ClassName MyPhoenixThinClient.java
 * @createTime 2021年09月01日 10:44:00
 */
public class MyPhoenixThinClient {
    public static void main(String[] args) throws SQLException {

        // 1. 获取连接对象
        Connection connection = DriverManager.getConnection("jdbc:phoenix:thin:url=http://hadoop102:8765;serialization=PROTOBUF");
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
