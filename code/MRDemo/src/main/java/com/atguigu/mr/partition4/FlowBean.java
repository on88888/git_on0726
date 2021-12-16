package com.atguigu.mr.partition4;


import org.apache.hadoop.io.Writable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/*
    让该类的对象可以序列化
    1.自定义一个类并实现Writable接口
    2.重写write和readFields方法
    3.write方法 ： 序列化时调用此方法
    4.readFields方法 ：反序列化时调用此方法
        注意：反序列化反数据的顺序和系列化时数据的顺序得保持一致。
 */
public class FlowBean implements Writable {
    private long upFlow;
    private long downFlow;
    private long sumFlow;

    /*
        如果类是一个JavaBean必须有一个空参构造器。
            因为所有的JavaBean的对象在使用中都是通过框架来创建对象的。
            框架会通过反射调用空参构造器创建对象。
            如果没有空参构造器那么就会创建对象失败。
     */
    public FlowBean(){

    }

    public FlowBean(long upFlow, long downFlow) {
        this.upFlow = upFlow;
        this.downFlow = downFlow;
        sumFlow = upFlow + downFlow;
    }

    /*
            序列化时调用此方法
         */
    public void write(DataOutput out) throws IOException {
        out.writeLong(upFlow);
        out.writeLong(downFlow);
        out.writeLong(sumFlow);
    }

    /*
        反序列化时调用此方法
        注意：反序列化反数据的顺序和系列化时数据的顺序得保持一致。
     */
    public void readFields(DataInput in) throws IOException {
        upFlow = in.readLong();
        downFlow = in.readLong();
        sumFlow = in.readLong();
    }

    public long getUpFlow() {
        return upFlow;
    }

    public void setUpFlow(long upFlow) {
        this.upFlow = upFlow;
    }

    public long getDownFlow() {
        return downFlow;
    }

    public void setDownFlow(long downFlow) {
        this.downFlow = downFlow;
    }

    public long getSumFlow() {
        return sumFlow;
    }

    public void setSumFlow(long sumFlow) {
        this.sumFlow = sumFlow;
    }

    @Override
    public String toString() {
        return upFlow + " " + downFlow + " " + sumFlow;
    }
}

