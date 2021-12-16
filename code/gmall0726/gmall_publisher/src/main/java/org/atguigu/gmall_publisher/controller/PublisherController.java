package org.atguigu.gmall_publisher.controller;

import com.alibaba.fastjson.JSONObject;
import org.atguigu.gmall_publisher.bean.DAUDataPerHour;
import org.atguigu.gmall_publisher.bean.GMVDataPerHour;
import org.atguigu.gmall_publisher.service.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.validation.ValidationAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Smexy on 2021/12/1
 */
@RestController
public class PublisherController {

    @Autowired
    private PublisherService publisherService;

    /*
            [{"id":"dau","name":"当日日活数","value":1200},
{"id":"new_mid","name":"新增设备数","value":233}
{"id":"order_amount","name":"当日交易额","value":1000.2 }
]
            [ {},{} ]

            []:  List， JsonArray
            {}:  Map,  JSONObject
     */
    @RequestMapping(value = "/realtime-total")
    public Object handle1(String date){

        Integer dauByDate = publisherService.getDAUByDate(date);
        Integer newUserCountByDate = publisherService.getNewUserCountByDate(date);
        Double gmvByDate = publisherService.getGMVByDate(date);

        ArrayList<JSONObject> result = new ArrayList<>();

        JSONObject jsonObject1 = new JSONObject();
        JSONObject jsonObject2 = new JSONObject();
        JSONObject jsonObject3 = new JSONObject();

        jsonObject1.put("id","dau");
        jsonObject1.put("name","当日日活数");
        jsonObject1.put("value",dauByDate);

        jsonObject2.put("id","new_mid");
        jsonObject2.put("name","新增设备数");
        jsonObject2.put("value",newUserCountByDate);

        jsonObject3.put("id","order_amount");
        jsonObject3.put("name","当日交易额");
        jsonObject3.put("value",gmvByDate);

        result.add(jsonObject1);
        result.add(jsonObject2);
        result.add(jsonObject3);

        return result;

    }

    /*
            {"yesterday":{"11":383,"12":123,"17":88,"19":200 },
                "today":{"12":38,"13":1233,"17":123,"19":688 }
            }
     */
    @RequestMapping(value = "/realtime-hours")
    public Object handle2(String id,String date){

        //基于今天的日期求昨天的日期
        String yestodayDate = LocalDate.parse(date).minusDays(1).toString();

        JSONObject result = new JSONObject();

        if ("order_amount".equals(id)){

            List<GMVDataPerHour> todayData = publisherService.getGMVDatasPerHour(date);
            List<GMVDataPerHour> yestodayData = publisherService.getGMVDatasPerHour(yestodayDate);

            result.put("yesterday",parseGMVPerHoursData(yestodayData));
            result.put("today",parseGMVPerHoursData(todayData));

        }else{

            List<DAUDataPerHour> todayData = publisherService.getDauDatasPerHour(date);
            List<DAUDataPerHour> yestodayData = publisherService.getDauDatasPerHour(yestodayDate);

            result.put("yesterday",parseDAUPerHoursData(yestodayData));
            result.put("today",parseDAUPerHoursData(todayData));
        }

        return result;


    }
    public  JSONObject parseGMVPerHoursData(List<GMVDataPerHour> datas){

        JSONObject jsonObject1 = new JSONObject();

        for (GMVDataPerHour data : datas) {

            jsonObject1.put(data.getHour() ,data.getGmv());

        }

        return jsonObject1;

    }

    // 返回{"11":383,"12":123,"17":88,"19":200 }
    public  JSONObject parseDAUPerHoursData(List<DAUDataPerHour> datas){

        JSONObject jsonObject1 = new JSONObject();

        for (DAUDataPerHour data : datas) {

            jsonObject1.put(data.getHour() ,data.getCount());

        }

        return jsonObject1;

    }

    @RequestMapping(value = "/sale_detail")
    public  Object handle3(String date,String keyword,Integer startpage,Integer size) throws IOException {

        return  publisherService.getSaleDetail(date,keyword,startpage,size);



    }
}
