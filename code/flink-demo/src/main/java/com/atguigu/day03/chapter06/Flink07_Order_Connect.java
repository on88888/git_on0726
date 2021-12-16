package com.atguigu.day03.chapter06;

import com.atguigu.day03.bean.OrderEvent;
import com.atguigu.day03.bean.TxEvent;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.datastream.ConnectedStreams;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.co.KeyedCoProcessFunction;
import org.apache.flink.util.Collector;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Hefei
 * @description
 * @project_name flink-0726
 * @package_name com.atguigu.chapter06
 * @since 2021/12/11 15:47
 */
public class Flink07_Order_Connect {
    public static void main(String[] args) {
        Configuration conf = new Configuration();
        conf.setInteger("rest.port", 10005);
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment(conf);
        env.setParallelism(2);

        // 订单数据
        SingleOutputStreamOperator<OrderEvent> orderDS = env
                .readTextFile("input/OrderLog.csv")
                .map(new MapFunction<String, OrderEvent>() {
                    @Override
                    public OrderEvent map(String value) throws Exception {
                        String[] datas = value.split(",");
                        return new OrderEvent(Long.valueOf(datas[0]),
                                datas[1],
                                datas[2],
                                Long.valueOf(datas[3]));

                    }
                });

        // 收款数据流
        SingleOutputStreamOperator<TxEvent> txDS = env
                .readTextFile("input/ReceiptLog.csv")
                .map(new MapFunction<String, TxEvent>() {
                    @Override
                    public TxEvent map(String value) throws Exception {
                        String[] datas = value.split(",");

                        return new TxEvent(
                                datas[0],
                                datas[1],
                                Long.valueOf(datas[2])
                        );
                    }
                });

        ConnectedStreams<OrderEvent, TxEvent> connectedStream = orderDS.connect(txDS);

        ConnectedStreams<OrderEvent, TxEvent> orderAndtx = connectedStream.keyBy("txId", "txId");

        orderAndtx.process(new KeyedCoProcessFunction<String, OrderEvent, TxEvent, String>() {
            // 这个用来存储 没有匹配上的orderEvent
            Map<String, OrderEvent> orderEventMap = new HashMap<>();
            // 这个用来存储 没有匹配上的txEvent
            Map<String, TxEvent> txEventMap = new HashMap<>();


            @Override
            public void processElement1(OrderEvent orderEvent,
                                        Context ctx,
                                        Collector<String> out) throws Exception {
                if (txEventMap.containsKey(orderEvent.getTxId())) {
                    out.collect("交易号txid为：" + orderEvent.getTxId() + "的订单对账成功，收款信息先来");

                    // 如果对账成功 还需要从map中删除对应数据
                    txEventMap.remove(orderEvent.getTxId());
                } else {
                    orderEventMap.put(orderEvent.getTxId(), orderEvent);
                }
            }

            @Override
            public void processElement2(TxEvent txEvent,
                                        Context ctx,
                                        Collector<String> out) throws Exception {

                if (orderEventMap.containsKey(txEvent.getTxId())) {
                    out.collect("交易号txid为：" + txEvent.getTxId() + "的订单对账成功，订单信息先来");
                    orderEventMap.remove(txEvent.getTxId());
                } else {
                    txEventMap.put(txEvent.getTxId(), txEvent);
                }
            }
        }).print();

        try {
            env.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}