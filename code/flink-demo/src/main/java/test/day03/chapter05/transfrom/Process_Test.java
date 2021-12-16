package test.day03.chapter05.transfrom;

import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.ProcessFunction;
import org.apache.flink.util.Collector;
import test.day02.bean.WaterSensorTest;
import test.day02.chapter05.source.RandomWatersensorTest;

/**
 * @author on
 * @Description TODO
 * @since 2021年12月11日 11:25:06
 */

public class Process_Test {
    public static void main(String[] args) {

        Configuration conf = new Configuration();
        conf.setInteger("rest.port", 10000);
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.setParallelism(2);

        DataStreamSource<WaterSensorTest> waterSensorDS = env.addSource(new RandomWatersensorTest());

        waterSensorDS.process(new ProcessFunction<WaterSensorTest, Integer>() {
                    int sum = 0;

                    @Override
                    public void processElement(WaterSensorTest value,
                                               ProcessFunction<WaterSensorTest, Integer>.Context ctx,
                                               Collector<Integer> out) throws Exception {
                        sum += value.getVc();
                        value.setVc(sum);
                        //System.out.println("ctx = " + ctx);
                        out.collect(sum);
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
