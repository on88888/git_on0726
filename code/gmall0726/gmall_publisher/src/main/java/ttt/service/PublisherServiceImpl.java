package ttt.service;

import org.atguigu.gmall_publisher.bean.DAUDataPerHour;
import org.atguigu.gmall_publisher.mappers.DAUMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Smexy on 2021/12/1
 */
@Service
public class PublisherServiceImpl implements PublisherService {

    @Autowired
    private DAUMapper dauMapper;

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
}
