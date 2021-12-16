package com.atguigu.mr.partition4;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

public class FlowDriver {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        Job job = Job.getInstance(new Configuration());

        //设置自定义分区类
        job.setPartitionerClass(MyPartitioner.class);
        //设置ReduceTask的数量
        /*
            默认情况下 ： 分区的数量 = reduceTask的数量
                        分区的数量 < reduceTask的数量
                        分区的数量 > reduceTask的数量 : 不可以会报错
         */
        job.setNumReduceTasks(5);

        job.setMapperClass(FlowMapper.class);
        job.setReducerClass(FlowReducer.class);
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(FlowBean.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(FlowBean.class);
        FileInputFormat.setInputPaths(job,new Path("D:\\io\\input4"));
        FileOutputFormat.setOutputPath(job,new Path("D:\\io\\output4"));


        job.waitForCompletion(true);
    }
}
