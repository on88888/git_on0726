package test.day02.chapter05.source;

import lombok.SneakyThrows;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

/**
 * @author on
 * @Description TODO
 * @since 2021年12月10日 18:37:42
 */
public class Source_Custom_RandomWatersensor_Test {
    @SneakyThrows
    public static void main(String[] args) {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        env.setParallelism(2);
        env.addSource(new RandomWatersensorTest())
                .print();

        env.execute();
    }
}
