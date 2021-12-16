package test.day02.chapter05.source;

import lombok.SneakyThrows;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer;

import java.util.Properties;

/**
 * @author on
 * @Description TODO
 * @since 2021年12月10日 18:31:32
 */
public class Source_Kafka_Test {

    public static void main(String[] args) throws Exception {

        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        env.setParallelism(1);

        Properties properties = new Properties();
        properties.setProperty("bootstrap.servers", "hadoop102:9092,hadoop103:9092,hadoop104:9092");
        properties.setProperty("group.id", "Source_Kafka_Test");
        properties.setProperty("auto.offset.reset", "latest");

        env.addSource(new FlinkKafkaConsumer<String>(
                        "test",
                        new SimpleStringSchema(),
                        properties))
                .print();
        env.execute();
    }
}
