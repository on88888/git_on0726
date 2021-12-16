package test.day03.project.test2;

import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import test.day03.project.test2.bean.MarketingUserBehavior;

/**
 * @author on
 * @Description TODO
 * @since 2021年12月11日 15:08:46
 */

//APP市场推广统计-行为
public class Test2_2 {
    public static void main(String[] args) {
        Configuration conf = new Configuration();
        conf.setInteger("rest.port",10000);
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.setParallelism(1);

        env.addSource(new MockAppDataSource())
                .map(new MapFunction<MarketingUserBehavior, Tuple2<String ,Long>>() {
                    @Override
                    public Tuple2<String, Long> map(MarketingUserBehavior value) throws Exception {
                        return Tuple2.of(value.getBehavior(), 1L);
                    }
                })
                .keyBy(value -> value.f0)
                .sum(1)
                .print();

        try {
            env.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
