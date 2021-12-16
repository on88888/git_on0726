package com.atguigu.day03.chapter06;

import com.atguigu.day03.bean.AdsClickLog;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.java.functions.KeySelector;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

/**
 * @author Hefei
 * @description
 * @project_name flink-0726
 * @package_name com.atguigu.chapter06
 * @since 2021/12/11 15:34
 */
public class Flink06_AdsClick {
    public static void main(String[] args) {
        Configuration conf = new Configuration();
        conf.setInteger("rest.port", 10005);
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment(conf);
        env.setParallelism(2);

        env.readTextFile("input/AdClickLog.csv")
                .map(new MapFunction<String, AdsClickLog>() {
                    @Override
                    public AdsClickLog map(String value) throws Exception {
                        String[] datas = value.split(",");
                        return  new AdsClickLog(
                                Long.valueOf(datas[0]),
                                Long.valueOf(datas[1]),
                                datas[2],
                                datas[3],
                                Long.valueOf(datas[4])
                        );
                    }
                })
                .map(new MapFunction<AdsClickLog, Tuple2<Tuple2<String,Long>,Long>>() {
                    @Override
                    public Tuple2<Tuple2<String, Long>, Long> map(AdsClickLog value) throws Exception {
                        return Tuple2.of(Tuple2.of(value.getProvince(), value.getAdId()), 1L);
                    }
                })
                .keyBy(new KeySelector<Tuple2<Tuple2<String, Long>, Long>, Tuple2<String,Long>>() {
                    @Override
                    public Tuple2<String, Long> getKey(Tuple2<Tuple2<String, Long>, Long> value) throws Exception {
                        return value.f0;
                    }
                })
                .sum(1)
                .print();

        try {
            env.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}