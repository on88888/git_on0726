package com.atguigu.hive.test;

import org.apache.hadoop.hive.ql.exec.UDFArgumentException;
import org.apache.hadoop.hive.ql.metadata.HiveException;
import org.apache.hadoop.hive.ql.udf.generic.GenericUDTF;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspectorFactory;
import org.apache.hadoop.hive.serde2.objectinspector.StructField;
import org.apache.hadoop.hive.serde2.objectinspector.StructObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.PrimitiveObjectInspectorFactory;
import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author 0726
 * @ClassName My_UDTF
 * @createTime 2021年11月12日 19:49
 */
public class My_UDTF extends GenericUDTF {

    @Override
    public StructObjectInspector initialize(StructObjectInspector argOIs) throws UDFArgumentException {

        //获取输入的每一列参数的字段引用类型
        List<? extends StructField> allStructFieldRefs = argOIs.getAllStructFieldRefs();

        if (allStructFieldRefs.size()!=1){
            throw new UDFArgumentException("参数个数只能是1列！");
        }

        //判断参数的类型是不是string
        if ( !"string".equals(allStructFieldRefs.get(0).getFieldObjectInspector().getTypeName()) ){
            throw new UDFArgumentException("参数的类型只能是string");
        }

        //返回
        // 函数输出的每一行的所有列的列名集合
        ArrayList<String> fieldNames = new ArrayList<String>();
        // 函数输出的每一行的所有列的ObjectInspector集合 (ObjectInspector:对象检查器)
        ArrayList<ObjectInspector> fieldOIs = new ArrayList<ObjectInspector>();
        // 列名随意
        fieldNames.add("col1");
        fieldOIs.add(PrimitiveObjectInspectorFactory.javaStringObjectInspector);
        // 固定写法
        return ObjectInspectorFactory.getStandardStructObjectInspector(fieldNames,
                fieldOIs);

    }

    String forwardobj[] = new String[1];
    @Override
    public void process(Object[] args) throws HiveException {

        //取出字符串
        String str = args[0].toString();

        //把字符串转成JSONArray对象  [{},{} ]
        JSONArray jsonArray = new JSONArray(str);

        for (int i = 0; i < jsonArray.length(); i++) {

            //取出每一个jsonarray中的对象
            String jsonObjectStr = jsonArray.getString(i);
            //写出
            forwardobj[0] = jsonObjectStr;
            forward(forwardobj);

        }

    }

    @Override
    public void close() throws HiveException {

    }
}
