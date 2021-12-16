package org.atguigu.gmall_publisher.mappers;

import org.atguigu.gmall_publisher.bean.DAUDataPerHour;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Smexy on 2021/12/1
 */
@Repository
public interface DAUMapper {

    //当日日活数
    Integer getDAUByDate(String date);

    //新增设备数
    Integer getNewUserCountByDate(String date);

    // "yesterday":{DAUDataPerHour,DAUDataPerHour,DAUDataPerHour,"19":200 }   "11":383 是一个entry
    List<DAUDataPerHour> getDauDatasPerHour(String date);

}
