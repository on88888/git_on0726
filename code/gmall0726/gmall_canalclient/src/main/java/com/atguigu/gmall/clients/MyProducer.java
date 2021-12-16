package com.atguigu.gmall.clients;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

/**
 * Created by Smexy on 2021/12/1
 */
public class MyProducer {

    private static KafkaProducer<String,String> producer;

    static {

        producer =  getProducer();

    }

    public static KafkaProducer<String,String> getProducer(){

        Properties properties = new Properties();

        // 集群地址，key,value的序列号器  参考ProducerConfig
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"hadoop102:9092");
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.StringSerializer");
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.StringSerializer");

        KafkaProducer<String, String> producer = new KafkaProducer<>(properties);

        return producer;

    }

    public static void sendData(String topic,String msg){

        producer.send(new ProducerRecord<String,String>(topic,msg));

    }
}
