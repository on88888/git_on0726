package test;

import org.apache.phoenix.queryserver.client.ThinClientUtil;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Properties;

/**
 * @Author 0726
 * @ClassName T1.java
 * @createTime 2021年10月13日 20:39
 */
public class T1 {
    /**
     * 批次插入
     * @throws Exception
     */
    @Test
    public void upsertBatch() throws Exception{
        //1、加载驱动
        Class.forName("org.apache.phoenix.queryserver.client.Driver");
        //2、获取connection链接
        String url = ThinClientUtil.getConnectionUrl("hadoop102", 8765);

        Connection connection = DriverManager.getConnection(url);
        //设置自动提交
        connection.setAutoCommit(true);
        //1、获取statement
        String sql = "upsert into person2 values(?,?,?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        //2、赋值
        for (int i = 0; i <=2500; i++) {
            //1、获取statemnt
            //2、赋值
            statement.setString(1,"1001"+i);
            statement.setString(2,"zhangsan"+i);
            statement.setString(3,(20+i)+"");
            //将当前结果放入一个批次中
            statement.addBatch();
            //3、执行
            if (i%1000==0) {
                //提交一个批次
                statement.executeUpdate();
                //清空批次
                statement.clearBatch();
            }
        }
        //提交最后一个不满的批次
        statement.executeBatch();
        connection.commit();
        //5、关闭
        statement.close();
        connection.close();
    }
}
