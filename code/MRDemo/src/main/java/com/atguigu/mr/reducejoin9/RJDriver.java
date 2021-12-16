package com.atguigu.mr.reducejoin9;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

public class RJDriver {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        //1.创建Job对象
        Job job = Job.getInstance(new Configuration());

        //2.给Job设置值
        //指定自定义分组的类
        job.setGroupingComparatorClass(MyGroup.class);
        job.setMapperClass(RJMapper.class);
        job.setReducerClass(RJReducer.class);
        job.setMapOutputKeyClass(OrderBean.class);
        job.setMapOutputValueClass(NullWritable.class);
        job.setOutputKeyClass(OrderBean.class);
        job.setOutputValueClass(NullWritable.class);
        FileInputFormat.setInputPaths(job,new Path("D:\\io\\input9"));
        FileOutputFormat.setOutputPath(job,new Path("D:\\io\\output9"));

        //3.执行Job
        job.waitForCompletion(true);
    }
}
