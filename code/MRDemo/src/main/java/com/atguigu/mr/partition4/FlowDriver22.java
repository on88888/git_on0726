package com.atguigu.mr.partition4;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;
//虚拟机选项：-DHADOOP_USER_NAME=atguigu
//程序参数： hdfs://hadoop102:9820/input4 hdfs://hadoop102:9820/output4
public class FlowDriver22 {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        //1.创建Job实例
        Configuration conf = new Configuration();
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

        Job job = Job.getInstance(conf);
        //虚拟机选项：-DHADOOP_USER_NAME=atguigu
        //程序参数： hdfs://hadoop102:9820/input4 hdfs://hadoop102:9820/output4
        job.setJar("D:\\develop\\hadoop\\MRDemo\\target\\MRDemo-1.0-SNAPSHOT.jar");

        job.setPartitionerClass(MyPartitioner.class);

        job.setNumReduceTasks(5);

        job.setMapperClass(FlowMapper.class);
        job.setReducerClass(FlowReducer.class);

        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(FlowBean.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputKeyClass(FlowBean.class);

        FileInputFormat.setInputPaths(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));

        job.waitForCompletion(true);


    }
}
