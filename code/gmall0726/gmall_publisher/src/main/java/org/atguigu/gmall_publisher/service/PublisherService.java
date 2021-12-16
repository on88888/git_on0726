package org.atguigu.gmall_publisher.service;

import com.alibaba.fastjson.JSONObject;
import org.atguigu.gmall_publisher.bean.DAUDataPerHour;
import org.atguigu.gmall_publisher.bean.GMVDataPerHour;

import java.io.IOException;
import java.util.List;

/**
 * Created by Smexy on 2021/12/1
 */
public interface PublisherService {


    Integer getDAUByDate(String date);


    Integer getNewUserCountByDate(String date);

    List<DAUDataPerHour> getDauDatasPerHour(String date);

    //当日gmv
    Double getGMVByDate(String date);

    List<GMVDataPerHour> getGMVDatasPerHour(String date);

    //查询购物明细
    JSONObject getSaleDetail(String date, String keyword, Integer startpage, Integer size) throws IOException;
}
