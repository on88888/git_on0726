package JDBC_Demo.jdbc4;



import JDBC_Demo.jdbc2.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/*
    批处理：(添加批量数据效率高)
    1.在数据库连接地址后面加一个参数：
        jdbc:mysql://localhost:3306/db4?rewriteBatchedStatements=true

    2.批处理支持的驱动包 ：5.1.3x
 */
public class BatchDemo {
    public static void main(String[] args) throws SQLException {
        Connection connection = JDBCUtils.getConnection();
        String sql = "insert into student(id,name,age) values(?,?,?)";
        PreparedStatement ps = connection.prepareStatement(sql);
        for (int i = 1; i <= 100000 ; i++) {
            ps.setInt(1,i);
            ps.setString(2,"aa"+i);
            ps.setInt(3,18);
            //添加到批处理
            ps.addBatch();

            if (i % 1000 == 0){
                //执行批处理
                ps.executeBatch();
                //清空批处理
                ps.clearBatch();
            }
        }
        JDBCUtils.close(ps,connection);
    }
}
