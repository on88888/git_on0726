package com.atguigu.mr.outputformat8;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

public class AddressDriver {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        Job job = Job.getInstance(new Configuration());

        //设置自定义OutputFormat类（不设置使用的是默认的TextOutputFormat）
        job.setOutputFormatClass(MyOutputFormat.class);
        job.setOutputKeyClass(LongWritable.class);
        job.setOutputValueClass(Text.class);
        //不运行ReduceTask---如果不运行ReduceTask将不能排序。
        job.setNumReduceTasks(0);


        FileInputFormat.setInputPaths(job,new Path("D:\\io\\input8"));
        FileOutputFormat.setOutputPath(job,new Path("D:\\io\\output8"));

        job.waitForCompletion(true);
    }
}
