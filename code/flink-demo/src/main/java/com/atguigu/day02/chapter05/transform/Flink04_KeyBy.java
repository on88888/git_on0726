package com.atguigu.day02.chapter05.transform;

import org.apache.flink.api.java.functions.KeySelector;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

/**
 * @author Hefei
 * @description
 * @project_name flink-0726
 * @package_name com.atguigu.chapter05.transform
 * @since 2021/12/10 15:10
 */
public class Flink04_KeyBy {
    public static void main(String[] args) {
        Configuration conf = new Configuration();
        conf.setInteger("rest.port", 10000);
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment(conf);
        env.setParallelism(2);

        DataStreamSource<Integer> ds = env.fromElements(1, 2, 3, 4);

        ds
                // .keyBy(new KeySelector<Integer, Integer>() {
                //     @Override
                //     public Integer getKey(Integer value) throws Exception {
                //         if (value % 2 == 0) {
                //             return 0;
                //         } else {
                //             return 1;
                //         }
                //     }
                // })
                .keyBy(new KeySelector<Integer, String>() {
                    @Override
                    public String getKey(Integer value) throws Exception {
                        if (value % 2 == 0) {
                            return "偶数";
                        } else {
                            return "奇数";
                        }
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