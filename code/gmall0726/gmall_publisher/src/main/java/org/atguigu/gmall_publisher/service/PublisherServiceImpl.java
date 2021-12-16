package org.atguigu.gmall_publisher.service;

import com.alibaba.fastjson.JSONObject;
import org.atguigu.gmall_publisher.bean.DAUDataPerHour;
import org.atguigu.gmall_publisher.bean.GMVDataPerHour;
import org.atguigu.gmall_publisher.dao.ESDao;
import org.atguigu.gmall_publisher.mappers.DAUMapper;
import org.atguigu.gmall_publisher.mappers.GMVMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

/**
 * Created by Smexy on 2021/12/1
 */
@Service
public class PublisherServiceImpl implements PublisherService {

    @Autowired
    private DAUMapper dauMapper;

    @Autowired
    private GMVMapper gmvMapper;

    @Autowired
    private ESDao esDao;

    @Override
    public Integer getDAUByDate(String date) {

        System.out.println("执行查询之前的业务逻辑....");

        Integer dauByDate = dauMapper.getDAUByDate(date);

        System.out.println("执行查询之后的业务逻辑....");

        return dauByDate;
    }

    @Override
    public Integer getNewUserCountByDate(String date) {

        System.out.println("执行查询之前的业务逻辑....");

        Integer newUserCountByDate = dauMapper.getNewUserCountByDate(date);

        System.out.println("执行查询之后的业务逻辑....");

        return newUserCountByDate;
    }

    @Override
    public List<DAUDataPerHour> getDauDatasPerHour(String date) {


        System.out.println("执行查询之前的业务逻辑....");

        List<DAUDataPerHour> dauDatasPerHour = dauMapper.getDauDatasPerHour(date);

        System.out.println("执行查询之后的业务逻辑....");

        return dauDatasPerHour;
    }

    @Override
    public Double getGMVByDate(String date) {

        System.out.println("执行查询之前的业务逻辑....");

        Double gmvByDate = gmvMapper.getGMVByDate(date);

        System.out.println("执行查询之后的业务逻辑....");

        return gmvByDate;
    }

    @Override
    public List<GMVDataPerHour> getGMVDatasPerHour(String date) {

        System.out.println("执行查询之前的业务逻辑....");

        List<GMVDataPerHour> gmvDatasPerHour = gmvMapper.getGMVDatasPerHour(date);

        System.out.println("执行查询之后的业务逻辑....");

        return gmvDatasPerHour;
    }

    @Override
    public JSONObject getSaleDetail(String date, String keyword, Integer startpage, Integer size) throws IOException {
        return esDao.getSaleDetail(date,keyword,startpage,size);
    }

}
