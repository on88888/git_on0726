package test;

import org.apache.phoenix.queryserver.client.ThinClientUtil;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @Author 0726
 * @ClassName PhoenixText.java
 * @createTime 2021年10月13日 10:31
 */
public class PhoenixText {

    private Connection connection = null;
    private PreparedStatement statement = null;

    @Before
    public void init() throws Exception{
        //1、加载驱动
        Class.forName("org.apache.phoenix.queryserver.client.Driver");
        //2、获取connection链接
        String url = ThinClientUtil.getConnectionUrl("hadoop102", 8765);

        Connection connection = DriverManager.getConnection(url);
        //设置自动提交
        connection.setAutoCommit(true);
    }

    @After
    public void close() throws Exception {

        if (connection!=null){
            connection.close();
        }
        if (statement!=null){
            statement.close();
        }
    }

    /**
     * 创建表
     * @throws Exception
     */
    @Test
    public void createTable() throws Exception {
        //3、创建Statement对象
        String sql = "create table person(" +
                "id varchar primary key," +
                "name varchar," +
                "age varchar)COLUMN_ENCODED_BYTES=0";
        statement = connection.prepareStatement(sql);
        //4、执行sql
        statement.execute();
    }

    @Test
    public void upsert() throws Exception {
        //1、获取statemnt
        String sql = "upsert into person values(?,?,?)";
        statement = connection.prepareStatement(sql);
        //2、赋值
        statement.setString(1, "1001");
        statement.setString(2, "zhangfei");
        statement.setString(3, "20");
        //3、执行
        statement.executeUpdate();
        //提交
        //connection.commit();//(可以在Before上设置自动提交)

    }

    /**
     * 查询数据
     * @throws Exception
     */
    @Test
    public void query() throws Exception{
        //1、获取statement对象
        String sql = "select * from person where id>?";
        statement = connection.prepareStatement(sql);
        //2、给参数赋值
        statement.setString(1,"1008");
        //3、执行查询
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()){
            String id = resultSet.getString("id");
            String name = resultSet.getString("name");
            String age = resultSet.getString("age");
            System.out.println("id="+id+",name="+name+",age="+age);
        }
        //4、结果展示
    }



}
