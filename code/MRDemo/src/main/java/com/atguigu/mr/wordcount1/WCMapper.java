package com.atguigu.mr.wordcount1;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 *
 * @author AllenWoon
 *
 * Mapper<KEYIN, VALUEIN, KEYOUT, VALUEOUT>
 * KEYIN：是指框架读取到的数据的key类型
 *  在默认的读取数据组件InputFormat下，读取的key是一行文本的偏移量，所以key的类型是long类型的
 *
 * VALUEIN指框架读取到的数据的value类型
 *  在默认的读取数据组件InputFormat下，读到的value就是一行文本的内容，所以value的类型是String类型的
 *
 * keyout是指用户自定义逻辑方法返回的数据中key的类型 这个是由用户业务逻辑决定的。
 *  在我们的单词统计当中，我们输出的是单词作为key，所以类型是String
 *
 * VALUEOUT是指用户自定义逻辑方法返回的数据中value的类型 这个是由用户业务逻辑决定的。
 *  在我们的单词统计当中，我们输出的是单词数量作为value，所以类型是Integer
 *
 * 但是，String ,Long都是jdk中自带的数据类型，在序列化的时候，效率比较低。hadoop为了提高序列化的效率，他就自己自定义了一套数据结构。
 *
 * 所以说在我们的hadoop程序中，如果该数据需要进行序列化（写磁盘，或者网络传输），就一定要用实现了hadoop序列化框架的数据类型
 *
 *
 * Long------->LongWritable
 * String----->Text
 * Integer---->IntWritable
 * null------->nullWritable
 *
 */

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
