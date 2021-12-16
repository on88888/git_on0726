package com.atguigu.flume.demo.sink;

import org.apache.flume.*;
import org.apache.flume.conf.Configurable;
import org.apache.flume.sink.AbstractSink;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.StandardCharsets;

/**
 * @author leon
 * @ClassName CustomSink.java
 * @createTime 2021年09月28日 15:33:00
 */
public class CustomSink extends AbstractSink implements Configurable {

    private static  final Logger logger = LoggerFactory.getLogger(CustomSink.class);

    @Override
    public void configure(Context context) {

    }

    /**
     * 主要的业务逻辑：
     * @return
     * @throws EventDeliveryException
     */
    @Override
    public Status process() throws EventDeliveryException {
        Status status;

        // 1.获取channel
        Channel channel = getChannel();
        // 2. 获取事务
        Transaction transaction = channel.getTransaction();
        // 3. 声明一个事件
        Event event;

        // 3.5 开启事务
        transaction.begin();

        try {
            while (true){
                event = channel.take();
                if(event != null)
                    break;
            }

            // 4. 获取事件中的数据
            String message = new String(event.getBody(), StandardCharsets.UTF_8);
            // 5. 包装为flume的日志
            logger.info(message);
            status = Status.READY;
            transaction.commit();
        } catch (ChannelException e) {
            status = Status.BACKOFF;
            transaction.rollback();
            e.printStackTrace();
        } finally {
            transaction.close();
        }

        return status;
    }


}
