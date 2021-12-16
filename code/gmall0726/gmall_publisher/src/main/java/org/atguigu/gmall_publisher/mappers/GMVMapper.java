package org.atguigu.gmall_publisher.mappers;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.atguigu.gmall_publisher.bean.GMVDataPerHour;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Smexy on 2021/12/1
 */
@Repository
@DS("mysql")
public interface GMVMapper {

    //当日gmv
    Double getGMVByDate(String date);

    List<GMVDataPerHour> getGMVDatasPerHour(String date);

}
