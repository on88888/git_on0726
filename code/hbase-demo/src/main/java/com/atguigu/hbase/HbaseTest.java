package com.atguigu.hbase;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.NamespaceDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

public class HbaseTest {
    private Connection connection;
    private Admin admin;

    @Before
    public void init() throws IOException {
        //1、创建hbase客户端
        Configuration conf = HBaseConfiguration.create();
        conf.set("hbase.zookeeper.quorum","hadoop102:2181,hadoop103:2181,hadoop104:2181");

        connection = ConnectionFactory.createConnection(conf);

        //2、创建admin对象
        admin = connection.getAdmin();
    }

    @After
    public void close() throws IOException {
        if(admin!=null)
            admin.close();
        if(connection!=null)
            connection.close();
    }

    /**
     * 创建命名空间
     * @throws IOException
     */
    @Test
    public void createNamespace() throws IOException {


        //3、封装命名空间数据信息
        NamespaceDescriptor big = NamespaceDescriptor.create("big").build();
        //4、创建
        admin.createNamespace(big);


    }

    /**
     * 查询所有的命名空间
     * @throws IOException
     */
    @Test
    public void listNamespace() throws IOException {

        NamespaceDescriptor[] namespaces = admin.listNamespaceDescriptors();

        for(NamespaceDescriptor desc: namespaces){
            System.out.println(desc.getName());
        }
    }

    /**
     * 查看命名空间所有表
     * @throws Exception
     */
    @Test
    public void listNamespaceTables() throws Exception{

        TableName[] tableNames = admin.listTableNamesByNamespace("default");

        for(TableName table: tableNames){
            System.out.println( new String(table.getName()) );
        }
    }

    /**
     * 修改命名空间
     * @throws Exception
     */
    @Test
    public void alterNamespace() throws Exception{

        NamespaceDescriptor big = NamespaceDescriptor.create("big").addConfiguration("COMMENT", "xxx").build();
        admin.modifyNamespace(big);
    }

    /**
     * 删除命名空间
     * @throws Exception
     */
    @Test
    public void dropNamespace() throws Exception{

        admin.deleteNamespace("big");
    }

    @Test
    public void createTable() throws Exception{

        //描述列簇
        ColumnFamilyDescriptor f1 = ColumnFamilyDescriptorBuilder.newBuilder("f1".getBytes())
                .setMaxVersions(3)
                .build();
        ColumnFamilyDescriptor f2 = ColumnFamilyDescriptorBuilder.newBuilder("f2".getBytes())
                .setMaxVersions(2)
                .build();

        //描述表
        TableDescriptor tableDescriptor = TableDescriptorBuilder.newBuilder(TableName.valueOf("bigdata:person"))
                .setColumnFamily(f1)
                .setColumnFamily(f2)
                .build();

        admin.createTable(tableDescriptor);
    }

    /**
     * 修改表
     * @throws Exception
     */
    @Test
    public void alterTable() throws Exception{
        //描述列簇
        ColumnFamilyDescriptor f1 = ColumnFamilyDescriptorBuilder.newBuilder("f1".getBytes())
                .setMaxVersions(1)
                .build();
       ColumnFamilyDescriptor f2 = ColumnFamilyDescriptorBuilder.newBuilder("f2".getBytes())
                .setMaxVersions(2)
                .build();

        //描述表
        TableDescriptor tableDescriptor = TableDescriptorBuilder.newBuilder(TableName.valueOf("bigdata:person"))
                .setColumnFamily(f1)
                .setColumnFamily(f2)
                .build();
        admin.modifyTable(tableDescriptor);
    }

    @Test
    public void dropTable() throws Exception{

        //禁用表
        admin.disableTable(TableName.valueOf("bigdata2:person2"));
        //删除
        admin.deleteTable(TableName.valueOf("bigdata2:person2"));
    }


}
