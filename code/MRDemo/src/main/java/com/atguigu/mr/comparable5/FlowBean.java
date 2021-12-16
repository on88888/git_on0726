package com.atguigu.mr.comparable5;


import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/*
    自定义类的对象可以作为key进行排序。
    1.那么该类需要实现WritableComparable接品
    2.WritableComparable 继承了 Writable和Comparable接口
    3.重写Writable和Comparable接口中的方法
 */
public class FlowBean implements WritableComparable<FlowBean> {
    private long upFlow;
    private long downFlow;
    private long sumFlow;


    public FlowBean(){

    }

    public FlowBean(long upFlow, long downFlow, long sumFlow) {
        this.upFlow = upFlow;
        this.downFlow = downFlow;
        this.sumFlow = sumFlow;
    }

    /*
        用来指定按照哪个属性进行排序
        按照总流量进行排序
     */
    public int compareTo(FlowBean o) {
        return Long.compare(this.sumFlow,o.getSumFlow());
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

