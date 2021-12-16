package com.atguigu.day03.chapter02;

import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.api.java.operators.DataSource;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.util.Collector;


/**
 * @author Hefei
 * @description 用批处理的方式处理有界流数据（文件）
 * @project_name flink-0726
 * @package_name com.atguigu.chapter02
 * @since 2021/12/8 10:03
 */
public class Flink01_BatchWordcount {
    public static void main(String[] args) throws Exception {

        // 1. 创建执行环境(批处理的执行环境）来处理的是有界流
        ExecutionEnvironment env = ExecutionEnvironment.getExecutionEnvironment();

        DataSource<String> lineDS = env.readTextFile("input/words.txt");


        // 2. 处理数据
        lineDS
                // 经过flatmap之后 我们的数据由一行一行的 变成了一个一个的单词
                .flatMap(new FlatMapFunction<String, String>() {
                    @Override
                    public void flatMap(String line, Collector<String> out) throws Exception {
                        for (String word : line.split(" ")) {
                            out.collect(word);
                        }
                    }
                })
                // 经过map之后 我们的一个个的单词变成了(word,1L)这种形式
                .map(new MapFunction<String, Tuple2<String, Long>>() {
                    @Override
                    public Tuple2<String, Long> map(String word) throws Exception {
                        return Tuple2.of(word, 1L);
                    }
                })
                // 根据word进行分组
                .groupBy(0)
                // 根据第二个信息进行求和
                .sum(1)
                // 3. 打印
                .print();


    }
}