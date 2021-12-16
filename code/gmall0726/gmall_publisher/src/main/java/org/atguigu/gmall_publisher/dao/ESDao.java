package org.atguigu.gmall_publisher.dao;

import com.alibaba.fastjson.JSONObject;

import java.io.IOException;

/**
 * Created by Smexy on 2021/12/6
 */
public interface ESDao {

    JSONObject getSaleDetail(String date, String keyword, Integer startpage, Integer size) throws IOException;

}
