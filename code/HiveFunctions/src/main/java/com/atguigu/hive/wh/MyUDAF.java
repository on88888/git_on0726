package com.atguigu.hive.wh;

import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.hive.ql.exec.UDFArgumentException;
import org.apache.hadoop.hive.ql.exec.UDFArgumentTypeException;
import org.apache.hadoop.hive.ql.metadata.HiveException;
import org.apache.hadoop.hive.ql.parse.SemanticException;
import org.apache.hadoop.hive.ql.udf.generic.AbstractGenericUDAFResolver;
import org.apache.hadoop.hive.ql.udf.generic.GenericUDAFEvaluator;
import org.apache.hadoop.hive.ql.udf.generic.GenericUDAFParameterInfo;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspectorFactory;
import org.apache.hadoop.hive.serde2.objectinspector.PrimitiveObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.StandardMapObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.PrimitiveObjectInspectorFactory;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.PrimitiveObjectInspectorUtils;
import org.apache.hadoop.hive.serde2.typeinfo.PrimitiveTypeInfo;
import org.apache.hadoop.hive.serde2.typeinfo.TypeInfo;
import java.text.DecimalFormat;
import java.util.*;

/**
 * Created by Smexy on 2021/11/20
 *
 *      udaf运行的原理：
 *              ①在什么时候运行？
 *                      在分组之后
 *                          select
 *                              coupon_id,
 *                              myudaf(spu_name)
 *                          from xxx(过去30天使用优惠券购物的spu_name)
 *                          group by coupon_id
 *              ②如何运行
 *                      使用函数，计算分组后组内的每一行，在计算时，每扫描一行，会将临时计算的结果存入一个缓冲区对象(自己设计)中
 *
 *                      当组内的所有行全部扫描结束后，使用缓冲区，计算出结果
 *
 *------------------------------------------------------
 *      设计函数：
 *              myudaf(spu_name)
 *
 *     输入：      一列，string
 *
 *     缓冲区：  Map<spu_name,count>
 *
 *     输出： `spu_comment` STRING COMMENT '商品占比，按订单数统计，要求结果形式如下:iPhone X:30%,xiaomi 10:20%,iPhone 12:30%,其他:20%',
 *              1列   string
 *
 *              https://cwiki.apache.org/confluence/display/Hive/GenericUDAFCaseStudy
 */
public class MyUDAF extends AbstractGenericUDAFResolver {

    // 返回计算器
    @Override
    public GenericUDAFEvaluator getEvaluator(GenericUDAFParameterInfo info) throws SemanticException {
        // Type-checking goes here!   类型检查
        //检查是不是1个参数

        TypeInfo[] parameters = info.getParameters();
        if (parameters.length != 1) {
            throw new UDFArgumentTypeException(parameters.length - 1,
                    "请传入一个参数!");
        }

        // validate the second parameter, which is the number of histogram bins
        if (parameters[0].getCategory() != ObjectInspector.Category.PRIMITIVE) {
            throw new UDFArgumentTypeException(1,
                    "只能传入基本数据类型，但是，以下类型 "
                            + parameters[0].getTypeName() + "是你传的!");
        }

        if( ((PrimitiveTypeInfo) parameters[0]).getPrimitiveCategory()
                != PrimitiveObjectInspector.PrimitiveCategory.STRING) {
            throw new UDFArgumentException("只允许传入字符串!");
        }


        //检查是不是string类型

        return new MyEvaluator();
    }

    //定义自己的计算器
    public static class MyEvaluator extends GenericUDAFEvaluator {

        /*
                Hive在整个UDAF的运算过程中，有四种模式！
                    Mode:PARTIAL1 ,PARTIAL2,FINAL,COMPLETE

                        四种不同的模式下，所使用的参数类型是不同的！

               ----------------------
               return如何写，返回一个ObjectInspector(某个类型的对象检查器)
                    根据返回的类型，创建对应的 ObjectInspector。

                        Model: In PARTIAL1 and PARTIAL2 ,返回terminatePartial() 返回值类型对应的 ObjectInspector
                                                Map类型的 ObjectInspector
                                In FINAL and COMPLETE mode，返回 terminate() 返回值类型 对应的 ObjectInspector
                                                String类型的 ObjectInspector



         */
        @Override
        public ObjectInspector init(Mode m, ObjectInspector[] parameters) throws HiveException {
            // 调用父类的方法，获取Mode Set
            super.init(m, parameters);

            // 为mapOI赋值
            if (m == Mode.FINAL || m== Mode.PARTIAL2){

               mapOI = (StandardMapObjectInspector)parameters[0];
            }

            // 返回
            if (m == Mode.PARTIAL1 || m ==Mode.PARTIAL2){

               return  ObjectInspectorFactory.getStandardMapObjectInspector(
                       PrimitiveObjectInspectorFactory.javaStringObjectInspector,
                       PrimitiveObjectInspectorFactory.javaIntObjectInspector

               );
            }else{

                return  PrimitiveObjectInspectorFactory.javaStringObjectInspector;

            }

        }

        // 返回一个新的缓冲区对象实例
        @Override
        public AggregationBuffer getNewAggregationBuffer() throws HiveException {
            return new MyBuf();
        }

        // 重置缓冲区
        @Override
        public void reset(AggregationBuffer agg) throws HiveException {

            ((MyBuf)agg).datas.clear();

        }

        /*
            迭代： UDAF函数在扫描输入的每一行的时候，将需要存储的计算结果存入缓冲区

            AggregationBuffer agg： 缓冲区

            Object[] parameters： 输入的参数
                     myudaf(spu_name)

         */
        @Override
        public void iterate(AggregationBuffer agg, Object[] parameters) throws HiveException {

            //先充输入的参数中取出spu_name
            String spu_name = parameters[0].toString();

            HashMap<String, Integer> datas = ((MyBuf)agg).datas;

            //让datas中同一个spu_name的key，所对应的value + 1
           Integer newValue = datas.getOrDefault(spu_name , 0) + 1;

           datas.put(spu_name ,newValue);

        }

        /*
            Return the contents of the current aggregation in a persistable way

            用持久化的方式返回当前聚合的结果！ HashMap<String,Integer> datas是最终聚合的结果！

            提示：  持久化方式
                            聚合的结果的类似必须有以下类型组成：
                                    java的基本数据类型 或包装类
                                    Array,Map,List,Hadoop Writeable！


         */
        @Override
        public Object terminatePartial(AggregationBuffer agg) throws HiveException {
            return ((MyBuf)agg).datas;
        }

        /*
            将两个缓冲区合并为一个缓冲区！

            分布式运算！
                    数据，可能被切片！

                    AggregationBuffer agg： 当前机器的缓冲区

                    Object partial: 通过网络传输过来的别的机器上的缓冲区。需要先反序列化为Map类型，再进行合并！
                            反序列化时，必须知道要把这个object反序列化为什么类型的对象！
         */

        //声明反序列化的数据类型，用对象检测器检查是否是指定的类型
        // Hive会在merge()所在的阶段，对mapOI，调用init进行赋值
        private StandardMapObjectInspector mapOI ;


        @Override
        public void merge(AggregationBuffer agg, Object partial) throws HiveException {

            //取当前机器的缓冲区对象中的map集合

            HashMap<String, Integer> map1 = ((MyBuf) agg).datas;

            //反序列化 partial 为 map
            Map<?, ?> map2 = mapOI.getMap(partial);

            //确定map2中key-value的类型
            PrimitiveObjectInspector keyObjectInspector = (PrimitiveObjectInspector)mapOI.getMapKeyObjectInspector();
            PrimitiveObjectInspector valueObjectInspector = (PrimitiveObjectInspector)mapOI.getMapValueObjectInspector();

            //合并
            for (Map.Entry<?, ?> entry : map2.entrySet()) {

                //取出key,value
                String key = PrimitiveObjectInspectorUtils.getString(entry.getKey(), keyObjectInspector);
                int value = PrimitiveObjectInspectorUtils.getInt(entry.getValue(), valueObjectInspector);

                map1.put( key , map1.getOrDefault(key , 0) + value );

            }

        }

        /*
              AggregationBuffer agg: 最终合并的缓冲区

              从最终合并的缓冲区中，得到输出，将输出返回！
         */
        @Override
        public Object terminate(AggregationBuffer agg) throws HiveException {

            //每一个spu_name和它的用券次数
            HashMap<String, Integer> datas = ((MyBuf) agg).datas;

            //计算前三比例：  计算总次数 ，计算前三名， 计算比例
            Double totalTimes = 0d;

            for (Integer value : datas.values()) {

                totalTimes += value;

            }

            //求前三
            ArrayList<Map.Entry<String, Integer>> entries = new ArrayList<>(datas.entrySet());

            entries.sort(new Comparator<Map.Entry<String, Integer>>() {
                @Override
                public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                    return -o1.getValue().compareTo(o2.getValue());
                }
            });

            List<Map.Entry<String, Integer>> top3 = entries.subList(0, Math.min(3, entries.size()));


            //求前三的总次数
            Double top3Times = 0d;

            DecimalFormat decimalFormat = new DecimalFormat("###.##%");

            //准备最终的结合，存放前三和其他   如果同一个coupon_id下，用券的spu_name超过3，集合的size = 4，否则有多少集合就是多少
            ArrayList<String> result = new ArrayList<>();

            for (Map.Entry<String, Integer> entry : top3) {

                top3Times += entry.getValue();

                result.add(entry.getKey() + ":" + decimalFormat.format(entry.getValue() / totalTimes));

            }

            //放其他
            if (entries.size() > 3){

                result.add("其他:" + decimalFormat.format( (totalTimes - top3Times) / totalTimes));

            }

            String str = StringUtils.join(result, ',');

            return str;
        }
        // UDAF logic goes here!  定义缓冲区对象
        static class MyBuf implements AggregationBuffer {

            //定义一个Map结构存储数据
           private HashMap<String,Integer> datas = new HashMap<>();

        };

    }

    public static void main(String[] args) throws HiveException {


        HashMap<String,Integer> datas = new HashMap<>();

        datas.put("小米",10);
        datas.put("小米1",20);
        datas.put("小米2",30);
        datas.put("小米3",40);

        MyEvaluator.MyBuf myBuf = new MyEvaluator.MyBuf();

        myBuf.datas = datas;

        Object str = new MyEvaluator().terminate(myBuf);


        System.out.println(str);


    }
}
