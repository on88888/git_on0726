package com.atguigu.day03.chapter05.transform;

import com.atguigu.day03.bean.WaterSensor;
import com.atguigu.day03.chapter05.source.RandomWatersensor;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.KeyedProcessFunction;
import org.apache.flink.util.Collector;

import java.util.HashMap;

/**
 * @author Hefei
 * @description
 * @project_name flink-0726
 * @package_name com.atguigu.chapter05.transform
 * @since 2021/12/11 9:23
 */
public class Flink11_Process_Keyby {
    public static void main(String[] args) {
        Configuration conf = new Configuration();
        conf.setInteger("rest.port", 10000);
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment(conf);
        env.setParallelism(2);

        env.addSource(new RandomWatersensor())
                .keyBy(WaterSensor::getId)
                .process(new KeyedProcessFunction<String, WaterSensor, String>() {
                    HashMap<String, Integer> hashMap = new HashMap();
                    @Override
                    public void processElement(WaterSensor waterSensor,
                                               KeyedProcessFunction<String, WaterSensor, String>.Context ctx,
                                               Collector<String> out) throws Exception {



                        if (hashMap.containsKey(waterSensor.getId())) {
                            Integer sum = hashMap.get(waterSensor.getId());
                            sum += waterSensor.getVc();
                            hashMap.put(waterSensor.getId(), sum);
                           out.collect( "当前" + waterSensor.getId() + "传感器水位值是：" + sum);

                        } else {
                            out.collect( "当前" + waterSensor.getId() + "传感器水位值是：" + waterSensor.getVc());
                            hashMap.put(waterSensor.getId(), waterSensor.getVc());
                        }
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