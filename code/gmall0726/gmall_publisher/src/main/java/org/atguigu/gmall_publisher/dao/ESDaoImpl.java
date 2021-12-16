package org.atguigu.gmall_publisher.dao;

import com.alibaba.fastjson.JSONObject;
import io.searchbox.client.JestClient;
import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;
import io.searchbox.core.search.aggregation.MetricAggregation;
import io.searchbox.core.search.aggregation.TermsAggregation;
import org.atguigu.gmall_publisher.bean.Option;
import org.atguigu.gmall_publisher.bean.SaleDetail;
import org.atguigu.gmall_publisher.bean.Stat;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.TermsBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Smexy on 2021/12/6
 *
 *  {
 *      total:xxx,
 *      stats: [
 *                  {
 *                      Stat:{
 *                          titile:xxx,
 *                          Options:[
 *                              {option:{
 *                                  name:xxx
 *                                  value:
 *
 *                              }}
 *
 *                          ]
 *
 *
 *
 *                      }
 *                  },
 *                  {}
 *
 *              ]
 *      detail: [
 *              SaleDetail...
 *              ]
 *
 *
 *  }
 */
@Repository
public class ESDaoImpl implements ESDao {

    @Autowired
    private JestClient jestClient;


    /*
        GET /gmall2021_sale_detail2021-12-06/_search
            {
              "query": {
                "match": {
                  "sku_name": "手机"
                }
              },
              "aggs": {
                "gendercount": {
                  "terms": {
                    "field": "user_gender",
                    "size": 2
                  }
                },
                "agecount": {
                  "terms": {
                    "field": "user_age",
                    "size": 150
                  }
                }
              },
              "from": 20,
              "size": 10
            }

            date:   gmall2021_sale_detail+date
     */
    @Override
    public JSONObject getSaleDetail(String date, String keyword, Integer startpage, Integer size) throws IOException {

        JSONObject jsonObject = new JSONObject();

        SearchResult searchResult = queryES(date, keyword, startpage, size);

        List<SaleDetail> saleDetails = getDetail(searchResult);

        ArrayList<Stat> stats = new ArrayList<>();

        stats.add(getAgeStat(searchResult));
        stats.add(getGenderStat(searchResult));

        jsonObject.put("total",searchResult.getTotal());
        jsonObject.put("stats",stats);
        jsonObject.put("detail",saleDetails);

        return jsonObject;
    }

    public Stat getGenderStat(SearchResult searchResult){

        MetricAggregation aggregations = searchResult.getAggregations();

        TermsAggregation agecount = aggregations.getTermsAggregation("gendercount");

        double man = 0d;
        double woman = 0d;

        double total = 0d;

        List<TermsAggregation.Entry> buckets = agecount.getBuckets();

        for (TermsAggregation.Entry bucket : buckets) {

            if ("F".equals(bucket.getKey())){

                woman = bucket.getCount();

            }else{

                man = bucket.getCount();
            }

        }

        total = woman + man ;

        DecimalFormat decimalFormat = new DecimalFormat("###.##%");

        double manPercent = man /  total;
        double womanPercent = 1 - manPercent;

        ArrayList<Option> options = new ArrayList<>();

        options.add(new Option("男",decimalFormat.format(manPercent)));
        options.add(new Option("女",decimalFormat.format(womanPercent)));

        return new Stat("用户性别占比",options);


    }

    public Stat getAgeStat(SearchResult searchResult){

        MetricAggregation aggregations = searchResult.getAggregations();

        TermsAggregation agecount = aggregations.getTermsAggregation("agecount");

        double agelt20 = 0d;
        double age20to30 = 0d;
        double agegt30 = 0d;

        double total = 0d;

        List<TermsAggregation.Entry> buckets = agecount.getBuckets();

        for (TermsAggregation.Entry bucket : buckets) {

            if (Double.parseDouble(bucket.getKey()) < 20){

                agelt20 += bucket.getCount();

            }else if(Double.parseDouble(bucket.getKey()) >= 30){

                agegt30 += bucket.getCount();
            }else{

                age20to30 += bucket.getCount();
            }

        }

        total = age20to30 + agegt30 + agelt20;

        DecimalFormat decimalFormat = new DecimalFormat("###.##%");

        double agelt20Percent = agelt20 /  total;
        double age20to30Percent = age20to30 /  total;
        double agegt30Percent = 1 - agelt20Percent - age20to30Percent;

        ArrayList<Option> options = new ArrayList<>();

        options.add(new Option("20岁到30岁",decimalFormat.format(age20to30Percent)));
        options.add(new Option("30岁及30岁以上",decimalFormat.format(agegt30Percent)));
        options.add(new Option("20岁以下",decimalFormat.format(agelt20Percent)));

        return new Stat("用户年龄占比",options);


    }

    public List<SaleDetail> getDetail(SearchResult searchResult){

        ArrayList<SaleDetail> saleDetails = new ArrayList<>();

        List<SearchResult.Hit<SaleDetail, Void>> hits = searchResult.getHits(SaleDetail.class);

        for (SearchResult.Hit<SaleDetail, Void> hit : hits) {

            SaleDetail saleDetail = hit.source;

            saleDetail.setEs_metadata_id(hit.id);

            saleDetails.add(saleDetail);

        }

        return saleDetails;

    }


    public SearchResult queryES(String date, String keyword, Integer startpage, Integer size) throws IOException{

        String indexName = "gmall2021_sale_detail" + date;

        int from = (startpage - 1) * size;


        MatchQueryBuilder matchQueryBuilder = new MatchQueryBuilder("sku_name", keyword);

        TermsBuilder termsBuilder1 = AggregationBuilders.terms("gendercount").size(2).field("user_gender");
        TermsBuilder termsBuilder2 = AggregationBuilders.terms("agecount").size(150).field("user_age");

        String queryString = new SearchSourceBuilder().query(matchQueryBuilder).aggregation(termsBuilder1).aggregation(termsBuilder2).size(size).from(from).toString();

        Search search = new Search.Builder(queryString).addIndex(indexName).addType("_doc").build();

        SearchResult searchResult = jestClient.execute(search);

        return searchResult;

    }
}
