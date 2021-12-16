package com.atguigu.day02.chapter05.source;

import com.atguigu.day02.bean.WaterSensor;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

import java.util.Arrays;
import java.util.List;

/**
 * @author Hefei
 * @description
 * @project_name flink-0726
 * @package_name com.atguigu.chapter05.source
 * @since 2021/12/10 11:08
 */
public class Flink01_Source_Collection {
    public static void main(String[] args) throws Exception {

        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        env.setParallelism(1);
        List<WaterSensor> waterSensors = Arrays.asList(
                new WaterSensor("sensor_1", 1633792000L, 10),
                new WaterSensor("sensor_2", 1633794000L, 50),
                new WaterSensor("sensor_3", 1633796000L, 40)
        );
        List<Integer> data = Arrays.asList(1, 2, 3, 4);

        // env.fromCollection(waterSensors)
        //         .print();

        env.fromElements(new WaterSensor("sensor_1", 1633792000L, 10),
                new WaterSensor("sensor_2", 1633794000L, 50),
                new WaterSensor("sensor_3", 1633796000L, 40))
                        .print();

        env.execute();

    }
}