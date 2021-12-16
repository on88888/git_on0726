package ttt.service;

import org.atguigu.gmall_publisher.bean.DAUDataPerHour;

import java.util.List;

/**
 * Created by Smexy on 2021/12/1
 */
public interface PublisherService {


    Integer getDAUByDate(String date);


    Integer getNewUserCountByDate(String date);

    List<DAUDataPerHour> getDauDatasPerHour(String date);

}
