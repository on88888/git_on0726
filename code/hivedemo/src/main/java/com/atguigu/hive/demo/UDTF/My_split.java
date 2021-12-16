package com.atguigu.hive.demo.UDTF;

import org.apache.hadoop.hive.ql.exec.UDFArgumentException;
import org.apache.hadoop.hive.ql.metadata.HiveException;
import org.apache.hadoop.hive.ql.udf.generic.GenericUDTF;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspectorFactory;
import org.apache.hadoop.hive.serde2.objectinspector.StructObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.PrimitiveObjectInspectorFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author leon
 * @ClassName My_split.java
 * @createTime 2021年09月24日 10:08:00
 */
public class My_split extends GenericUDTF {

    @Override
    public StructObjectInspector initialize(StructObjectInspector argOIs) throws UDFArgumentException {
        // 设置列名
        List<String> names = Arrays.asList("column01");
        // 数据的类型检查器
        List<ObjectInspector> fieldOIs = Arrays
                .<ObjectInspector> asList(PrimitiveObjectInspectorFactory.javaStringObjectInspector);
        return ObjectInspectorFactory
                .getStandardStructObjectInspector(names, fieldOIs);
    }

    @Override
    public void process(Object[] args) throws HiveException {
        // 1. 获取数据
        String str = args[0].toString();
        // 2. 候取分隔符
        String splitKey = args[1].toString();
        // 3. 使用分隔符分割数据
        String[] splits = str.split(splitKey);

        ArrayList<String> outputList = new ArrayList<>();

        // 4. 将数据传输
        for (String word : splits) {
            // 4.1 清空集合
            outputList.clear();
            // 4.2 数据添加到集合中
            outputList.add(word);
            // 4.3 传输数据
            forward(outputList);

        }
    }

    @Override
    public void close() throws HiveException {

    }
}
