package com.atguigu.day03.chapter05.source;

import com.atguigu.day03.bean.WaterSensor;
import org.apache.flink.streaming.api.functions.source.SourceFunction;

import java.util.Calendar;
import java.util.Random;

/**
 * @author Hefei
 * @description
 * @project_name flink-0726
 * @package_name com.atguigu.chapter05.source
 * @since 2021/12/10 11:33
 */
public class RandomWatersensor implements SourceFunction<WaterSensor> {
    private Boolean running = true;

    @Override
    public void run(SourceContext<WaterSensor> ctx) throws Exception {
        Random random = new Random();
        while (running) {
            ctx.collect(new WaterSensor(
                    "sensor" + random.nextInt(50),
                    Calendar.getInstance().getTimeInMillis(),
                    random.nextInt(100)
            ));
            Thread.sleep(200);
        }
    }

    @Override
    public void cancel() {

    }

}