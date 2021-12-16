package com.atguigu.kafka.demo.consumer;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.ProducerConfig;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Properties;

/**
 * @author leon
 * @ClassName CustomConsumer01.java
 * @createTime 2021年09月30日 10:57:00
 */
public class CustomConsumer01 {
    public static void main(String[] args) {
        // 1. 配置文件对象
        Properties properties = new Properties();
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "hadoop102:9092");

        properties.put(ConsumerConfig.GROUP_ID_CONFIG,"atguigu3");
        properties.put(ConsumerConfig.PARTITION_ASSIGNMENT_STRATEGY_CONFIG,"org.apache.kafka.clients.consumer.StickyAssignor");

        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.StringDeserializer");
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.StringDeserializer");
        // 2. 初始化消费者客户端对象
        KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(properties);

        // 3. 订阅Topic
        ArrayList<String> topics = new ArrayList<>();
        topics.add("first");
        consumer.subscribe(topics);

        // 4. 消费
        while (true){
            // 5. 拉取数据
            ConsumerRecords<String, String> consumerRecords = consumer.poll(Duration.ofSeconds(2));

            // 6. 遍历数据
            for (ConsumerRecord<String, String> consumerRecord : consumerRecords) {
                String value = consumerRecord.value();
                int partition = consumerRecord.partition();
                long offset = consumerRecord.offset();

                System.out.println("数据： "+value+", 分区："+partition+", 偏移量："+offset);
            }

        }


    }
}
