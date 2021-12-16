package test.day02.chapter05.transfrom;

import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

/**
 * @author on
 * @Description TODO
 * @since 2021年12月10日 20:24:52
 */
public class UnionTest {
    public static void main(String[] args) {

        Configuration conf = new Configuration();
        conf.setInteger("rest.port",10000);
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.setParallelism(2);

        DataStreamSource<Integer> DS1 = env.fromElements(1, 2, 3, 4);
        DataStreamSource<Integer> DS2 = env.fromElements(1, 5, 7, 8);
        DataStreamSource<Integer> DS5 = env.fromElements(1, 6, 6, 6);
        DataStreamSource<String> DS3 = env.fromElements("a", "b", "c");
        DataStreamSource<String> DS4 = env.fromElements("d", "e", "f");

        // 这个是错误的用法  不同类型的数据流无法进行unionDataStream<Integer> union = DS1.union(DS3);
        DataStream<Integer> union = DS1.union(DS2).union(DS5);
        // 这时候 三个流 合成一个流  且无法分开  无法知道之前的流中是什么数据
        union.print();

        try {
            env.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
