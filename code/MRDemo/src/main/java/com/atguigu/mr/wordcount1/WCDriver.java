package com.atguigu.mr.wordcount1;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

/*
    大家常犯错误：
        1.导包错误（ClassCastException）
        2.在设置Mapper输出的类型和最终输出的类型设置错误（ClassCastException）

 */
public class WCDriver {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        //1.创建Job实例
        Configuration conf = new Configuration();//配置文件
        Job job = Job.getInstance(conf);

        //2.给Job赋值
        //2.1设置Jar加载路径(如果是本地模式可以不设置)
        job.setJarByClass(WCDriver.class);
        //2.2设置Mapper和Reducer类
        job.setMapperClass(WCMapper.class);
        job.setReducerClass(WCReducer.class);
        //2.3设置Mapper输出的key,value的类型
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);
        //2.4设置最终输出的key,value的类型（在这是Reducer输出的key,value的类型）
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);
        //2.5设置输入和输出数据的路径
        FileInputFormat.setInputPaths(job,new Path("D:\\io\\input1"));
        //注意：输出路径一定不能存在
        FileOutputFormat.setOutputPath(job,new Path("D:\\io\\output1"));


        //3.提交Job
        /*
         boolean waitForCompletion(boolean verbose)
         verbose : 打印进度
         返回值 ：如果Job执行成功返回true,否则返回false
         */
        boolean b = job.waitForCompletion(true);
        //JVM退出的状态：0正常退出 1非正常退出
        //System.exit(b? 0:1);
    }
}
