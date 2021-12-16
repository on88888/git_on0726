package test.day02.chapter05.transfrom;

import com.atguigu.day02.bean.WaterSensor;
import org.apache.flink.api.common.functions.ReduceFunction;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import test.day02.bean.WaterSensorTest;


import java.util.ArrayList;

/**
 * @author on
 * @Description TODO
 * @since 2021年12月10日 20:04:53
 */
public class ReduceTest {
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

        waterSensorDS
                .keyBy(WaterSensorTest::getId)
                .reduce(new ReduceFunction<WaterSensorTest>() {
                    @Override
                    public WaterSensorTest reduce(WaterSensorTest value1, WaterSensorTest value2) throws Exception {
                        System.out.println("reduce方法执行了");
                        return new WaterSensorTest(
                                value1.getId(),
                                value1.getTs(),
                                value1.getVc()+value2.getVc());
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
