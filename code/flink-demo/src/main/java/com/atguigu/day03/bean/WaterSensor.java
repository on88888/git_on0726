package com.atguigu.day03.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Hefei
 * @description
 * @project_name flink-0726
 * @package_name com.atguigu.bean
 * @since 2021/12/10 11:06
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WaterSensor {
    private String id;
    private Long ts;
    private Integer vc;
}