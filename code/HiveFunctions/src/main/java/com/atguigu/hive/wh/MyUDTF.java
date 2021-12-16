package com.atguigu.hive.wh;

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
 * Created by Smexy on 2021/11/12
 *
 *          自定义一个UDTF ： 表生成函数。
 *                  输入：   1行1列
 *                              string :  [{},{} ]
 *
 *                  输出：   N行1列
 *                              每一行：   只有1列，string类型
 *                                      {}
 *                                      {}
 *
 */
public class MyUDTF extends GenericUDTF {

    /*
            ①检查入参，看函数传入的参数是否是期望的类型
                    当前，期望传入1列，是string类型

                  StructObjectInspector argOIs：  输入参数的 ObjectInspectors .

            ② 返回函数输出的每一行，所有列所对应的ObjectInspector(对象类型检测器): 作用是检查传入的某一列参数的类型是不是指定的类型
                        ObjectInspector 是一个接口
                                ListObjectInspector:  用来检查参数是不是 Array类型
                                        如何构造：   ObjectInspectorFactory.getStandardMapObjectInspector()
                                MapObjectInspector:  用来检查参数是不是 Map类型
                                PrimitiveObjectInspector:  用来检查参数是不是 基本(int,string,bigint )类型
                                        如何构造：PrimitiveObjectInspectorFactory.javaxxxObjectInspector
                                StructObjectInspector： 用来检查参数是不是 struct类型
                                UnionObjectInspector:  用来检查参数是不是 Union类型

                       当前： 返回 String类型的ObjectInspector



     */
    @Override
    public StructObjectInspector initialize(StructObjectInspector argOIs) throws UDFArgumentException {

        // 获取输入的每一列参数的字段引用类型
        List<? extends StructField> allStructFieldRefs = argOIs.getAllStructFieldRefs();

        // 判断参数的个数
        if (allStructFieldRefs.size() != 1){

            throw  new UDFArgumentException("参数的个数只能是1列!");

        }

        //判断参数的类型是不是string
        if (!"string".equals(allStructFieldRefs.get(0).getFieldObjectInspector().getTypeName())){

            throw  new UDFArgumentException("参数的类型只能是string!");

        }

        //返回
        //  函数输出的每一行的所有列的列名集合
        ArrayList<String> fieldNames = new ArrayList<String>();
        // 函数输出的每一行的所有列的ObjectInspector集合
        ArrayList<ObjectInspector> fieldOIs = new ArrayList<ObjectInspector>();

        fieldNames.add("col1");
        fieldOIs.add(PrimitiveObjectInspectorFactory.javaStringObjectInspector);

        // 固定写法
        return ObjectInspectorFactory.getStandardStructObjectInspector(fieldNames,
                fieldOIs);

    }


    /*
            输入：
                    Object[] args 代表输入的每行的参数集合
            计算
            结果可以使用forward()输出
     */

    //要写出的每一行的数据的集合
    String forwardObj[] = new String[1];


    @Override
    public void process(Object[] args) throws HiveException {

        //取出字符串
        String str = args[0].toString();

        //把字符串转换为 JSONArray对象   [{},{} ]
        JSONArray jsonArray = new JSONArray(str);

        for (int i = 0 ; i < jsonArray.length() ; i++){

            //取出每一个jsonarray中的对象
            String jsonObjectStr = jsonArray.getString(i);

            //写出
            forwardObj[0] = jsonObjectStr;

            forward(forwardObj);

        }

    }

    @Override
    public void close() throws HiveException {

    }
}
