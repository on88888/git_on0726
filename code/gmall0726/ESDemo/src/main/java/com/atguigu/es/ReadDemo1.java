package com.atguigu.es;

import io.searchbox.client.JestClient;
import io.searchbox.client.JestClientFactory;
import io.searchbox.client.config.HttpClientConfig;

import java.io.IOException;

/**
 * Created by Smexy on 2021/12/4
 *
 *      通用步骤：
 *
 *              ①创建一个Jest客户端
 *              ②使用客户端连接服务端
 *              ③创建读写的命令
 *                  Action
 *                      读： Search
 *
 *                      写：  Delete
 *                            Index
 *
 *                       批量写: Bulk
 *
 *
 *              ④使用客户端发送读写命令
 *              ⑤关闭客户端
 *
 *       ----------------------------
 *          创建对象：
 *                  new 对象()
 *
 *          在jest中，大量使用了 工厂模式，建造者模式
 *
 *          工厂模式：  mybatis，获取SqlSession
 *                          SqlSessionFactory.getSession()
 *
 *                     JestClient client = JestClientFactory.xxx()
 *
 *
 *          建造者模式: 获取 A对象
 *
 *                      new  ABuilder.xxx().xxx().xxx().build()
 *
 *
 *
 */
public class ReadDemo1 {

    public static void main(String[] args) throws IOException {

        JestClientFactory jestClientFactory = new JestClientFactory();

        HttpClientConfig httpClientConfig = (new HttpClientConfig.Builder("http://hadoop102:9200")).build();

        jestClientFactory.setHttpClientConfig(httpClientConfig);

        JestClient jestClient = jestClientFactory.getObject();

        //准备命令

        // 执行命令




        jestClient.close();


    }
}
