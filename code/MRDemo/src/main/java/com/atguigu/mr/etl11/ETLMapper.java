package com.atguigu.mr.etl11;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Counter;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class ETLMapper extends Mapper<LongWritable, Text,Text, NullWritable> {
    private  Counter success;
    private  Counter fail;
    //创建计数器对象
    @Override
    protected void setup(Context context) throws IOException, InterruptedException {
        /*
        getCounter(String groupName, String counterName);
        groupName : 组名（随便写）
        counterName : 计数器的名字（随便写）
         */
        success = context.getCounter("ETL", "success");
        fail = context.getCounter("ETL", "fail");
    }


    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        //1.将Text转成String
        String line = value.toString();
        //2.切割
        String[] split = line.split(" ");
        //3.判断
        if (split.length > 11){
            success.increment(1);//加1
            context.write(value,NullWritable.get());
        }else{
            fail.increment(1);//加1
        }
    }
}
