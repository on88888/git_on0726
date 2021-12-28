package JDBC_Demo.jdbc3;

/*
事务：一组逻辑操作单元,使数据从一种状态变换到另一种状态。
场景：AA给CC转账1000元
	name	balance
	AA	2000
	CC	2000
	try{
		1.开启事务
		通过代码让AA减去1000
		System.out.println(1/0);
		通过代码让CC加上1000
		2.事务提交
	}catch(Exception e){
		3.事务回滚
	}

	CREATE TABLE account(
    NAME VARCHAR(20),
    balance INT
    )
*/


import JDBC_Demo.jdbc2.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AccountDemo {
    public static void main(String[] args) {
        Connection connection = null;
        PreparedStatement ps = null;

        try {
            //1.获取数据库连接对象
            connection = JDBCUtils.getConnection();
            //禁止自动提交（开启事务）
            connection.setAutoCommit(false);
            //2.SQL语句
            String sql = "update account set balance=? where name=?";
            //3.预编译
            ps = connection.prepareStatement(sql);
            //4.赋值
            //AA - 1000
            ps.setInt(1,1000);
            ps.setString(2,"aa");
            //5.执行sql
            ps.executeUpdate();

            //System.out.println(1/0);

            //CC + 1000
            ps.setInt(1,3000);
            ps.setString(2,"cc");
            //5.执行sql
            ps.executeUpdate();

            //事务提交
            connection.commit();
        }catch (Exception e){
            e.printStackTrace();

            try {
                //事务回滚
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }finally {
            //允许自动提交
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            //关闭资源
            JDBCUtils.close(ps,connection);
        }
    }
}
