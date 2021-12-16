package test.day03.chapter05.sink;

import org.apache.flink.api.common.serialization.SimpleStringEncoder;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.core.fs.Path;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.sink.filesystem.StreamingFileSink;
import org.apache.flink.streaming.api.functions.sink.filesystem.rollingpolicies.DefaultRollingPolicy;
import test.day02.bean.WaterSensorTest;
import test.day02.chapter05.source.RandomWatersensorTest;

import java.util.concurrent.TimeUnit;

/**
 * @author on
 * @Description TODO
 * @since 2021年12月11日 11:46:44
 */
public class Sink_File_Test {
    public static void main(String[] args) {

        Configuration conf = new Configuration();
        conf.setInteger("rest.port", 10000);
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.setParallelism(2);

        StreamingFileSink<String> fileSink = StreamingFileSink
                .forRowFormat(new Path("./output"),
                        new SimpleStringEncoder<String>("UTF-8"))
                .withRollingPolicy(DefaultRollingPolicy.builder()
                        .withRolloverInterval(TimeUnit.MINUTES.toMillis(15))
                        .withInactivityInterval(TimeUnit.MINUTES.toMillis(5))
                        .withMaxPartSize(1024 * 1024 * 1024)
                        .build())
                .build();

        env.addSource(new RandomWatersensorTest())
                .map(WaterSensorTest::toString)
                .addSink(fileSink);

        try {
            env.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
