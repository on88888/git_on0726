package test;


import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

/**
 * @author
 * @createTime
 *
 */

public class CAsyncProducer {
    public static void main(String[] args) {

        Properties properties = new Properties();
//        properties.put("bootstrap.servers", "hadoop102:9092");
//        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
//        properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "hadoop102:9092");

        // 批次大小 默认16K
        properties.put("batch.size", 16384);

        // 等待时间
        properties.put("linger.ms", 1);

        // RecordAccumulator缓冲区大小 默认32M
        properties.put("buffer.memory", 33554432);

        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");

        KafkaProducer<String, String> producer = new KafkaProducer<String, String>(properties);

        for (int i = 1; i <= 5 ; i++) {
            String message = "kaka_"+i;
            ProducerRecord<String, String> producerRecord = new ProducerRecord<String, String>("first",message );

            producer.send(producerRecord);
            // 6. 睡一觉
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }



        //producer.close();

    }
}


