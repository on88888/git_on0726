package text.t2;

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
                KEYOUT ：写出的key的类型（在这就是手机号）
                VALUEOUT ：写出的value的类型（在这就是FlowBean）


手机号1，对象（upflow,downflow,sumflow）
手机号1，对象 (upflow,downflow,sumflow)
 */
public class TFReducer extends Reducer<Text,TFBean,Text,TFBean> {
    /**
     * 在ReduceTask中执行的业务逻辑代码就写在reduce方法中
     *    注意：该方法在被循环调用，每调用一次读取一组数据（key值相同为一组）
     * @param key 读取的key（手机号）
     * @param values 读取的所有的value（FlowBean）
     * @param context 上下文（在这用来将key,value写出去）
     * @throws IOException
     * @throws InterruptedException
     */


    @Override
    protected void reduce(Text key, Iterable<TFBean> values, Context context) throws IOException, InterruptedException {
        long sumUpFlow = 0;
        long sumDownFlow = 0;

        for (TFBean value : values) {
            sumUpFlow += value.getUpFlow();
            sumDownFlow += value.getDownFlow();
        }

        TFBean outValue = new TFBean(sumUpFlow, sumDownFlow);
        context.write(key, outValue);

    }

    //总上行流量
    //总下行流量
    //1.遍历所有的value
    //2.累加上行流量，下行流量，总流量
    //2.封装K,V
    //3.写出k,v
}
