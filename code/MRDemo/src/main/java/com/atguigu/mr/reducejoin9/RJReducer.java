package com.atguigu.mr.reducejoin9;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.Iterator;

public class RJReducer extends Reducer<OrderBean, NullWritable,OrderBean,NullWritable> {

    /*
                            key                     value
              null   1     null     小米             null
              1001   1     1 　     null             null
              1004   1     4        null             null

            1.key的值随着value的值的变化而变化。
     */
    @Override
    protected void reduce(OrderBean key, Iterable<NullWritable> values, Context context) throws IOException, InterruptedException {
        //获取迭代器对象
        Iterator<NullWritable> iterator = values.iterator();
        //指针下移
        iterator.next();
        //获取第一条数据pname
        String pname = key.getPname();
        while(iterator.hasNext()){
            iterator.next();//指针下移
            //替换第二条数据开始的所有的pname
            key.setPname(pname);
            //写出K,V
            context.write(key,NullWritable.get());
        }
    }
}
