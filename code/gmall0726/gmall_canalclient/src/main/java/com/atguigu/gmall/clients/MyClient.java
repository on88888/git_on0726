package com.atguigu.gmall.clients;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.otter.canal.client.CanalConnector;
import com.alibaba.otter.canal.client.CanalConnectors;
import com.alibaba.otter.canal.protocol.CanalEntry;
import com.alibaba.otter.canal.protocol.Message;
import com.atguigu.gmall.constants.TopicName;
import com.google.protobuf.ByteString;
import com.google.protobuf.InvalidProtocolBufferException;
import java.net.InetSocketAddress;
import java.util.List;

/**
 * Created by Smexy on 2021/12/1
 *
 *
 *
 * ④解析订阅到的数据
 *
 * ⑤将数据写入kafka
 */
public class MyClient {

    public static void main(String[] args) throws InterruptedException, InvalidProtocolBufferException {

        /*
                ①先创建一个客户端对象CanalConnector

                newSingleConnector(SocketAddress address : 参考canal.properties中的  canal.ip，canal.port
                                  String destination： 参考canal.properties  的 canal.destinations = example
                                  String username:   参考instance.properties中的  canal.user
                                  String password:    参考instance.properties中的canal.password
                                  )
         */
        CanalConnector canalConnector = CanalConnectors.newSingleConnector(new InetSocketAddress("hadoop102", 11111),
                "example", null, null
        );


        //②使用客户端对象连接 Canal server端
        canalConnector.connect();

        // ③订阅表  计算GMV，所有已经提交的订单的交易额综合
        canalConnector.subscribe("gmall0726.order_info");


       // ④解析订阅到的数据
//        Message message = canalConnector.get(100);
//        System.out.println(message);
        while (true){

            Message message = canalConnector.get(100);

            if (message.getId() == -1){

                System.out.println("歇会......");

                Thread.sleep(5000);

                continue;

            }

            // 拉取到数据，解析
            List<CanalEntry.Entry> entries = message.getEntries();

            for (CanalEntry.Entry entry : entries) {

                //获取表名
                //String tableName = entry.getHeader().getTableName();

                if (entry.getEntryType() == CanalEntry.EntryType.ROWDATA){

                    parseData(entry.getStoreValue());

                }

            }

        }





    }

    private static void parseData(ByteString storeValue) throws InvalidProtocolBufferException {

        CanalEntry.RowChange rowChange = CanalEntry.RowChange.parseFrom(storeValue);

        // 判断当前sql是不是Insert?
        if (rowChange.getEventType() == CanalEntry.EventType.INSERT){

            //一个sql引起的N行的数据变化
            List<CanalEntry.RowData> rowDatasList = rowChange.getRowDatasList();

            // 一个rowData是一行
            for (CanalEntry.RowData rowData : rowDatasList) {

                //准备一个{}
                JSONObject jsonObject = new JSONObject();

                // update :  将一个列由过去的值，更新为一个新值。  对弈一列，会记录之前的值和之后的值
                // insert :  只有之后的值
                // 获取order_info 的 insert(需要)? update? detete?
                List<CanalEntry.Column> afterColumnsList = rowData.getAfterColumnsList();

                for (CanalEntry.Column column : afterColumnsList) {

                        jsonObject.put(column.getName(), column.getValue());

                }

//                System.out.println(jsonObject);// sql数据打印到控制台
                MyProducer.sendData(TopicName.GMALL_ORDER_INFO,jsonObject.toJSONString());  //拉取到kafka

            }

        }

    }

}
