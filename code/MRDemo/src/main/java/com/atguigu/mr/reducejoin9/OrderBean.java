package com.atguigu.mr.reducejoin9;

import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class OrderBean implements WritableComparable<OrderBean> {

    private long id;
    private long pid;
    private long amount;
    private String pname;

    public OrderBean(){

    }

    public OrderBean(long id, long pid, long amount, String pname) {
        this.id = id;
        this.pid = pid;
        this.amount = amount;
        this.pname = pname;
    }

    /*
        先按照pid排序再按照pname排序
     */
    @Override
    public int compareTo(OrderBean o) {
        int comparePid = Long.compare(this.getPid(), o.getPid());
        if (comparePid == 0){
            return -this.pname.compareTo(o.getPname());
        }
        return comparePid;
    }
    //序列化时调用的方法
    @Override
    public void write(DataOutput out) throws IOException {
        out.writeLong(id);
        out.writeLong(pid);
        out.writeLong(amount);
        out.writeUTF(pname);
    }
    //反序列化时调用的方法-注意：反序列时的顺序和序列化时的顺序保持一致
    @Override
    public void readFields(DataInput in) throws IOException {
        id = in.readLong();
        pid = in.readLong();
        amount = in.readLong();
        pname = in.readUTF();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getPid() {
        return pid;
    }

    public void setPid(long pid) {
        this.pid = pid;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    @Override
    public String toString() {
        return id+ " " + pid + " " + pname + " " + amount;
    }
}
