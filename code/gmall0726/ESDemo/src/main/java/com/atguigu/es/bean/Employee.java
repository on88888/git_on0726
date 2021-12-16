package com.atguigu.es.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by Smexy on 2021/12/4
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {

    private String empid;
    private Integer age;
    private String gender;
    private String name;
    private String hobby;
    private Double balance;
}
