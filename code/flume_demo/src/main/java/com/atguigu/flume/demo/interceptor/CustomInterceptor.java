package com.atguigu.flume.demo.interceptor;

import org.apache.flume.Context;
import org.apache.flume.Event;
import org.apache.flume.interceptor.Interceptor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @describe 需求： 对数据判断： 字母--》 type:letter  数字： type：number
 * @author leon
 * @ClassName CustomInterceptor.java
 * @createTime 2021年09月28日 14:14:00
 */
public class CustomInterceptor implements Interceptor {

    @Override
    public void initialize() {

    }

    /**
     * 单个事件的拦截
     * @param event
     * @return
     */
    @Override
    public Event intercept(Event event) {
        // 1. 获取事件中的头部和body
        Map<String, String> headers = event.getHeaders();
        byte[] body = event.getBody();
        // 2. 判断
        if(body[0] >= 'a' && body[0] <= 'z' || body[0] >= 'A' && body[0] <= 'Z'){
            // type:letter
            headers.put("type","letter");
        }
        if(body[0] >= '0' && body[0] <= '9' ){
            // type:number
            headers.put("type","number");
        }

        event.setHeaders(headers);
        return event;
    }

    /**
     * 对批量事件的拦截
     * @param events
     * @return
     */
    @Override
    public List<Event> intercept(List<Event> events) {
        // 1. 遍历
        for (Event event : events) {
            intercept(event);
        }
        return events;
    }

    @Override
    public void close() {

    }

    public static class Builder implements Interceptor.Builder {

        @Override
        public Interceptor build() {
            return new CustomInterceptor();
        }

        @Override
        public void configure(Context context) {

        }
    }


}
