package com.atguigu.test;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.NamespaceDescriptor;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

/**
 * @Author 0726
 * @ClassName HbaseTest.java
 * @createTime 2021年10月11日 10:20
 */
public class HbaseTest {
    private Connection connection;
    private Admin admin;
    @Before
    public void init() throws IOException {
        //1.创建配置信息并配置
        Configuration conf = HBaseConfiguration.create();
        conf.set("hbase.zookeeper.quorum", "hadoop102,hadoop103,hadoop104");
        //2.获取与HBase的连接
        connection = ConnectionFactory.createConnection(conf);
        //3.获取DDL操作对象
        admin = connection.getAdmin();
    }

    @After
    public void close() throws IOException {
        admin.close();
        connection.close();
    }


    @Test
    public void createNamespace() throws IOException {

        NamespaceDescriptor bigdata = NamespaceDescriptor.create("bigdata").build();
        admin.createNamespace(bigdata);

    }


}
