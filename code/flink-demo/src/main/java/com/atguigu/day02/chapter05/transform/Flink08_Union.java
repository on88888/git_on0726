package com.atguigu.day02.chapter05.transform;

import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

/**
 * @author Hefei
 * @description
 * @project_name flink-0726
 * @package_name com.atguigu.chapter05.transform
 * @since 2021/12/10 16:23
 */
public class Flink08_Union {
    public static void main(String[] args) {
        Configuration conf = new Configuration();
        conf.setInteger("rest.port", 10000);
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment(conf);
        env.setParallelism(2);

        DataStreamSource<Integer> DS1 = env.fromElements(1, 2, 3, 4);
        DataStreamSource<Integer> DS3 = env.fromElements(1, 2, 3, 4, 6, 8);
        DataStreamSource<Integer> DS4 = env.fromElements(1, 2, 3, 4, 10, 15);
        DataStreamSource<String> DS2 = env.fromElements("a", "b", "c");

        // 这个是错误的用法  不同类型的数据流无法进行unionDataStream<Integer> union = DS1.union(DS2);
        DataStream<Integer> union = DS1.union(DS3).union(DS4);
        // 这时候 三个流 合成一个流  且无法分开  无法知道之前的流中是什么数据
        union.print();

        try {
            env.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}