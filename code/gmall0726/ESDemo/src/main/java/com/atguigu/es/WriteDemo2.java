package com.atguigu.es;

import com.atguigu.es.bean.Employee;
import io.searchbox.client.JestClient;
import io.searchbox.client.JestClientFactory;
import io.searchbox.client.config.HttpClientConfig;
import io.searchbox.core.DocumentResult;
import io.searchbox.core.Index;

import java.io.IOException;

/**
 * Created by Smexy on 2021/12/4
 *
 *
 *
 *
 *
 */
public class WriteDemo2 {

    public static void main(String[] args) throws IOException {

        JestClientFactory jestClientFactory = new JestClientFactory();

        HttpClientConfig httpClientConfig = (new HttpClientConfig.Builder("http://hadoop102:9200")).build();

        jestClientFactory.setHttpClientConfig(httpClientConfig);

        JestClient jestClient = jestClientFactory.getObject();

        //准备命令
        // 添加一个员工  POST /test/emps/201
        Employee employee = new Employee("2001", 55, "男", "mike", "坐飞机", 2222.22);

        Index index = new Index.Builder(employee).index("test").type("emps").id("202").build();


        // 执行命令
        DocumentResult execute = jestClient.execute(index);

        System.out.println(execute.getResponseCode());


        jestClient.close();


    }
}
