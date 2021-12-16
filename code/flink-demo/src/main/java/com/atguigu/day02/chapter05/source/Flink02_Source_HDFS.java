package com.atguigu.day02.chapter05.source;

import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

/**
 * @author Hefei
 * @description
 * @project_name flink-0726
 * @package_name com.atguigu.chapter05.source
 * @since 2021/12/10 11:08
 */
public class Flink02_Source_HDFS {
    public static void main(String[] args) throws Exception {

        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        env.setParallelism(1);

        env.readTextFile("hdfs://hadoop102:8020/user/atguigu/words.txt").print();

        env.execute();

    }
}