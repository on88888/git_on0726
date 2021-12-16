package com.atguigu.day02.chapter05.transform;

import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.datastream.ConnectedStreams;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.co.CoMapFunction;

/**
 * @author Hefei
 * @description
 * @project_name flink-0726
 * @package_name com.atguigu.chapter05.transform
 * @since 2021/12/10 16:19
 */
public class Flink07_Connect {
    public static void main(String[] args) {
        Configuration conf = new Configuration();
        conf.setInteger("rest.port", 10000);
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment(conf);
        env.setParallelism(2);

        DataStreamSource<Integer> DS1 = env.fromElements(1, 2, 3, 4);
        DataStreamSource<String> DS2 = env.fromElements("a", "b", "c");

        ConnectedStreams<Integer, String> connectedStreams = DS1.connect(DS2);

        connectedStreams.map(new CoMapFunction<Integer, String, String>() {
                    @Override
                    public String map1(Integer value) throws Exception {
                        return value + "";
                    }

                    @Override
                    public String map2(String value) throws Exception {
                        return value;
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