package com.atguigu.mr.comparable5;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/*
    Reducer在这的作用：就是将读的k,v交换顺序。
        注意:读的k,v类型不能改变，写的类型可以随意指定
 */
public class FlowReducer extends Reducer<FlowBean, Text,Text,FlowBean> {
    @Override
    protected void reduce(FlowBean key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
        for (Text value : values) {
            context.write(value,key);
        }
    }
}
