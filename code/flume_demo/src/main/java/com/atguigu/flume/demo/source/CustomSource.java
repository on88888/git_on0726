package com.atguigu.flume.demo.source;

import org.apache.flume.Context;
import org.apache.flume.EventDeliveryException;
import org.apache.flume.PollableSource;
import org.apache.flume.conf.Configurable;
import org.apache.flume.event.SimpleEvent;
import org.apache.flume.source.AbstractSource;

import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;

/**
 * @author leon
 * @ClassName CustomSource.java
 * @createTime 2021年09月28日 15:06:00
 */
public class CustomSource extends AbstractSource implements Configurable, PollableSource {

    private String prefix;
    private String suffix;
    /**
     * 读取配置文件中的参数
     * @param context
     */
    @Override
    public void configure(Context context) {
        prefix = context.getString("prefix", "welcome!");
        suffix = context.getString("suffix");

    }

    /**
     * 主要的业务逻辑
     * @return
     * @throws EventDeliveryException
     */
    @Override
    public Status process() throws EventDeliveryException {
        SimpleEvent event = new SimpleEvent();
        try {
            // 1. 生产数据
            for(int i=1; i<=100000; i++){
                String str = "我是"+i+"号技师,";
                String message = prefix +str+suffix;
                // 2. 包装Event
                event.setBody(message.getBytes(StandardCharsets.UTF_8));
                //3. 发送
                getChannelProcessor().processEvent(event);
                TimeUnit.SECONDS.sleep(1);
            }
        } catch (Exception e) {
            return Status.BACKOFF;
        }
        return Status.READY;
    }


    @Override
    public long getBackOffSleepIncrement() {
        return 0;
    }

    @Override
    public long getMaxBackOffSleepInterval() {
        return 0;
    }


}
