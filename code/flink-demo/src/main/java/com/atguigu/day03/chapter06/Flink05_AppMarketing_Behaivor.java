package com.atguigu.day03.chapter06;

import org.apache.flink.api.common.typeinfo.Types;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

public class Flink05_AppMarketing_Behaivor {
    public static void main(String[] args) {
        Configuration conf = new Configuration();
        conf.setInteger("rest.port", 10004);
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment(conf);
        env.setParallelism(2);

        env.addSource(new MockAppDataSource())
                .map(value -> Tuple2.of(value.getBehavior(), 1L))
                .returns(Types.TUPLE(Types.STRING, Types.LONG))
                .keyBy(value -> value.f0)
                .sum(1)
                .print();

        try {
            env.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}