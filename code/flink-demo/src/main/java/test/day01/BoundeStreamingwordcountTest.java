package test.day01;

import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.java.functions.KeySelector;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.util.Collector;

/**
 * @author on
 * @Description 使用流处理的方式来处理有界流数据
 * @since  2021年12月08日 16:47:00
 */
public class BoundeStreamingwordcountTest {
    public static void main(String[] args) throws Exception {
        //创建执行环境
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.setParallelism(2);
        DataStreamSource<String> DSS = env.socketTextStream("localhost", 9999);
        DSS
        //处理数据
                .flatMap(new FlatMapFunction<String, String>() {
                    @Override
                    public void flatMap(String line, Collector<String> out) throws Exception {
                        for (String word : line.split(" ")) {
                            out.collect(word);
                        }
                    }
                })
                .map(new MapFunction<String, Tuple2<String,Integer>>() {
                    @Override
                    public Tuple2<String, Integer> map(String word) throws Exception {
                        return Tuple2.of(word,1);
                    }
                })
                .keyBy(new KeySelector<Tuple2<String, Integer>, String>() {
                    @Override
                    public String getKey(Tuple2<String, Integer> tuple2) throws Exception {
                        return tuple2.f0;
                    }
                })
                .sum(1)
                .print();
        // 启动（执行）
        env.execute();
    }
}
