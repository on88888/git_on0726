package text.t1;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/*
     该类是在ReduceTask中执行的（ReduceTask执行的业务逻辑代码）。

      Reducer<KEYIN,VALUEIN,KEYOUT,VALUEOUT>:
         两组：
            第一组：
                KEYIN ：读取的key的类型（Mapper输出的Key的类型）
                VALUEIN ：读取的value的类型（Mapper输出的value的类型）

           第二组：
                KEYOUT ：写出的key的类型（在这就是单词的类型）
                VALUEOUT ：写出的value的类型（在这就是单词的数量的量型）

 */
public class TReducer extends Reducer<Text, IntWritable,Text,IntWritable> {
    //封装k,v
    IntWritable outValue = new IntWritable();
    /**
     * 在ReduceTask中执行的业务逻辑代码就写在reduce方法中
     *    注意：该方法在被循环调用，每调用一次读取一组数据（key值相同为一组）
     * @param key 读取的key（单词）
     * @param values 读取的所有的value
     * @param context 上下文（在这用来将key,value写出去）
     * @throws IOException
     * @throws InterruptedException
     */
    @Override
    protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
        int sum = 0;
        for (IntWritable value : values) {

            sum += value.get();
        }
        outValue.set(sum);
        context.write(key, outValue);
    }
    //value的值累加的和
    //1.遍历所有的value（遍历key相同的一组）
    //2.将所有的value进行累加
    //2.1将IntWritable变成int类型
    //2.2将value进行累加
    //3.给value赋值
    //4.将key,value写出去（key相同的一组）
}
