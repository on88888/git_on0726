package com.atguigu.mr.mapjoin10;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class MJDriver {
    public static void main(String[] args) throws IOException, URISyntaxException, ClassNotFoundException, InterruptedException {
        Job job = Job.getInstance(new Configuration());

        //添加缓存文件的路径
        job.addCacheFile(new URI("file:///D:/io/input10/pd.txt"));
        job.setMapperClass(MJMapper.class);
        job.setMapOutputKeyClass(OrderBean.class);
        job.setMapOutputValueClass(NullWritable.class);
        job.setOutputKeyClass(OrderBean.class);
        job.setOutputValueClass(NullWritable.class);
        job.setNumReduceTasks(0);
        FileInputFormat.setInputPaths(job,
                new Path("D:\\io\\input10\\order.txt"));
        FileOutputFormat.setOutputPath(job,new Path("D:\\io\\output10"));

        job.waitForCompletion(true);
    }
}
