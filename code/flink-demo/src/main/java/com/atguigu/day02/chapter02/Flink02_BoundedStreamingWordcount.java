package com.atguigu.day02.chapter02;

import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.java.functions.KeySelector;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.util.Collector;

/**
 * @author Hefei
 * @description 使用流处理的方式来处理有界流数据
 * @project_name flink-0726
 * @package_name com.atguigu.chapter02
 * @since 2021/12/8 10:18
 */
public class Flink02_BoundedStreamingWordcount {
    public static void main(String[] args) throws Exception {
        // 1. 创建流式执行环境
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        // 设置并行度
        // env.setParallelism(1);

        DataStreamSource<String> lineDSS = env.readTextFile("input/words.txt");


        // 处理数据
        lineDSS
                .flatMap(new FlatMapFunction<String, String>() {

                    @Override
                    public void flatMap(String line, Collector<String> out) throws Exception {
                        for (String word : line.split(" ")) {
                            out.collect(word);
                        }
                    }
                })
                .map(new MapFunction<String, Tuple2<String,Long>>() {
                    @Override
                    public Tuple2<String, Long> map(String word) throws Exception {
                        return Tuple2.of(word, 1L);
                    }
                })
                .keyBy(new KeySelector<Tuple2<String, Long>, String>() {
                    @Override
                    public String getKey(Tuple2<String, Long> tuple2) throws Exception {
                        return tuple2.f0;
                    }
                })
                .sum(1)
                .print();


        // 打印数据

        // 启动（执行）
        env.execute();
    }

}