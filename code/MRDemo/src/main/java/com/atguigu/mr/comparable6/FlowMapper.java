package com.atguigu.mr.comparable6;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/*
    注意 ： key为FlowBean(因为要排序)
 */
public class FlowMapper extends Mapper<LongWritable, Text, FlowBean,Text> {
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        //1.将Text转成String
        String line = value.toString();
        //2.切割数据
        String[] split = line.split("\t");
        //3.封装K,V
        FlowBean outKey = new FlowBean(Long.parseLong(split[1]),
                Long.parseLong(split[2]), Long.parseLong(split[3]));
        Text outValue = new Text(split[0]);
        //4.写出K,V
        context.write(outKey,outValue);
    }
}
