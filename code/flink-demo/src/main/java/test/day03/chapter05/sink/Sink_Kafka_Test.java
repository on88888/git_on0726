package test.day03.chapter05.sink;

import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaProducer;
import test.day02.bean.WaterSensorTest;
import test.day02.chapter05.source.RandomWatersensorTest;

/**
 * @author on
 * @Description TODO
 * @since 2021年12月11日 13:44:28
 */
public class Sink_Kafka_Test {
    public static void main(String[] args) {

        Configuration conf = new Configuration();
        conf.setInteger("rest.port", 10000);
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.setParallelism(2);

        env.addSource(new RandomWatersensorTest())
                .map(WaterSensorTest::toString)
                .addSink(new FlinkKafkaProducer<String>("hadoop102:9092",
                        "watersensor",
                        new SimpleStringSchema()));

        try {
            env.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
