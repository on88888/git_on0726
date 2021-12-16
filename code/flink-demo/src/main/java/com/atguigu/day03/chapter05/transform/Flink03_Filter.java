package com.atguigu.day03.chapter05.transform;

import org.apache.flink.api.common.functions.FilterFunction;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

/**
 * @author Hefei
 * @description
 * @project_name flink-0726
 * @package_name com.atguigu.chapter05.transform
 * @since 2021/12/10 14:46
 */
public class Flink03_Filter {
    public static void main(String[] args) {
        Configuration conf = new Configuration();
        conf.setInteger("rest.port", 10000);
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment(conf);
        env.setParallelism(2);

        DataStreamSource<Integer> ds = env.fromElements(1, 2, 3, 4);
        // 过滤流中的 偶数 ，只要偶数 不要奇数
        ds.filter(new FilterFunction<Integer>() {
            @Override
            public boolean filter(Integer value) throws Exception {
                if(value % 2 == 0){
                    return true;
                }else {
                    return false;
                }
            }
        }).print();

        try {
            env.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}