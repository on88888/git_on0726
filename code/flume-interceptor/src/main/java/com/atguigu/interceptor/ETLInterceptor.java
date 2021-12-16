package com.atguigu.interceptor;

import com.alibaba.fastjson.JSON;
import org.apache.flume.Context;
import org.apache.flume.Event;
import org.apache.flume.interceptor.Interceptor;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ETLInterceptor implements Interceptor {

    /**
     * 初始化
     */
    @Override
    public void initialize() {

    }

    /**
     * 针对单条数据进行拦截处理
     * @param event
     * @return
     */
    @Override
    public Event intercept(Event event) {

        //取出json数据
        String json = new String(event.getBody());
        try{

            JSON.parseObject(json);
            return event;
        }catch (Exception e){
            return null;
        }
    }

    /**
     * 针对一个批次的数据拦截处理
     *    过滤非json格式数据
     *          {"属性名":属性值,... }
     *          [元素,元素,..]
     * @param events
     * @return
     */
    @Override
    public List<Event> intercept(List<Event> events) {
/*        Iterator<Event> it = events.iterator();
        while (it.hasNext()){
            Event event = it.next();
            //非json数据 删除
            if( intercept(event) == null){
                it.remove();
            }
        }
        return events;*/

        ArrayList<Event> result = new ArrayList<>();
        for (Event event : events) {
            if (intercept(event)!=null){
                result.add(event);
            }
        }
        return result;
    }

    /**
     * 关闭
     */
    @Override
    public void close() {

    }


    public static class Builder implements Interceptor.Builder{
        //创建自定义拦截器对象,提供给flume使用
        @Override
        public Interceptor build() {
            return new ETLInterceptor();
        }

        @Override
        public void configure(Context context) {

        }
    }

}
