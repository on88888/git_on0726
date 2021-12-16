package com.atguigu.day03.chapter05.transform;

import com.atguigu.day03.bean.WaterSensor;
import com.atguigu.day03.chapter05.source.RandomWatersensor;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

/**
 * @author Hefei
 * @description
 * @project_name flink-0726
 * @package_name com.atguigu.chapter05.transform
 * @since 2021/12/10 15:42
 */
public class Flink05_Agg {
    public static void main(String[] args) {
        Configuration conf = new Configuration();
        conf.setInteger("rest.port", 10000);
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment(conf);
        env.setParallelism(1);

        // ArrayList<WaterSensor> waterSensors = new ArrayList<>();
        // waterSensors.add(new WaterSensor("sensor_1", 1607527992000L, 20));
        // waterSensors.add(new WaterSensor("sensor_1", 1607527994000L, 50));
        // waterSensors.add(new WaterSensor("sensor_1", 1607527996000L, 10));
        // waterSensors.add(new WaterSensor("sensor_2", 1607527993000L, 10));
        // waterSensors.add(new WaterSensor("sensor_2", 1607527995000L, 30));

        // DataStreamSource<WaterSensor> waterSensorDS = env.fromCollection(waterSensors);
        DataStreamSource<WaterSensor> waterSensorDS = env.addSource(new RandomWatersensor());

        waterSensorDS.keyBy(WaterSensor::getId)
                .minBy("vc",Boolean.TRUE)
                .print();

        try {
            env.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}