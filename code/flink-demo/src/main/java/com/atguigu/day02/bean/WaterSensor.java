package com.atguigu.day02.bean;

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

@Data //生成getter,setter等函数
@AllArgsConstructor //生成全参数构造函数
@NoArgsConstructor//生成无参构造函数
public class WaterSensor {
    private String id;
    private Long ts;
    private Integer vc;
}