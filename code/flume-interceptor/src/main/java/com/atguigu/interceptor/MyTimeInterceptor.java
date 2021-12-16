package com.atguigu.interceptor;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.flume.Context;
import org.apache.flume.Event;
import org.apache.flume.interceptor.Interceptor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MyTimeInterceptor implements Interceptor {

    @Override
    public void initialize() {

    }

    @Override
    public Event intercept(Event event) {

        //1、获取json数据
        String json = new String(event.getBody());
        //2、取出数据的时间
        JSONObject jsonObject = JSON.parseObject(json);
        Long ts = jsonObject.getLong("ts");
        //3、将数据时间添加到header中
        Map<String, String> headers = event.getHeaders();
        headers.put("timestamp",ts+"");

        //event.setHeaders(headers);
        //4、数据返回
        return event;
    }

    @Override
    public List<Event> intercept(List<Event> events) {
        List<Event> lists = new ArrayList<Event>();
        for(Event event: events){
            lists.add(intercept(event));
        }
        return lists;
    }

    @Override
    public void close() {

    }

    public static class Builder implements Interceptor.Builder{
        //创建自定义拦截器对象,提供给flume使用
        @Override
        public Interceptor build() {
            return new MyTimeInterceptor();
        }

        @Override
        public void configure(Context context) {

        }
    }
}
