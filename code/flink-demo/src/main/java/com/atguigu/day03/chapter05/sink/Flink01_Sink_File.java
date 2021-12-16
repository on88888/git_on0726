package com.atguigu.day03.chapter05.sink;

import com.atguigu.day03.bean.WaterSensor;
import com.atguigu.day03.chapter05.source.RandomWatersensor;
import org.apache.flink.api.common.serialization.SimpleStringEncoder;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.core.fs.Path;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.sink.filesystem.StreamingFileSink;
import org.apache.flink.streaming.api.functions.sink.filesystem.rollingpolicies.DefaultRollingPolicy;

import java.util.concurrent.TimeUnit;

/**
 * @author Hefei
 * @description
 * @project_name flink-0726
 * @package_name com.atguigu.chapter05.sink
 * @since 2021/12/11 10:02
 */
public class Flink01_Sink_File {
    public static void main(String[] args) {
        Configuration conf = new Configuration();
        conf.setInteger("rest.port", 10000);
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment(conf);
        env.setParallelism(1);

        StreamingFileSink<String> fileSink = StreamingFileSink
                .forRowFormat(new Path("./output"),
                        new SimpleStringEncoder<String>("UTF-8"))
                .withRollingPolicy(DefaultRollingPolicy.builder()
                        .withRolloverInterval(TimeUnit.MINUTES.toMillis(15))
                        .withInactivityInterval(TimeUnit.MINUTES.toMillis(5))
                        .withMaxPartSize(1024)
                        .build())
                .build();

        env.addSource(new RandomWatersensor())
                .map(WaterSensor::toString)
                .addSink(fileSink);


        try {
            env.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}