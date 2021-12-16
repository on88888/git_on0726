package test.day02.chapter05.source;

import com.atguigu.day02.bean.WaterSensor;
import lombok.SneakyThrows;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

import java.util.Arrays;
import java.util.List;

/**
 * @author on
 * @Description TODO
 * @since 2021年12月10日 18:15:32
 */
public class Source_Collection_test {
    @SneakyThrows
    public static void main(String[] args) {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        //设置并行度
        env.setParallelism(1);

        List<WaterSensor> waterSensors = Arrays.asList(
                new WaterSensor("sensor_1", 1633792000L, 10),
                new WaterSensor("sensor_2", 1633794000L, 50),
                new WaterSensor("sensor_3", 1633796000L, 40)
        );

        //env.fromCollection(waterSensors)
        //      .print();

        env.fromElements(
                        new WaterSensor("sensor_1", 1633792000L, 10),
                        new WaterSensor("sensor_2", 1633794000L, 50),
                        new WaterSensor("sensor_3", 1633796000L, 40))
                .print();
        //启动env
        env.execute();
    }
}
