package test.day02.chapter05.test;

import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import test.day02.bean.WaterSensorTest;

import java.util.ArrayList;

/**
 * @author on
 * @Description TODO
 * @since 2021年12月10日 20:58:22
 */

//练习题：自己模拟数据，使用map算子将WaterSensor类型的数据转换为String类型数据
public class Test01_Map {
    public static void main(String[] args) {

        Configuration conf = new Configuration();
        conf.setInteger("rest.port",10000);
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.setParallelism(1);

        ArrayList<WaterSensorTest> waterSensors = new ArrayList<>();
        waterSensors.add(new WaterSensorTest("sensor_1", 1607527992000L, 20));
        waterSensors.add(new WaterSensorTest("sensor_1", 1607527994000L, 50));
        waterSensors.add(new WaterSensorTest("sensor_1", 1607527996000L, 50));
        waterSensors.add(new WaterSensorTest("sensor_2", 1607527993000L, 10));
        waterSensors.add(new WaterSensorTest("sensor_2", 1607527995000L, 30));

        DataStreamSource<WaterSensorTest> waterSensorDS = env.fromCollection(waterSensors);

        waterSensorDS.map(new MapFunction<WaterSensorTest, String>() {
            @Override
            public String map(WaterSensorTest value) throws Exception {
                return value.toString();
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
