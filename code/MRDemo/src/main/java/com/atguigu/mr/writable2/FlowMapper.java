package com.atguigu.mr.writable2;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/*
    该类是在MapTask中执行的（MapTask执行的业务逻辑代码）。

     Mapper<KEYIN, VALUEIN, KEYOUT, VALUEOUT>:
     泛型分两组：
            第一组 ：
                    KEYIN ：读取数据时的偏移量的类型
                    VALUEIN ：读取的一行一行的内容的类型
            第二组：
                    KEYOUT ：写出的key的类型（在这是手机号）
                    VALUEOUT ：写出的value的类型（在这是FlowBean）
 */
public class FlowMapper extends Mapper<LongWritable, Text,Text,FlowBean> {
    private Text outKey = new Text();
    private  FlowBean outValue = new FlowBean();
    /**
     * 在MapTask中执行的业务逻辑代码就写在map方法中
     *  注意：map方法在被 循环调用每调用一次传一行数据。
     * @param key 读取数据时的偏移量
     * @param value 读取的一行一行的内容
     * @param context 上下文（在这是用来将key,Value写出去）
     * @throws IOException
     * @throws InterruptedException
     *
     * 1	13736230513	192.196.100.1	www.atguigu.com	2481	24681	200
     * 2	13846544121	192.196.100.2			264	0	200
     */
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        //1.将Text转成String
        String line = value.toString();
        //2.切割数据
        String[] phoneInfo = line.split("\t");
        //3.封装K,V
        outKey.set(phoneInfo[1]);
        outValue.setUpFlow(Long.parseLong(phoneInfo[phoneInfo.length - 3]));
        outValue.setDownFlow(Long.parseLong(phoneInfo[phoneInfo.length - 2]));
        outValue.setSumFlow(outValue.getUpFlow()+outValue.getDownFlow());
        //4.写出K,V
        context.write(outKey,outValue);
    }
}
