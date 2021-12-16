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
    从本地向集群提交
    1.配置
         //设置在集群运行的相关参数-设置HDFS,NAMENODE的地址
        conf.set("fs.defaultFS", "hdfs://hadoop102:9820");
        //指定MR运行在Yarn上
        conf.set("mapreduce.framework.name","yarn");
        //指定MR可以在远程集群运行
        conf.set("mapreduce.app-submission.cross-platform","true");
        //指定yarn resourcemanager的位置
        conf.set("yarn.resourcemanager.hostname", "hadoop103");
    2.打包
    3.将
        job.setJarByClass(WCDriver3.class);注释掉
        添加
        job.setJar("jar包的路径")
    4.配置
        ①输入输出的路径 :
                Program Arguments:
                        hdfs://hadoop102:9820/input hdfs://hadoop102:9820/output
        ②设置操作HDFS的用户名
                  VM Options :
                        -DHADOOP_USER_NAME=atguigu
 */
public class WCDriver3 {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        //1.创建Job实例
        Configuration conf = new Configuration();//配置文件
        //指定提交到那个队列
        conf.set("mapred.job.queue.name", "hive");
        //设置在集群运行的相关参数-设置HDFS,NAMENODE的地址
        conf.set("fs.defaultFS", "hdfs://hadoop102:9820");
        //指定MR运行在Yarn上
        conf.set("mapreduce.framework.name","yarn");
        //指定MR可以在远程集群运行
        conf.set("mapreduce.app-submission.cross-platform","true");
        //指定yarn resourcemanager的位置
        conf.set("yarn.resourcemanager.hostname", "hadoop103");

        //虚拟机选项：-DHADOOP_USER_NAME=atguigu
        //程序参数： hdfs://hadoop102:9820/input1 hdfs://hadoop102:9820/output1

        Job job = Job.getInstance(conf);

        //2.给Job赋值
        //2.1设置Jar加载路径(如果是本地模式可以不设置)
        //job.setJarByClass(WCDriver3.class);
        //设置Jar包的路径
        job.setJar("D:\\develop\\hadoop\\MRDemo\\target\\MRDemo-1.0-SNAPSHOT.jar");

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
        FileInputFormat.setInputPaths(job,new Path(args[0]));
        //注意：输出路径一定不能存在
        FileOutputFormat.setOutputPath(job,new Path(args[1]));


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
