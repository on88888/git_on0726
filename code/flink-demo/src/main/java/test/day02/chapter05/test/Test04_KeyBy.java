package test.day02.chapter05.test;

import org.apache.flink.api.java.functions.KeySelector;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import test.day02.bean.WaterSensorTest;

import java.util.ArrayList;

/**
 * @author on
 * @Description TODO
 * @since 2021年12月10日 21:18:05
 */

//练习：自己模拟数据，按照WaterSensor的id值来为数据标记key。
public class Test04_KeyBy {
    public static void main(String[] args) {

        Configuration conf = new Configuration();
        conf.setInteger("rest.port",10000);
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.setParallelism(2);

        ArrayList<WaterSensorTest> waterSensors = new ArrayList<>();
        waterSensors.add(new WaterSensorTest("sensor_1", 1607527992000L, 20));
        waterSensors.add(new WaterSensorTest("sensor_1", 1607527994000L, 50));
        waterSensors.add(new WaterSensorTest("sensor_1", 1607527996000L, 50));
        waterSensors.add(new WaterSensorTest("sensor_2", 1607527993000L, 10));
        waterSensors.add(new WaterSensorTest("sensor_2", 1607527995000L, 30));

        DataStreamSource<WaterSensorTest> waterSensorDS = env.fromCollection(waterSensors);

        waterSensorDS.keyBy(new KeySelector<WaterSensorTest, Object>() {
            @Override
            public Object getKey(WaterSensorTest value) throws Exception {
                if (value.getId().equals("sensor_2")){
                    return "sensor_1";
                }else {
                    return "sensor_2";
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
