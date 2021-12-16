package com.atguigu.kafka.demo.producer;

import org.apache.kafka.clients.producer.*;

import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @author leon
 * @ClassName CustomAsyncProducerCallBack.java
 * @createTime 2021年09月29日 15:51:00
 */
public class CustomAsyncProducerCallBack {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // 1. 设置生产者配置对象
        Properties properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"hadoop102:9092");
        // 批次大小 默认16K
        properties.put("batch.size", 16384);

        // 等待时间
        properties.put("linger.ms", 1);

        // RecordAccumulator缓冲区大小 默认32M
        properties.put("buffer.memory", 33554432);

        // 设置ack
        properties.put(ProducerConfig.ACKS_CONFIG, "all");

        properties.put(ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG, "true");

        // 重试次数
        properties.put("retries", 3);

        // 配置分区器
        properties.put(ProducerConfig.PARTITIONER_CLASS_CONFIG, "com.atguigu.kafka.demo.partitioner.CustomPartition");

        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.StringSerializer");
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.StringSerializer");

        // 2. 初始化一个kafka生产者对象
        KafkaProducer<String, String> producer = new KafkaProducer<String, String>(properties);

        // 3. 生产数据并发送
        for(int i=1; i<=1000000; i++){
            String message = "kafka_"+i;
            // 4. 包装为ProducerRecord
            ProducerRecord<String, String> producerRecord = new ProducerRecord<String, String>(
                    "first",
                    ""+i,
                    message
            );

            System.out.println("你看看我在哪里？");

            // 5. 发送数据
            producer.send(producerRecord, new Callback() {
                @Override
                public void onCompletion(RecordMetadata metadata, Exception exception) {
                    // 判断
                    if(exception == null){
                        int partition = metadata.partition();
                        long offset = metadata.offset();
                        System.out.println("数据： "+producerRecord.value()+"， 分区："+partition+", 偏移量："+offset);
                    }

                }
            });

//            // 6. 睡一觉
//            try {
//                Thread.sleep(2);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
        }

        // 4.关闭资源
        producer.close();

    }
}
