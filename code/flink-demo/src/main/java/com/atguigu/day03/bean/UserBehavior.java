package com.atguigu.day03.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Hefei
 * @description
 * @project_name flink-0726
 * @package_name com.atguigu.bean
 * @since 2021/12/11 14:10
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserBehavior {
    private Long userId;
    private Long itemId;
    private Integer categoryId;
    private String behavior;
    private Long timestamp;
}