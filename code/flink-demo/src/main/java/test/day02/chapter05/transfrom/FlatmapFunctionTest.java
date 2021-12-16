package test.day02.chapter05.transfrom;

import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.util.Collector;

/**
 * @author on
 * @Description TODO
 * @since 2021年12月10日 19:07:23
 */
public class FlatmapFunctionTest {
    public static void main(String[] args) {
        Configuration conf = new Configuration();
        conf.setInteger("rest.port",10000);
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.setParallelism(1);

        DataStreamSource<Integer> ds = env.fromElements(1, 2, 3, 4);

        //求出每个数字本身，平方，立方
        ds.flatMap(new FlatMapFunction<Integer, Integer>() {
            @Override
            public void flatMap(Integer value, Collector<Integer> out) throws Exception {
                out.collect(value);
                out.collect(value*value);
                out.collect(value*value*value);
            }
        })
                .print();

        try {
            env.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
