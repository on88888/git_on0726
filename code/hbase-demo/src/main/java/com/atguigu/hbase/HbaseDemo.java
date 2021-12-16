package com.atguigu.hbase;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.*;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class HbaseDemo {

    /**
     * 	1、namespace相关
     * 		1、创建namespace: create_namespace '命名空间名称'
     * 		2、查看所有的命名空间： list_namespace
     * 		3、查看命名空间下所有表: list_namespace_tables '命名空间名称'
     * 		4、删除命名空间[删除命名的时候,命名空间下不能有表,如果有表不能删除命名空间]: drop_namespace '命名空间名称'
     * 	2、表相关
     * 		1、创建表[版本默认为1]: create '表名','列簇名1','列簇2',..
     * 		2、创建表,指定版本号:  create '表名',{NAME=>'列簇名',VERSIONS=>版本数},...
     * 		3、修改表: alter '表',{NAME=>'列簇名',VERSIONS=>版本数},..
     * 		4、删除表:
     * 			1、禁用表: disable '表名'
     * 			2、删除表: drop '表名'
     * 		5、启动表: enable '表名'
     * 		6、查看所有表: list
     * 	3、数据相关
     * 		1、插入/修改数据: put '命名空间名称:表名',rowkey,'列簇名:列限定符名称',值
     * 		2、查询数据
     * 			1、根据rowkey查询单条数据:
     * 				1、查询整行数据: get '命名空间名称:表名',rowkey
     * 				2、查询某个列的数据: get '命名空间名称:表名',rowkey,'列簇名:列限定符名称'
     * 				3、查看某个列簇的数据: get '命名空间名称:表名',rowkey,'列簇名'
     * 				4、查看某个时间戳的数据: get '命名空间名称:表名',rowkey,{COLUMN=>'列簇名:列限定符名称',TIMESTAMP=>时间戳}
     * 				5、查看多个版本的数据: get '命名空间名称:表名',rowkey,{COLUMN=>'列簇名:列限定符名称',VERSIONS=>版本数}
     * 			2、扫描表
     * 				1、查询整表数据: scan '命名空间名称:表名'
     * 				2、查询查询某个列的数据: scan '命名空间名称:表名',{COLUMNS=>'列簇名:列限定符名称'}
     * 				3、查看某个列簇的数据： scan '命名空间名称:表名',{COLUMNS=>'列簇名'}
     * 				4、查看多个版本的数据：scan '命名空间名称:表名',{COLUMNS=>'列簇名',VERSIONS=>版本数}
     * 				5、查看指定rowkey范围段的数据: scan '命名空间名称:表名',{STARTKEY=>起始rowkey,STOPKEY=>结束rowkey} [查询结果不包含stopkey]
     * 		3、删除数据
     * 			1、delete： 只能删除单个cell
     * 				delete '命名空间名称:表名',rowkey,'列簇名:列限定符名称'
     * 			2、deleteall： 可以删除单个cell也可以删除整行数据
     * 				1、删除单个cell: deleteall '命名空间名称:表名',rowkey,'列簇名:列限定符名称'
     * 				2、删除整行: deleteall '命名空间名称:表名',rowkey
     * 		4、统计表的行数: count '命名空间名称:表名'
     * 		5、清空表数据: truncate '命名空间名称:表名'
     */

    private Admin admin;
    private Connection connection;
    @Before
    public void init () throws Exception{

        //1.创建配置信息并配置
        Configuration conf = HBaseConfiguration.create();
        conf.set("hbase.zookeeper.quorum","hadoop102:2181,hadoop103:2181,hadoop104:2181");
        //2.获取与HBase的连接
         connection = ConnectionFactory.createConnection(conf);
        //获取admin对象
        //3.获取DDL操作对象
         admin = connection.getAdmin();
    }
    @After
    public void close() throws Exception{

        //关闭连接
        admin.close();
        connection.close();
    }

    /**
     * 创建命名空间
     * @throws IOException
     */
    @Test
    public void createNamespace() throws IOException {

        //描述命名空间
        NamespaceDescriptor bigdata2 = NamespaceDescriptor.create("bigdata2").build();
        //创建
        admin.createNamespace(bigdata2);

    }

    /**
     * 查看所有的命名空间
     * @throws Exception
     */
    @Test
    public void listNamespace() throws Exception{

        NamespaceDescriptor[] namespaceDescriptors = admin.listNamespaceDescriptors();

        for(NamespaceDescriptor desc: namespaceDescriptors){
            System.out.println(desc.getName());
        }
    }

    /**
     * 查看指定命名空间的表
     * @throws Exception
     */
    @Test
    public void listNamespaceTables() throws Exception{

        TableName[] tableNames = admin.listTableNamesByNamespace("bigdata2");

        for(TableName tableName: tableNames){
//            System.out.println(tableName.getNameAsString());
            System.out.println(new String(tableName.getName()));
        }
    }

    /**
     * 删除命名空间
     */
    @Test
    public void dropNamespace() throws Exception{

        //删除命名空间下所有表
            //获取命名空间下所有表
            TableName[] tables = admin.listTableNamesByNamespace("bigdata2");

            for(TableName table: tables){
                //禁用表
                admin.disableTable(table);
                //删除表
                admin.deleteTable(table);
                //启动表
                //admin.enableTable(table);
            }

        //删除命名空间
        admin.deleteNamespace("bigdata2");
    }

    /**
     * 创建表
     * @throws Exception
     */
    @Test
    public void createTable() throws Exception{

        //描述列簇
        ColumnFamilyDescriptor f1 = ColumnFamilyDescriptorBuilder.newBuilder("f1".getBytes()).setMaxVersions(3).build();
        ColumnFamilyDescriptor f2 = ColumnFamilyDescriptorBuilder.newBuilder("f2".getBytes()).setMaxVersions(3).build();
        //描述表
        TableDescriptor build = TableDescriptorBuilder.newBuilder(TableName.valueOf("bigdata2:person3"))
                .setColumnFamily(f1)
                .setColumnFamily(f2)
                .build();
        //创建
//        admin.createTable(build);
        byte[][] splitkeys = { "1001".getBytes(),"1002".getBytes(),"1003".getBytes() };
        admin.createTable(build,splitkeys);
//        byte[][] splitkeys = { "aa".getBytes(),"bb".getBytes(),"cc".getBytes() };
//        admin.createTable(build,splitkeys);

//        admin.createTable(build,"1".getBytes(),"3".getBytes(),5);
    }

    /**
     * 修改表
     * @throws Exception
     */
    @Test
    public void alterTable() throws Exception{
        //描述列簇
        ColumnFamilyDescriptor f1 = ColumnFamilyDescriptorBuilder.newBuilder("f1".getBytes()).setMaxVersions(3).build();
        //ColumnFamilyDescriptor f2 = ColumnFamilyDescriptorBuilder.newBuilder("f2".getBytes()).setMaxVersions(3).build();
        //描述表
        TableDescriptor build = TableDescriptorBuilder.newBuilder(TableName.valueOf("bigdata2:person"))
                .setColumnFamily(f1)
                //.setColumnFamily(f2)
                .build();
        admin.modifyTable(build);

    }

    /**
     * 插入数据
     * @throws Exception
     */
    @Test
    public void put() throws Exception{

        //获取Table对象
        Table table = connection.getTable(TableName.valueOf("bigdata2:person3"));
        // put '命名空间名称:表名',rowkey,'列簇名:列限定符名称',值
        //封装数据
        Put put = new Put("1001".getBytes());

        put.addColumn("f1".getBytes(),"name".getBytes(),"lisi".getBytes());
        put.addColumn("f1".getBytes(),"age".getBytes(), Bytes.toBytes(20));
        put.addColumn("f1".getBytes(),"address".getBytes(),"shenzhen".getBytes());
        //插入
        table.put(put);
        //关闭table对象
        table.close();

    }

    /**
     * 批量插入
     * @throws Exception
     */
    @Test
    public void putList() throws Exception{
        //获取Table对象
        Table table = connection.getTable(TableName.valueOf("bigdata2:person3"));
        // put '命名空间名称:表名',rowkey,'列簇名:列限定符名称',值

        //封装数据
        List<Put> list = new ArrayList<Put>();
        Put put = null;

        for(int i=0;i<=10;i++){
            put = new Put( ("100"+i).getBytes() );
            put.addColumn("f1".getBytes(),"name".getBytes(),("lisi-"+i).getBytes());
            put.addColumn("f1".getBytes(),"age".getBytes(),Bytes.toBytes(20+i));
            put.addColumn("f1".getBytes(),"address".getBytes(),("shenzhe-"+i).getBytes());
            list.add(put);
        }
        //插入
        table.put(list);
        //关闭
        table.close();
    }

    /**
     * 根据rowkey查询数据
     *              1、查询整行数据: get '命名空间名称:表名',rowkey
     * 				2、查询某个列的数据: get '命名空间名称:表名',rowkey,'列簇名:列限定符名称'
     * 				3、查看某个列簇的数据: get '命名空间名称:表名',rowkey,'列簇名'
     * 				4、查看某个时间戳的数据: get '命名空间名称:表名',rowkey,{COLUMN=>'列簇名:列限定符名称',TIMESTAMP=>时间戳}
     * 				5、查看多个版本的数据: get '命名空间名称:表名',rowkey,{COLUMN=>'列簇名:列限定符名称',VERSIONS=>版本数}
     * @throws Exception
     */
    @Test
    public void get()  throws Exception{
        //获取表对象
        Table table = connection.getTable(TableName.valueOf("bigdata2:person2"));
        //封装数据
        //1、查询整行数据: get '命名空间名称:表名',rowkey
        Get get = new Get("1001".getBytes());
        //2、查询某个列的数据: get '命名空间名称:表名',rowkey,'列簇名:列限定符名称'
        //Get get = new Get("1001".getBytes());
        //get.addColumn("f1".getBytes(),"name".getBytes());
        //3、查看某个列簇的数据: get '命名空间名称:表名',rowkey,'列簇名'
        //Get get = new Get("1001".getBytes());
        //get.addFamily("f1".getBytes());
        //4、查看某个时间戳的数据: get '命名空间名称:表名',rowkey,{COLUMN=>'列簇名:列限定符名称',TIMESTAMP=>时间戳}
        //Get get = new Get("1001".getBytes());
        get.addColumn("f1".getBytes(),"name".getBytes());
        //get.setTimestamp(1620887027129L);
        //5、查询多个版本数据
        get.readVersions(3);
        //查询
        Result result = table.get(get);
        //结果展示
        List<Cell> cells = result.listCells();
        for(Cell cell:cells){

            //获取rowkey
            String rowkey = new String(cell.getRowArray(), cell.getRowOffset(), cell.getRowLength());
            //获取列簇
            String family = new String(cell.getFamilyArray(), cell.getFamilyOffset(), cell.getFamilyLength());
            //获取列限定符
            String qualifier = new String(cell.getQualifierArray(), cell.getQualifierOffset(), cell.getQualifierLength());
            //获取值
            if(family.equals("f1") && qualifier.equals("age") ){

                int value = Bytes.toInt(cell.getValueArray(), cell.getValueOffset(), cell.getValueLength());
                System.out.println("rowkey="+rowkey+",family="+family+",qualifier="+qualifier+",value="+value);
            }else{

                String value = new String(cell.getValueArray(), cell.getValueOffset(), cell.getValueLength());
                System.out.println("rowkey="+rowkey+",family="+family+",qualifier="+qualifier+",value="+value);
            }



        }
        //关闭
        table.close();
    }

    /**
     * 扫描数据
     * 				1、查询整表数据: scan '命名空间名称:表名'
     * 				2、查询查询某个列的数据: scan '命名空间名称:表名',{COLUMNS=>'列簇名:列限定符名称'}
     * 				3、查看某个列簇的数据： scan '命名空间名称:表名',{COLUMNS=>'列簇名'}
     * 				4、查看多个版本的数据：scan '命名空间名称:表名',{COLUMNS=>'列簇名',VERSIONS=>版本数}
     * 				5、查看指定rowkey范围段的数据: scan '命名空间名称:表名',{STARTKEY=>起始rowkey,STOPKEY=>结束rowkey} [查询结果不包含stopkey]
     * @throws Exception
     */
    @Test
    public void scan() throws Exception{

        //获取表对象
        Table table = connection.getTable(TableName.valueOf("bigdata2:person3"));
        //封装数据
        //1、查询整表
        Scan scan = new Scan();
        //2、查询查询某个列的数据: scan '命名空间名称:表名',{COLUMNS=>'列簇名:列限定符名称'}
        //scan.addColumn("f1".getBytes(),"name".getBytes());
        //3、查看某个列簇的数据： scan '命名空间名称:表名',{COLUMNS=>'列簇名'}
        scan.addFamily("f1".getBytes());
        //4、查看多个版本的数据：scan '命名空间名称:表名',{COLUMNS=>'列簇名',VERSIONS=>版本数}
        scan.readVersions(3);
        //5、查看指定rowkey范围段的数据: scan '命名空间名称:表名',{STARTKEY=>起始rowkey,STOPKEY=>结束rowkey} [查询结果不包含stopkey]
        //scan.withStartRow("1001".getBytes(),true);
        //scan.withStopRow("1002".getBytes(),true);
        //查询
        ResultScanner scanner = table.getScanner(scan);
        //结果展示
        Iterator<Result> it = scanner.iterator();
        while (it.hasNext()){

            Result row = it.next();

            List<Cell> cells = row.listCells();

            for(Cell cell:cells){

                //获取rowkey
                String rowkey = new String(CellUtil.cloneRow(cell));
                //获取列簇
                String family = new String(CellUtil.cloneFamily(cell));
                //获取列限定符
                String qualifier = new String(CellUtil.cloneQualifier(cell));
                //获取值
                if(family.equals("f1") && qualifier.equals("age") ){

                    int value = Bytes.toInt(CellUtil.cloneValue(cell));
                    System.out.println("rowkey="+rowkey+",family="+family+",qualifier="+qualifier+",value="+value);
                }else{

                    String value = new String(CellUtil.cloneValue(cell));
                    System.out.println("rowkey="+rowkey+",family="+family+",qualifier="+qualifier+",value="+value);
                }



            }
        }
        //关闭
        table.close();
    }

    /**
     * 删除数据
     * @throws Exception
     */
    @Test
    public void delete() throws Exception{

        //获取表对象
        Table table = connection.getTable(TableName.valueOf("bigdata2:person"));
        //封装数据
        //删除整行
        Delete delete = new Delete("10010".getBytes());
        //删除单个Cell
        //delete.addColumn("f1".getBytes(),"name".getBytes());
        //删除列簇
        delete.addFamily("f1".getBytes());
        //删除
        table.delete(delete);

        //关闭
        table.close();
    }

    /**
     * 清空表
     * @throws Exception
     */
    @Test
    public void truncate() throws Exception{

        if( admin.tableExists(TableName.valueOf("bigdata2:person"))){
            //禁用表
            admin.disableTable(TableName.valueOf("bigdata2:person"));
            //清空表
            admin.truncateTable(TableName.valueOf("bigdata2:person"),false);
        }
    }
}
