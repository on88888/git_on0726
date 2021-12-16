package com.atguigu.day03.chapter06;

import com.atguigu.day03.bean.UserBehavior;
import org.apache.flink.api.common.functions.FilterFunction;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

/**
 * @author Hefei
 * @description
 * @project_name flink-0726
 * @package_name com.atguigu.chapter06
 * @since 2021/12/11 14:11
 */
public class Flink01_PV_Wordcount {
    public static void main(String[] args) {
        Configuration conf = new Configuration();
        conf.setInteger("rest.port", 10001);
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment(conf);
        env.setParallelism(2);

        env
                .readTextFile("input/UserBehavior.csv")
                .map(new MapFunction<String, UserBehavior>() {
                    @Override
                    public UserBehavior map(String line) throws Exception {
                        String[] datas = line.split(",");
                        return new UserBehavior(
                                Long.valueOf(datas[0]),
                                Long.valueOf(datas[1]),
                                Integer.valueOf(datas[2]),
                                datas[3],
                                Long.valueOf(datas[4])
                        );
                    }
                })
                .filter(new FilterFunction<UserBehavior>() {
                    @Override
                    public boolean filter(UserBehavior value) throws Exception {
                        return  "pv".equals(value.getBehavior());
                    }
                })
                .map(new MapFunction<UserBehavior, Tuple2<String,Long>>() {
                    @Override
                    public Tuple2<String, Long> map(UserBehavior value) throws Exception {
                        return Tuple2.of(value.getBehavior(), 1L);
                    }
                })
                .keyBy(t -> t.f0)
                .sum(1)
                .print();


        try {
            env.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}