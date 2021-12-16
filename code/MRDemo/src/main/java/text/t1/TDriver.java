package text.t1;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

public class TDriver {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf);

        job.setJarByClass(TDriver.class);
        job.setMapperClass(TMapper.class);
        job.setReducerClass(TReducer.class);
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

        FileInputFormat.setInputPaths(job, new Path("D:\\io\\input1"));
        FileOutputFormat.setOutputPath(job, new Path("D:\\io\\output1"));

        job.waitForCompletion(true);

    }
    //1.创建Job实例

    //2.给Job赋值
    //2.1设置Jar加载路径(如果是本地模式可以不设置)
    //2.2设置Mapper和Reducer类
    //2.3设置Mapper输出的key,value的类型
    //2.4设置最终输出的key,value的类型（在这是Reducer输出的key,value的类型）
    //2.5设置输入和输出数据的路径
    //注意：输出路径一定不能存在
    //3.提交Job
        /*
         boolean waitForCompletion(boolean verbose)
         verbose : 打印进度
         返回值 ：如果Job执行成功返回true,否则返回false
         */
    //JVM退出的状态：0正常退出 1非正常退出
    //System.exit(b? 0:1);
}
