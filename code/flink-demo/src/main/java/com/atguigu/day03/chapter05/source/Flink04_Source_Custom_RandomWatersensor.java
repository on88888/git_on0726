package com.atguigu.day03.chapter05.source;

import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

/**
 * @author Hefei
 * @description
 * @project_name flink-0726
 * @package_name com.atguigu.chapter05.source
 * @since 2021/12/10 11:30
 */
public class Flink04_Source_Custom_RandomWatersensor {
    public static void main(String[] args) throws Exception {

        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        env.setParallelism(1);

        env.addSource(new RandomWatersensor())
                .print();

        env.execute();
    }
}