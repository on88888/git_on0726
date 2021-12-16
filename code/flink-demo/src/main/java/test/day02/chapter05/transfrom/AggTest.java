package test.day02.chapter05.transfrom;

import org.apache.flink.api.java.functions.KeySelector;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import test.day02.bean.WaterSensorTest;
import test.day02.chapter05.source.RandomWatersensorTest;

/**
 * @author on
 * @Description TODO
 * @since 2021年12月10日 19:41:29
 */
public class AggTest {
    public static void main(String[] args) {
        Configuration conf = new Configuration();
        conf.setInteger("rest.port", 10000);
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.setParallelism(1);

        DataStreamSource<WaterSensorTest> WaterSensorDS = env.addSource(new RandomWatersensorTest());

        /*WaterSensorDS.keyBy(new KeySelector<WaterSensorTest, String>() {
                    @Override
                    public String getKey(WaterSensorTest value) throws Exception {
                        return value.getId();
                    }
                })
                .minBy("vc")
                .print();*/

        WaterSensorDS.keyBy(WaterSensorTest::getId) // :: 方法逻辑
                .minBy("vc", Boolean.TRUE)
                .print();

        try {
            env.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
