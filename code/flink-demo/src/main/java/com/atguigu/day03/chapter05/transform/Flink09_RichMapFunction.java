package com.atguigu.day03.chapter05.transform;

import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.common.functions.RichMapFunction;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

/**
 * @author Hefei
 * @description
 * @project_name flink-0726
 * @package_name com.atguigu.chapter05.transform
 * @since 2021/12/10 16:28
 */
public class Flink09_RichMapFunction {
    public static void main(String[] args) {
        Configuration conf = new Configuration();
        conf.setInteger("rest.port", 10000);
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment(conf);
        env.setParallelism(2);

        DataStreamSource<Integer> ds = env.fromElements(1, 2, 3, 4);

        // 求出每个数字的平方然后传出来
        ds.map(new RichMapFunction<Integer, Integer>() {
                    @Override
                    public Integer map(Integer value) throws Exception {
                        return value * value;
                    }

                    @Override
                    public void open(Configuration parameters) throws Exception {
                        System.out.println("Flink09_RichMapFunction.open");
                        // 一般都会在open方法中 获取数据库的连接  然后进行分区操作
                    }

                    @Override
                    public void close() throws Exception {
                        System.out.println("Flink09_RichMapFunction.close");
                        // close只是会在程序结束时候 调用一次 进行关闭 一般用来归还数据库连接
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