package com.atguigu.day03.chapter05.source;

import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer;

import java.nio.charset.StandardCharsets;
import java.util.Properties;

/**
 * @author Hefei
 * @description
 * @project_name flink-0726
 * @package_name com.atguigu.chapter05.source
 * @since 2021/12/10 11:08
 */
public class Flink03_Source_Kafka {
    public static void main(String[] args) throws Exception {

        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        env.setParallelism(1);

        Properties properties = new Properties();

        properties.setProperty("bootstrap.servers", "hadoop102:9092,hadoop103:9092,hadoop104:9092");
        properties.setProperty("group.id", "Flink03_Source_Kafka");
        properties.setProperty("auto.offset.reset", "latest");
        env.addSource(new FlinkKafkaConsumer<String>(
                        "test",
                        new SimpleStringSchema(StandardCharsets.UTF_8),
                        properties))
                .print();

        env.execute();

    }
}