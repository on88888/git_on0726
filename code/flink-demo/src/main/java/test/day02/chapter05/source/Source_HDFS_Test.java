package test.day02.chapter05.source;

import lombok.SneakyThrows;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

/**
 * @author on
 * @Description TODO
 * @since 2021年12月10日 18:24:23
 */
public class Source_HDFS_Test {

    public static void main(String[] args) throws Exception {

        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        env.setParallelism(1);

        env.readTextFile("hdfs://hadoop102:8020/user/atguigu/words.txt")
                .print();

        env.execute();
    }
}
