package com.atguigu.mr.reducejoin9;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

/*
    自定义分组方式：
    1.自定义一个类并继承WritableComparator
    2.调用父类构造器
    3.重写方法 ： public int compare(WritableComparable a, WritableComparable b)
            在该方法指定分组的属性。
     注意：默认的分组方式和排序方式相同。
 */
public class MyGroup extends WritableComparator {
    /*
        调用父类构造器
     */
    public MyGroup(){
        //调用父类中的构造器
        /*
        WritableComparator(Class<? extends WritableComparable> keyClass,boolean createInstances) {
        keyClass : key值的类的运行时类。
        createInstances : 是否创建实例
         */
        super(OrderBean.class,true);
    }

    /*
        按照指定的属性进行分组：
        按照pid进行分组
     */
    @Override
    public int compare(WritableComparable a, WritableComparable b) {
        //向下转型
        OrderBean o1 = (OrderBean) a;
        OrderBean o2 = (OrderBean) b;
        return Long.compare(o1.getPid(),o2.getPid());
    }
}
