package com.atguigu.mr.inputformat3;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.CombineTextInputFormat;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;


public class WCDriver {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {

        Configuration conf = new Configuration();//配置文件
        Job job = Job.getInstance(conf);

        //设置虚拟切片的最大值
        CombineTextInputFormat.setMaxInputSplitSize(job,4194304);//4m
       // CombineTextInputFormat.setMaxInputSplitSize(job,4190000);//4m
        //设置InputFormat(如果不设置使用默认的InputFormat-TextInputFormat)
        job.setInputFormatClass(CombineTextInputFormat.class);

        job.setJarByClass(WCDriver.class);
        job.setMapperClass(WCMapper.class);
        job.setReducerClass(WCReducer.class);
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);
        FileInputFormat.setInputPaths(job,new Path("D:\\io\\input3"));
        FileOutputFormat.setOutputPath(job,new Path("D:\\io\\output3"));


        boolean b = job.waitForCompletion(true);

    }
}
