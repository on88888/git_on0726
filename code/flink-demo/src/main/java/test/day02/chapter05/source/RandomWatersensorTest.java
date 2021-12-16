package test.day02.chapter05.source;

import org.apache.flink.streaming.api.functions.source.SourceFunction;
import test.day02.bean.WaterSensorTest;

import java.util.Calendar;
import java.util.Random;

/**
 * @author on
 * @Description TODO
 * @since 2021年12月10日 18:39:41
 */
public class RandomWatersensorTest implements SourceFunction<WaterSensorTest> {

    @Override
    public void run(SourceContext<WaterSensorTest> ctx) throws Exception {
        Random random = new Random();
        while (true){
            ctx.collect(new WaterSensorTest(
                    "sensor" + random.nextInt(50),
                    Calendar.getInstance().getTimeInMillis(),
                    random.nextInt(100)
            ));
            Thread.sleep(1000);
        }
    }

    @Override
    public void cancel() {

    }
}
