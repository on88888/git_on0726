package com.atguigu.day03.chapter05.sink;

import com.atguigu.day03.bean.WaterSensor;
import com.atguigu.day03.chapter05.source.RandomWatersensor;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaProducer;

/**
 * @author Hefei
 * @description
 * @project_name flink-0726
 * @package_name com.atguigu.chapter05.sink
 * @since 2021/12/11 10:11
 */
public class Flink02_Sink_Kafka {
    public static void main(String[] args) {
        Configuration conf = new Configuration();
        conf.setInteger("rest.port", 10000);
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment(conf);
        env.setParallelism(1);

        env.addSource(new RandomWatersensor())
                .map(WaterSensor::toString)
                .addSink(new FlinkKafkaProducer<String>(
                        "hadoop102:9092",
                        "watersensor",
                        new SimpleStringSchema()));

        try {
            env.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}