package com.atguigu.day02.chapter05.transform;

import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.util.Collector;

/**
 * @author Hefei
 * @description
 * @project_name flink-0726
 * @package_name com.atguigu.chapter05.transform
 * @since 2021/12/10 14:41
 */
public class Flink02_FlatmapFunction {
    public static void main(String[] args) {
        Configuration conf = new Configuration();
        conf.setInteger("rest.port", 10000);
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment(conf);
        env.setParallelism(1);

        DataStreamSource<Integer> ds = env.fromElements(1, 2, 3, 4);

        // 求出每个数字本身、它的的平方、它的立方然后传出来  1  -> N
        ds.flatMap(new FlatMapFunction<Integer, Integer>() {
            @Override
            public void flatMap(Integer value, Collector<Integer> out) throws Exception {
                out.collect(value);
                out.collect(value * value);
                out.collect(value * value * value);
            }
        })
                .print();

        try {
            env.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}