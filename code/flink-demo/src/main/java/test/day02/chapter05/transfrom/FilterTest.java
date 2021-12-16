package test.day02.chapter05.transfrom;

import org.apache.flink.api.common.functions.FilterFunction;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

/**
 * @author on
 * @Description TODO
 * @since 2021年12月10日 19:11:47
 */
public class FilterTest {
    public static void main(String[] args) {
        Configuration conf = new Configuration();
        conf.setInteger("rest.port", 10000);
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.setParallelism(1);

        DataStreamSource<Integer> ds = env.fromElements(1, 2, 3, 4, 5, 6, 7, 8);

        //过滤流中的偶数，只要偶数不要奇数
        ds.filter(new FilterFunction<Integer>() {
                    @Override
                    public boolean filter(Integer value) throws Exception {
                        if (value % 2 == 0) {
                            return true;
                        } else {
                            return false;
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
