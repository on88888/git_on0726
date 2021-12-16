package com.atguigu.kafka.demo.producer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

/**
 * @author leon
 * @ClassName CustomAsyncProducer.java
 * @createTime 2021年09月29日 15:26:00
 */
public class CustomAsyncProducer {
    public static void main(String[] args) {
        // 1. 设置生产者配置对象
        Properties properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"hadoop102:9092");
        // 批次大小 默认16K
        properties.put("batch.size", 16384);

        // 等待时间
        properties.put("linger.ms", 1);



        // RecordAccumulator缓冲区大小 默认32M
        properties.put("buffer.memory", 33554432);


        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.StringSerializer");
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.StringSerializer");

        // 2. 初始化一个kafka生产者对象
        KafkaProducer<String, String> producer = new KafkaProducer<String, String>(properties);

        // 3. 生产数据并发送
        for(int i=1; i<=5; i++){
            String message = "kafka_"+i;
            // 4. 包装为ProducerRecord
            ProducerRecord<String, String> producerRecord = new ProducerRecord<String, String>(
                    "first",
                    message
            );

            // 5. 发送数据
            producer.send(producerRecord);

            // 6. 睡一觉
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // 4.关闭资源
        //producer.close();

    }
}
