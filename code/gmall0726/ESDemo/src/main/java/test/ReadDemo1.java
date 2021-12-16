package test;

/**
 * @Author 0726
 * @ClassName ReadDemo1
 * @createTime 2021年12月04日 11:34
 */

import io.searchbox.client.JestClient;
import io.searchbox.client.JestClientFactory;
import io.searchbox.client.config.HttpClientConfig;
import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;

import java.io.IOException;

/**
 *       通用步骤：
 *
 *               ①创建一个Jest客户端
 *               ②使用客户端连接服务端
 *               ③创建读写的命令
 *                   Action
 *                       读： Search
 *
 *                       写：  Delete
 *                             Index
 *
 *                        批量写: Bulk
 *
 *
 *               ④使用客户端发送读写命令
 *               ⑤关闭客户端
 */
public class ReadDemo1 {

    public static void main(String[] args) throws IOException {
        JestClientFactory jestClientFactory = new JestClientFactory();

        HttpClientConfig httpClientConfig = (new HttpClientConfig.Builder("http://hadoop102:9200")).build();

        jestClientFactory.setHttpClientConfig(httpClientConfig);

        JestClient jestClient = jestClientFactory.getObject();
        //准备命令

        //执行命令

        jestClient.close();
    }

}





























