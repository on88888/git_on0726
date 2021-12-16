package com.atguigu.es;

import com.atguigu.es.bean.Employee;
import io.searchbox.client.JestClient;
import io.searchbox.client.JestClientFactory;
import io.searchbox.client.config.HttpClientConfig;
import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;
import io.searchbox.core.search.aggregation.AvgAggregation;
import io.searchbox.core.search.aggregation.MetricAggregation;
import io.searchbox.core.search.aggregation.TermsAggregation;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.avg.AvgAggregationBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;

import java.io.IOException;
import java.util.List;

/**
 * Created by Smexy on 2021/12/4
 *
 *
 *
 *
 */
public class ReadDemo3 {

    public static void main(String[] args) throws IOException {

        JestClientFactory jestClientFactory = new JestClientFactory();

        HttpClientConfig httpClientConfig = (new HttpClientConfig.Builder("http://hadoop102:9200")).build();

        jestClientFactory.setHttpClientConfig(httpClientConfig);

        JestClient jestClient = jestClientFactory.getObject();

        //准备命令  创建一个对象，让对象生成查询的string
        // match:{}
        MatchQueryBuilder matchQueryBuilder = new MatchQueryBuilder("hobby", "购物");

        // gendercount:{}
        TermsAggregationBuilder builder1 = AggregationBuilders.terms("gendercount").size(2).field("gender.keyword");

        // avgage:{}
        AvgAggregationBuilder builder2 = AggregationBuilders.avg("avgage").field("age");

        // 最外层{}:
        // java语法糖 ：  A.xxx() 返回 A
        String str = new SearchSourceBuilder().query(matchQueryBuilder).aggregation(builder1).aggregation(builder2).toString();


        Search search = new Search.Builder(str).addIndex("test").addType("emps").build();

        // 执行命令
        SearchResult searchResult = jestClient.execute(search);

        System.out.println("total:"+searchResult.getTotal());
        System.out.println("max score:"+searchResult.getMaxScore());

        List<SearchResult.Hit<Employee, Void>> hits = searchResult.getHits(Employee.class);

        for (SearchResult.Hit<Employee, Void> hit : hits) {

            System.out.println("_score"+hit.score);
            System.out.println("_type"+hit.type);
            System.out.println("_index"+hit.index);
            System.out.println("_id"+hit.id);
            System.out.println("_source"+hit.source);

        }

        // aggs
        MetricAggregation aggregations = searchResult.getAggregations();

        // 遍历gendercount
        TermsAggregation gendercount = aggregations.getTermsAggregation("gendercount");

        List<TermsAggregation.Entry> buckets = gendercount.getBuckets();

        for (TermsAggregation.Entry bucket : buckets) {

            System.out.println(bucket.getKey()+":"+bucket.getCount());

        }

        //遍历avgcount
        AvgAggregation avgage = aggregations.getAvgAggregation("avgage");

        System.out.println(avgage.getAvg());


        jestClient.close();


    }
}
