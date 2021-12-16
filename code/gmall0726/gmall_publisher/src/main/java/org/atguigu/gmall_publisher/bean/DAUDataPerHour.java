package org.atguigu.gmall_publisher.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by Smexy on 2021/12/1
 *
 *  "11":383
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DAUDataPerHour {

    private String hour;
    private  Integer count;
}
