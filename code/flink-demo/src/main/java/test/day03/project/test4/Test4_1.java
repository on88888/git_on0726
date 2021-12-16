package test.day03.project.test4;

import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.datastream.ConnectedStreams;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.co.KeyedCoProcessFunction;
import org.apache.flink.util.Collector;
import test.day03.project.test4.bean.OrderEvent;
import test.day03.project.test4.bean.TxEvent;

import java.util.HashMap;
import java.util.Map;

/**
 * @author on
 * @Description TODO
 * @since 2021年12月11日 15:30:45
 */

//订单数据从OrderLog.csv中读取，交易数据从ReceiptLog.csv中读取
/*
需求：将来自两条流的订单交易匹配
对于订单支付事件，用户支付完成其实并不算完，我们还得确认平台账户上是否到账了。
而往往这会来自不同的日志信息，所以我们要同时读入两条流的数据来做合并处理。
 */
public class Test4_1 {
    public static void main(String[] args) {

        Configuration conf = new Configuration();
        conf.setInteger("rest.port", 10000);
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.setParallelism(2);

        SingleOutputStreamOperator<OrderEvent> orderDS = env.readTextFile("input/OrderLog.csv")
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

        SingleOutputStreamOperator<TxEvent> receiptDS = env.readTextFile("input/ReceiptLog.csv")
                .map(new MapFunction<String, TxEvent>() {
                    @Override
                    public TxEvent map(String value) throws Exception {
                        String[] datas = value.split(",");

                        return new TxEvent(datas[0],
                                datas[1],
                                Long.valueOf(datas[2]));
                    }
                });

        ConnectedStreams<OrderEvent, TxEvent> connectedStreams = orderDS.connect(receiptDS);

        ConnectedStreams<OrderEvent, TxEvent> orderAndtx = connectedStreams.keyBy("txId", "txId");

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
                })
                .print();


        try {
            env.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
