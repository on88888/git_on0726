package com.atguigu.hive.demo.UDF;

import org.apache.hadoop.hive.ql.exec.UDF;
import org.apache.hadoop.hive.ql.exec.UDFArgumentException;
import org.apache.hadoop.hive.ql.exec.UDFArgumentTypeException;
import org.apache.hadoop.hive.ql.metadata.HiveException;
import org.apache.hadoop.hive.ql.udf.generic.GenericUDF;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.PrimitiveObjectInspectorFactory;

/**
 * 计算参数的长度， 参数类型String， 参数个数 1 个, 函数返回值的数据类型int
 * @author leon
 * @ClassName MyLen.java
 * @createTime 2021年09月24日 09:18:00
 */
public class MyLen extends GenericUDF {
    /**
     * 初始化：检查数据类型，数据类型检查器
     * @param arguments
     * @return
     * @throws UDFArgumentException
     */
    @Override
    public ObjectInspector initialize(ObjectInspector[] arguments) throws UDFArgumentException {
        // 判断参数的个数
        if(arguments.length != 1){
            throw new UDFArgumentException("参数个数错误！");
        }

        // 判断数据类型
        if(!arguments[0].getCategory().equals(ObjectInspector.Category.PRIMITIVE)){
            throw new UDFArgumentTypeException(0,"参数类型错误！");
        }

        return PrimitiveObjectInspectorFactory.javaIntObjectInspector;
    }

    /**
     * 主要逻辑： 计算参数的长度
     * @param arguments
     * @return
     * @throws HiveException
     */
    @Override
    public Object evaluate(DeferredObject[] arguments) throws HiveException {
        // 0. 判断数据是否为Null
        if(arguments[0].get() == null){
            return 0;
        }

        // 1. 获取参数
        String str = arguments[0].get().toString();
        // 2. 计算长度
        int length = str.length();

        return length;
    }

    @Override
    public String getDisplayString(String[] children) {
        return "";
    }
}
