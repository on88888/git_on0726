package com.atguigu.mr.outputformat8;

import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.RecordWriter;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

public class MyRecordWriter extends RecordWriter<LongWritable, Text> {
    private FSDataOutputStream atguigu;
    private FSDataOutputStream other;

    /*
        创建流
     */
    public MyRecordWriter(TaskAttemptContext job) {

        FileSystem fs = null;

        try {
            //创建文件系统对象
            fs = FileSystem.get(job.getConfiguration());
            //获取输出路径
            Path outputPath = FileOutputFormat.getOutputPath(job);
            //创建流
            atguigu = fs.create(new Path(outputPath, "atguigu.txt"));
            other = fs.create(new Path(outputPath, "other.txt"));
        } catch (Exception e) {
            //终止程序的运行
            throw new RuntimeException(e.getMessage());
        } finally {
            try {
                if (fs != null) {
                    fs.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * 该方法就是用来写数据的
     * 注意：该方法在被循环调用每调用一次传一行内容
     *
     * @param key   偏移量
     * @param value 一行一行的内容
     * @throws IOException
     * @throws InterruptedException
     */
    @Override
    public void write(LongWritable key, Text value) throws IOException, InterruptedException {
        //1.将Text转成String
        String address = value.toString() + "\n";
        //2.判断是否包含atguigu
        if (address.contains("atguigu")) {//写到atguigu.txt
            atguigu.write(address.getBytes());
        } else {//写到other.txt
            other.write(address.getBytes());
        }
    }

    /*
        该方法中用来关闭资源
     */
    @Override
    public void close(TaskAttemptContext context) throws IOException, InterruptedException {
        IOUtils.closeStream(atguigu);
        IOUtils.closeStream(other);
    }
}
