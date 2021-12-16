package com.atguigu.day03.chapter02;

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
 * @author Hefei
 * @description
 * @project_name flink-0726
 * @package_name com.atguigu.chapter02
 * @since 2021/12/8 10:47
 */
public class Flink05_UnBoundedStreamingWordcount_Parallelism {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        DataStreamSource<String> DSS = env.socketTextStream("localhost", 9999);

        env.setParallelism(10);
        System.out.println("XXXXXXXXXXXXXXXXXXXXXXXX");

        SingleOutputStreamOperator<Tuple2<String, Long>> mapDSS = DSS
                .flatMap(new FlatMapFunction<String, String>() {

                    @Override
                    public void flatMap(String line, Collector<String> out) throws Exception {
                        for (String word : line.split(" ")) {
                            out.collect(word);
                        }
                    }
                })
                .setParallelism(5)
                .map(new MapFunction<String, Tuple2<String, Long>>() {
                    @Override
                    public Tuple2<String, Long> map(String word) throws Exception {
                        return Tuple2.of(word, 1L);
                    }
                });

        KeyedStream<Tuple2<String, Long>, String> tuple2StringKeyedStream = mapDSS.keyBy(new KeySelector<Tuple2<String, Long>, String>() {
            @Override
            public String getKey(Tuple2<String, Long> value) throws Exception {
                return value.f0;
            }
        });
        tuple2StringKeyedStream.sum(1)
                .setParallelism(6)
                .print();

        System.out.println(env.getExecutionPlan());

        env.execute("Flink03_UnBoundedStreamingWordcount");

    }
}