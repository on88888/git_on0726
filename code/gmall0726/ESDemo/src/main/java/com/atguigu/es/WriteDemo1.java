package com.atguigu.es;

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
public class WriteDemo1 {

    public static void main(String[] args) throws IOException {

        JestClientFactory jestClientFactory = new JestClientFactory();

        HttpClientConfig httpClientConfig = (new HttpClientConfig.Builder("http://hadoop102:9200")).build();

        jestClientFactory.setHttpClientConfig(httpClientConfig);

        JestClient jestClient = jestClientFactory.getObject();

        //准备命令
        String str="{\n" +
                "  \"empid\": 1015,\n" +
                "  \"age\": 100,\n" +
                "  \"balance\": 3400,\n" +
                "  \"name\": \"Nick\",\n" +
                "  \"gender\": \"男\",\n" +
                "  \"hobby\": \"坐飞机,购物\"\n" +
                "}";
        // 添加一个员工  POST /test/emps/201
        Index index = new Index.Builder(str).index("test").type("emps").id("201").build();


        // 执行命令
        DocumentResult execute = jestClient.execute(index);

        System.out.println(execute.getResponseCode());


        jestClient.close();


    }
}
