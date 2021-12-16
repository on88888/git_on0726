package com.atguigu.mr.partition4;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;

/*
    自定义分区

    Partitioner<KEY, VALUE>：
        KEY : map输出的key的类型
        VALUE : map输出的value的类型
 */
public class MyPartitioner extends Partitioner<Text, FlowBean> {
    /**
     * 返回分区号
     * @param text map写出的key(在这是手机号)
     * @param flowBean map写出的value(在这是FlowBean)
     * @param numPartitions reduceTask的数量
     * @return
     *
     * 手机号136、137、138、139开头都分别放到一个独立的4个文件中，其他开头的放到一个文件中。
     */
    public int getPartition(Text text, FlowBean flowBean, int numPartitions) {
        String phoneNumber = text.toString();
        if (phoneNumber.startsWith("136")){
            return 0;
        }else if (phoneNumber.startsWith("137")){
            return 1;
        }else if (phoneNumber.startsWith("138")){
            return 2;
        }else if (phoneNumber.startsWith("139")){
            return 3;
        }else {
            return 4;
        }

    }
}
