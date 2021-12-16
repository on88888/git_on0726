package test;
import org.apache.phoenix.queryserver.client.Driver;
import org.apache.phoenix.queryserver.client.ThinClientUtil;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.*;
import java.util.Properties;

/**
 * @Author 0726
 * @ClassName TT.java
 * @createTime 2021年10月13日 14:12
 */
public class TT {


        private Connection connection = null;
        private PreparedStatement statement = null;

        @Before
        public void init() throws Exception{
            //1、加载驱动
            Class.forName("org.apache.phoenix.queryserver.client.Driver");
            //2、获取connection链接
            String url = ThinClientUtil.getConnectionUrl("hadoop102", 8765);

            //客户端开启namespace映射
            Properties props = new Properties();
            props.setProperty("phoenix.schema.isNamespaceMappingEnabled","true");
            props.setProperty("phoenix.schema.mapSystemTablesToNamespace","true");

            connection = DriverManager.getConnection(url,props);
            //设置自动提交
            connection.setAutoCommit(true);
        }

        /**
         * 创建表
         * @throws Exception
         */
        @Test
        public void createTable() throws Exception{


            //3、创建Statement对象
            String sql = "create table person2(" +
                    "id varchar primary key," +
                    "name varchar," +
                    "age varchar)COLUMN_ENCODED_BYTES=0";
            statement = connection.prepareStatement(sql);
            //4、执行sql
            statement.execute();
        }

        /**
         * 插入数据
         * @throws Exception
         */
        @Test
        public void upsert() throws Exception{
            for (int i = 0; i <=2500; i++) {
                //1、获取statemnt
                String sql = "upsert into person2 values(?,?,?)";
                statement = connection.prepareStatement(sql);
                //2、赋值
                statement.setString(1,"1001"+i);
                statement.setString(2,"zhangsan"+i);
                statement.setString(3,(20+i)+"");
                //3、执行
                statement.executeUpdate();
            }

            //提交
            //connection.commit();//(可以在Before上设置自动提交)
        }

        /**
         * 批次插入
         * @throws Exception
         */
        @Test
        public void upsertBatch() throws Exception{
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
/*            int i=130;
            while (i<=250){
                statement.setString(1,"100"+i);
                statement.setString(2,"zhangsan"+i);
                statement.setString(3,"2"+i);
                statement.addBatch();
                if(i%5==0){
                    //提交一个批次
                    statement.executeBatch();
                    statement.clearBatch();
                    connection.commit();
                }
                i=++i;
            }*/
            //提交最后一个不满的批次
            statement.executeBatch();
            connection.commit();

        }

        /**
         * 查询数据
         * @throws Exception
         */
        @Test
        public void query() throws Exception{
            //1、获取statement对象
            String sql = "select * from person2 where id>?";
            statement = connection.prepareStatement(sql);
            //2、给参数赋值
            statement.setString(1,"1001");
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

        /**
         * 删除数据
         * @throws Exception
         */
        @Test
        public void delete() throws Exception {

            String sql = "delete from person2 where id=?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, "1001");
            statement.executeUpdate();

        }
    /**
     * 删除表
     * @throws Exception
     */
    @Test
        public void dropTable() throws Exception{

            String sql = "drop table person";
            statement = connection.prepareStatement(sql);

            statement.execute();
        }
        @After
        public void close() throws Exception{
            //5、关闭
            if(statement!=null)
                statement.close();
            if(connection!=null)
                connection.close();
        }

    }
