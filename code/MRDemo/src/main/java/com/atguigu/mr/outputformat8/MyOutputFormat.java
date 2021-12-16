package com.atguigu.mr.outputformat8;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.RecordWriter;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

/*
    自定义OutputFormat

    FileOutputFormat<K, V> :
        泛型 ：因为没有Reducer和Mapper所以就是InputFormat输出的k,v类型
 */
public class MyOutputFormat extends FileOutputFormat<LongWritable, Text> {

    @Override
    public RecordWriter<LongWritable, Text> getRecordWriter(TaskAttemptContext job) throws IOException, InterruptedException {
        return new MyRecordWriter(job);
    }
}
