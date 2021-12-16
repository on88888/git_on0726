package com.atguigu.mr.partition;

import org.apache.hadoop.io.IntWritable;
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
                    KEYOUT ：写出的key的类型（在这是单词）
                    VALUEOUT ：写出的value的类型（在这是单词的数量）
 */
public class WCMapper extends Mapper<LongWritable, Text,Text, IntWritable> {
    //封装key--单词
    private Text outKey = new Text();
    //封装value--单词的数量
    private IntWritable outValue = new IntWritable();
    /**
     * 在MapTask中执行的业务逻辑代码就写在map方法中
     *  注意：map方法在被 循环调用每调用一次传一行数据。
     * @param key 读取数据时的偏移量
     * @param value 读取的一行一行的内容
     * @param context 上下文（在这是用来将key,Value写出去）
     * @throws IOException
     * @throws InterruptedException
     */
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        //super.map(key, value, context);一定不要写
        //1.将Text转成String
        String line = value.toString();
        //2.将数据切割（因为一行中可能有好多的单词）
        String[] words = line.split(" ");
        //3.封装K,V
        for (String word:words) {
            //给key赋值
            outKey.set(word);
            //给value赋值
            outValue.set(1);
            //4.写出k,v
            context.write(outKey,outValue);
        }

    }

}
