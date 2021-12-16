package com.atguigu.day03.chapter06;

import com.atguigu.day03.bean.UserBehavior;
import org.apache.flink.api.common.functions.FilterFunction;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.ProcessFunction;
import org.apache.flink.util.Collector;

/**
 * @author Hefei
 * @description
 * @project_name flink-0726
 * @package_name com.atguigu.chapter06
 * @since 2021/12/11 14:22
 */
public class Flink02_PV_Process {
    public static void main(String[] args) {
        Configuration conf = new Configuration();
        conf.setInteger("rest.port", 10002);
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment(conf);
        env.setParallelism(1);

        env
                .readTextFile("input/UserBehavior.csv")
                .map(new MapFunction<String, UserBehavior>() {
                    @Override
                    public UserBehavior map(String line) throws Exception {
                        String[] datas = line.split(",");
                        return new UserBehavior(
                                Long.valueOf(datas[0]),
                                Long.valueOf(datas[1]),
                                Integer.valueOf(datas[2]),
                                datas[3],
                                Long.valueOf(datas[4])
                        );
                    }
                })
                .filter(new FilterFunction<UserBehavior>() {
                    @Override
                    public boolean filter(UserBehavior value) throws Exception {
                        return "pv".equals(value.getBehavior());
                    }
                })
                .process(new ProcessFunction<UserBehavior, Long>() {
                    long sum = 0L;
                    @Override
                    public void processElement(UserBehavior userBehavior,
                                               Context ctx,
                                               Collector<Long> out) throws Exception {
                        sum++;
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