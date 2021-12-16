package test.day02.chapter05.test;

import org.apache.flink.api.common.functions.FilterFunction;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import test.day02.bean.WaterSensorTest;

import java.util.ArrayList;

/**
 * @author on
 * @Description TODO
 * @since 2021年12月10日 21:04:49
 */

//练习题：自己模拟数据，使用filter算子将WaterSensor类型的数据中vc值超过40的数据过滤出来并重新放回流中。
public class Test02_Filter {
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

        waterSensorDS.filter(new FilterFunction<WaterSensorTest>() {
            @Override
            public boolean filter(WaterSensorTest value) throws Exception {
                if (value.getVc() > 40){
                    return true;
                }
                else{
                    return false;
                }
            }
        })
                .print();

        try {
            env.execute("Test02_Filter");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
