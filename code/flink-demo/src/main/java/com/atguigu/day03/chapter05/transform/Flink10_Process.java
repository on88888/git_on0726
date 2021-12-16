package com.atguigu.day03.chapter05.transform;

import com.atguigu.day03.bean.WaterSensor;
import com.atguigu.day03.chapter05.source.RandomWatersensor;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.ProcessFunction;
import org.apache.flink.util.Collector;

/**
 * @author Hefei
 * @description
 * @project_name flink-0726
 * @package_name com.atguigu.chapter05.transform
 * @since 2021/12/11 9:15
 */
public class Flink10_Process {
    public static void main(String[] args) {
        Configuration conf = new Configuration();
        conf.setInteger("rest.port", 10000);
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment(conf);
        env.setParallelism(1);

        env.addSource(new RandomWatersensor())
                .process(new ProcessFunction<WaterSensor, Integer>() {
                    int a = 0;
                    @Override
                    public void processElement(WaterSensor waterSensor,
                                               ProcessFunction<WaterSensor, Integer>.Context ctx,
                                               Collector<Integer> out) throws Exception {
                        a +=waterSensor.getVc();
                        out.collect(a);
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