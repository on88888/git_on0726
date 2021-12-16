package com.atguigu.es;

import com.atguigu.es.bean.Employee;
import io.searchbox.client.JestClient;
import io.searchbox.client.JestClientFactory;
import io.searchbox.client.config.HttpClientConfig;
import io.searchbox.core.*;

import java.io.IOException;

/**
 * Created by Smexy on 2021/12/4
 *
 *
 *
 *
 *
 */
public class BulkWriteDemo1 {

    public static void main(String[] args) throws IOException {

        JestClientFactory jestClientFactory = new JestClientFactory();

        HttpClientConfig httpClientConfig = (new HttpClientConfig.Builder("http://hadoop102:9200")).build();

        jestClientFactory.setHttpClientConfig(httpClientConfig);

        JestClient jestClient = jestClientFactory.getObject();

        //准备要写的命令
        Employee employee = new Employee("2001", 55, "男", "mike", "坐飞机", 2222.22);

        Index index = new Index.Builder(employee).index("test").type("emps").id("203").build();

        Delete delete = new Delete.Builder("201").index("test").type("emps").build();

        Bulk bulk = new Bulk.Builder().addAction(index).addAction(delete).build();


        // 执行命令
        BulkResult execute = jestClient.execute(bulk);

        System.out.println(execute.getFailedItems().size());


        jestClient.close();


    }
}
