package com.atguigu.mr.reducejoin9;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;

import java.io.IOException;

public class RJMapper extends Mapper<LongWritable, Text, OrderBean, NullWritable> {
    //文件名
    private String fileName;

    /*
        在任务开始的时候只执行一次，在map方法执行前执行
        适合做一些初始化的操作
     */
    @Override
    protected void setup(Context context) throws IOException, InterruptedException {
        //通过切片信息获取
        FileSplit fileSplit = (FileSplit) context.getInputSplit();
        fileName = fileSplit.getPath().getName();
    }

    /*
        在任务结束的时候只执行一次，在map方法执行后执行
        适合做一些关闭资源的操作
     */
    @Override
    protected void cleanup(Context context) throws IOException, InterruptedException {

    }

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        //1.将Text转成String
        String line = value.toString();
        //2.切割
        String[] orderInfo = line.split("\t");
        //3.封装
        OrderBean orderBean = new OrderBean();
        if ("order.txt".equals(fileName)){
            orderBean.setId(Long.parseLong(orderInfo[0]));
            orderBean.setPid(Long.parseLong(orderInfo[1]));
            orderBean.setAmount(Long.parseLong(orderInfo[2]));
            orderBean.setPname("");//panme的值不能为null否则会报错（原因就是要排序）。
        }else if("pd.txt".equals(fileName)){
            orderBean.setPid(Long.parseLong(orderInfo[0]));
            orderBean.setPname(orderInfo[1]);
        }
        //4.写出K,V
        context.write(orderBean,NullWritable.get());
    }
}
