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
import io.searchbox.core.search.aggregation.MetricAggregation;
import io.searchbox.core.search.aggregation.TermsAggregation;
import test.bean.Employee;

import java.io.IOException;
import java.util.List;

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
public class ReadDemo2 {

    public static void main(String[] args) throws IOException {
        JestClientFactory jestClientFactory = new JestClientFactory();

        HttpClientConfig httpClientConfig = (new HttpClientConfig.Builder("http://hadoop102:9200")).build();

        jestClientFactory.setHttpClientConfig(httpClientConfig);

        JestClient jestClient = jestClientFactory.getObject();
        //准备命令
        String str = "{\n" +
                "  \"query\": {\n" +
                "    \"match_all\": {}\n" +
                "  },\n" +
                "  \"aggs\": {\n" +
                "    \"gendercount\": {\n" +
                "      \"terms\": {\n" +
                "        \"field\": \"gender.keyword\",\n" +
                "        \"size\": 2\n" +
                "      }\n" +
                "    }\n" +
                "  }\n" +
                "}";
        Search search = new Search.Builder(str).addIndex("test").addType("emps").build();

        //执行命令
        SearchResult searchResult = jestClient.execute(search);
        System.out.println("total = " + searchResult.getTotal());
        System.out.println("max_score = " + searchResult.getMaxScore());

        List<SearchResult.Hit<Employee, Void>> hits = searchResult.getHits(Employee.class);

        for (SearchResult.Hit<Employee, Void> hit : hits) {
            System.out.println("_score" + hit.score);
            System.out.println("_type" + hit.type);
            System.out.println("_index" + hit.index);
            System.out.println("_id" + hit.id);
            System.out.println("_source" + hit.source);
        }

        //aggs
        MetricAggregation aggregations = searchResult.getAggregations();

        TermsAggregation gendercount = aggregations.getTermsAggregation("gendercount");

        List<TermsAggregation.Entry> buckets = gendercount.getBuckets();

        for (TermsAggregation.Entry bucket : buckets) {
            System.out.println(bucket.getKey() + ":" + bucket.getCount());
        }

        jestClient.close();
    }

}





























