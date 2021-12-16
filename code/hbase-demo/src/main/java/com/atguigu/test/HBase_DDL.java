package com.atguigu.test;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.NamespaceDescriptor;
import org.apache.hadoop.hbase.NamespaceExistException;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;
import org.junit.Test;

import java.io.IOException;

/**
 * @Author 0726
 * @ClassName HBase_DDL.java
 * @createTime 2021年10月10日 14:09
 */
public class HBase_DDL {

    private static Connection connection;
    private static Admin admin;

    /**
     * 配置信息
     * @init
     */
    public static void init () throws Exception{
        //1.创建配置信息并配置
        Configuration conf = HBaseConfiguration.create();
        conf.set("hbase.zookeeper.quorum","hadoop102:2181,hadoop103:2181,hadoop104:2181");
        //2.获取与HBase的连接
        connection = ConnectionFactory.createConnection(conf);
        //获取admin对象
        //3.获取DDL操作对象
        admin = connection.getAdmin();
    }

    /**
     * 关闭资源
     * @close
     */
    public static void close() throws Exception{
        //关闭连接
        admin.close();
        connection.close();
    }


    public static void main(String[] args) throws Exception {

/*      //TODO 判断表是否存在
        boolean tableExist = isTableExist("bigdata2:person");
        System.out.println("tableExist = " + tableExist);*/

/*      //TODO 创建表
        createTable("bigdata2:person3", "f1","f2");*/

/*      //TODO 删除表
        dropTable("bigdata2:person4");*/

        //TODO 创建命名空间
        createNameSpace("bigdata");

    }


    //TODO 判断表是否存在
    public static boolean isTableExist(String tableName) throws Exception {

        //调用配置信息的方法
        init();
        //4.判断表是否存在操作
        boolean exists = admin.tableExists(TableName.valueOf(tableName));
/*        //查看指定命名空间的表
        TableName[] tableNames = admin.listTableNamesByNamespace("bigdata2");
        for(TableName table: tableNames){
//            System.out.println(tableName.getNameAsString());
            System.out.println(new String(table.getName()));
        }*/
        //调用关闭资源的方法
        close();
        //6.返回结果
        return exists;
    }

    //TODO 创建表
    public static void createTable(String tableName, String... cfs) throws Exception {
        //1.判断是否存在列族信息
        if (cfs.length <= 0) {
            System.out.println("请设置列族信息！");
            return;
        }
        //2.判断表是否存在
        if (isTableExist(tableName)) {
            System.out.println("需要创建的表已存在！");
            return;
        }
        //调用配置信息的方法
        init();
        //6.创建表描述器构造器
        TableDescriptorBuilder tableDescriptorBuilder = TableDescriptorBuilder.newBuilder(TableName.valueOf(tableName));
        //7.循环添加列族信息
        for (String cf : cfs) {
            ColumnFamilyDescriptorBuilder columnFamilyDescriptorBuilder = ColumnFamilyDescriptorBuilder.newBuilder(Bytes.toBytes(cf));
            tableDescriptorBuilder.setColumnFamily(columnFamilyDescriptorBuilder.build());
        }
        //8.执行创建表的操作
        admin.createTable(tableDescriptorBuilder.build());
/*        //查看指定命名空间的表
        TableName[] tableNames = admin.listTableNamesByNamespace("bigdata2");

        for(TableName table: tableNames){
//            System.out.println(tableName.getNameAsString());
            System.out.println(new String(table.getName()));
        }*/
        //调用关闭资源的方法
        close();
    }

/*    @Test
        //删除表
    public void droptable() throws Exception {

        init();
        TableName tableName = TableName.valueOf("bigdata2:person3");
        admin.disableTable(tableName);
        admin.deleteTable(tableName);

        TableName[] bigdata2s = admin.listTableNamesByNamespace("bigdata2");
        for (TableName bigdata2 : bigdata2s) {
            System.out.println(new String(bigdata2.getName()));
        }
        close();

    }*/

    //TODO 删除表
    public static void dropTable(String tableName) throws Exception {
        //1.判断表是否存在
        if (!isTableExist(tableName)) {
            System.out.println("需要删除的表不存在！");
            return;
        }
        //调用配置信息的方法
        init();
        //5.使表下线
        TableName name = TableName.valueOf(tableName);
        //禁用表
        admin.disableTable(name);
        //6.执行删除表操作
        admin.deleteTable(name);
        //查看指定命名空间的表
        TableName[] tableNames = admin.listTableNamesByNamespace("bigdata2");

        for(TableName table: tableNames){
//            System.out.println(tableName.getNameAsString());
            System.out.println(new String(table.getName()));
        }
        //调用关闭资源的方法
        close();
    }

    //TODO 创建命名空间
    public static void createNameSpace(String ns) throws Exception {
        //调用配置信息的方法
        init();
        //4.创建命名空间描述器
        NamespaceDescriptor namespaceDescriptor = NamespaceDescriptor.create(ns).build();
/*        //查看所有的命名空间
        NamespaceDescriptor[] namespaceDescriptors = admin.listNamespaceDescriptors();

        for(NamespaceDescriptor desc: namespaceDescriptors){
            System.out.println(desc.getName());
        }*/

//        System.out.println("namespaceDescriptor = " + namespaceDescriptor);

        //5.执行创建命名空间操作
        try {
            admin.createNamespace(namespaceDescriptor);
        } catch (NamespaceExistException e) {
            System.out.println("命名空间已存在！");
        } catch (Exception e) {
            e.printStackTrace();
        }
        //调用关闭资源的方法
        close();
    }

}
