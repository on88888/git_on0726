package test.day02.chapter05.transfrom;

import org.apache.flink.api.java.functions.KeySelector;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

/**
 * @author on
 * @Description TODO
 * @since 2021年12月10日 19:23:37
 */
public class KeyByTest {
    public static void main(String[] args) {
        Configuration conf = new Configuration();
        conf.setInteger("rest.port", 10000);
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.setParallelism(2);

        DataStreamSource<Integer> ds = env.fromElements(1, 2, 3, 4, 5, 6, 7, 8);

        ds.keyBy(new KeySelector<Integer, String>() {
                    @Override
                    public String getKey(Integer value) throws Exception {
                        if (value % 2 == 0) {
                            return "偶数";
                        } else {
                            return "奇数";
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
