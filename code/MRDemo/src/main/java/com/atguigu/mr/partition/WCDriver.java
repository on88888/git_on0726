package com.atguigu.mr.partition;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;


public class WCDriver {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {

        Configuration conf = new Configuration();//配置文件
        Job job = Job.getInstance(conf);

        //设置ReduceTask的数量
        //job.setNumReduceTasks(2);

        job.setJarByClass(WCDriver.class);
        job.setMapperClass(WCMapper.class);
        job.setReducerClass(WCReducer.class);
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);
        FileInputFormat.setInputPaths(job,new Path("D:\\io\\input"));
        FileOutputFormat.setOutputPath(job,new Path("D:\\io\\output4"));


        boolean b = job.waitForCompletion(true);

    }
}
