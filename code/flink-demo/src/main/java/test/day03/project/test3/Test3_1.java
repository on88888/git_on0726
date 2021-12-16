package test.day03.project.test3;

import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import test.day03.project.test3.bean.AdsClickLog;

/**
 * @author on
 * @Description TODO
 * @since 2021年12月11日 15:16:10
 */
//日志数据AdClickLog.csv
//各省份页面广告点击量实时统计
public class Test3_1 {
    public static void main(String[] args) {
        Configuration conf = new Configuration();
        conf.setInteger("rest.port",10000);
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.setParallelism(2);

        env.readTextFile("input/AdClickLog.csv")
                .map(new MapFunction<String, AdsClickLog>() {
                    @Override
                    public AdsClickLog map(String value) throws Exception {
                        String[] s = value.split(",");
                        return new AdsClickLog(Long.valueOf(s[0]),
                                Long.valueOf(s[1]),
                                s[2],
                                s[3],
                                Long.valueOf(s[4]));
                    }
                })
                .map(new MapFunction<AdsClickLog, Tuple2<String,Long>>() {
                    @Override
                    public Tuple2<String, Long> map(AdsClickLog value) throws Exception {
                        return Tuple2.of(value.getProvince()+"\t"+value.getAdId(),1L);
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
