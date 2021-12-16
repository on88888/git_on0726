package com.atguigu.kafka.demo.consumer;

import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.TopicPartition;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Map;
import java.util.Properties;

/**
 * @author leon
 * @ClassName CustomConsumer01.java
 * @createTime 2021年09月30日 10:57:00
 */
public class CustomConsumer04 {
    public static void main(String[] args) {
        // 1. 配置文件对象
        Properties properties = new Properties();
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "hadoop102:9092");

        properties.put(ConsumerConfig.GROUP_ID_CONFIG,"atguigu10");
        properties.put(ConsumerConfig.PARTITION_ASSIGNMENT_STRATEGY_CONFIG,"org.apache.kafka.clients.consumer.RoundRobinAssignor");

        // 是否自动提交offset
        properties.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "false");
        // 提交offset的时间周期
        //properties.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "60000");

        properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest ");

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

            // 在消费数据之前，手动提交offset--- 先提交offset，后消费---》 漏消费
            consumer.commitSync();

            // 6. 遍历数据
            for (ConsumerRecord<String, String> consumerRecord : consumerRecords) {
                String value = consumerRecord.value();
                int partition = consumerRecord.partition();
                long offset = consumerRecord.offset();

                System.out.println("数据： "+value+", 分区："+partition+", 偏移量："+offset);
            }

            // 在消费数据之后，手动提交offset-- 后提交offset，先消费---》 重复消费
            consumer.commitAsync(new OffsetCommitCallback() {
                @Override
                public void onComplete(Map<TopicPartition, OffsetAndMetadata> offsets, Exception exception) {
                    if(exception !=null){
                        System.err.println("offset:"+offsets);
                    }
                }
            });;

        }


    }
}
