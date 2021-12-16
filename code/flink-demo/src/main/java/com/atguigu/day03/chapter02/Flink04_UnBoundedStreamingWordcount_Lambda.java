package com.atguigu.day03.chapter02;

import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.common.typeinfo.Types;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

/**
 * @author Hefei
 * @description
 * @project_name flink-0726
 * @package_name com.atguigu.chapter02
 * @since 2021/12/8 10:47
 */
public class Flink04_UnBoundedStreamingWordcount_Lambda {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        DataStreamSource<String> DSS = env.socketTextStream("hadoop102", 9999);

        DSS
                .flatMap((FlatMapFunction<String, String>) (line, out) -> {
                    for (String word : line.split(" ")) {
                        out.collect(word);
                    }
                })
                .returns(Types.STRING)
                .map((MapFunction<String, Tuple2<String, Long>>) word -> Tuple2.of(word, 1L))
                .returns(Types.TUPLE(Types.STRING,Types.LONG))
                .keyBy(v -> v.f0)
                .sum(1)
                .print();

        env.execute("Flink03_UnBoundedStreamingWordcount");

    }
}