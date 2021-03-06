package test.day01;

import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.java.functions.KeySelector;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.KeyedStream;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.util.Collector;

/**
 * @author on
 * @Description TODO
 * @since 2021年12月08日 18:04:32
 */
public class UnBoundeStreamingwordcountTest {
    public static void main(String[] args) throws Exception {

        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        //DataStreamSource<String> DSS = env.socketTextStream("localhost", 9999);
        DataStreamSource<String> DSS = env.socketTextStream("hadoop102", 9999);
        System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
        SingleOutputStreamOperator<Tuple2<String, Long>> mapDSS = DSS.flatMap(new FlatMapFunction<String, String>() {
            @Override
            public void flatMap(String value, Collector<String> out) throws Exception {
                for (String s : value.split(" ")) {
                    out.collect(s);
                }
            }
        }).map(new MapFunction<String, Tuple2<String, Long>>() {
            @Override
            public Tuple2<String, Long> map(String value) throws Exception {
                return Tuple2.of(value, 1L);
            }
        });

        KeyedStream<Tuple2<String, Long>, String> wordBy = mapDSS.keyBy(new KeySelector<Tuple2<String, Long>, String>() {
            @Override
            public String getKey(Tuple2<String, Long> value) throws Exception {
                return value.f0;
            }
        });

        wordBy.sum(1)
                .print();

        env.execute("UnBoundeStreamingwordcountTest");
    }
}
