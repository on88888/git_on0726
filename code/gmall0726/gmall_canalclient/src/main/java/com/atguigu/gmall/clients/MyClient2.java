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
import java.util.Random;

/**
 * Created by Smexy on 2021/12/1
 *
 *
 *
 * ④解析订阅到的数据
 *
 * ⑤将数据写入kafka
 */
public class MyClient2 {

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
        canalConnector.subscribe("gmall0726.*");


       // ④解析订阅到的数据
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
                String tableName = entry.getHeader().getTableName();

                if (entry.getEntryType() == CanalEntry.EntryType.ROWDATA){

                    parseData(tableName,entry.getStoreValue());

                }

            }

        }





    }

    private static void parseData(String tableName,ByteString storeValue) throws InvalidProtocolBufferException, InterruptedException {

        CanalEntry.RowChange rowChange = CanalEntry.RowChange.parseFrom(storeValue);

        // order_info insert
        if (tableName.equals("order_info") && rowChange.getEventType() == CanalEntry.EventType.INSERT){

            sendDataToKafka(rowChange,TopicName.GMALL_ORDER_INFO);

            // order_detail insert
        }else if (tableName.equals("order_detail") && rowChange.getEventType() == CanalEntry.EventType.INSERT){

            sendDataToKafka(rowChange,TopicName.GMALL_ORDER_DETAIL);

            // user_info insert | update
        }else if (tableName.equals("user_info") && (rowChange.getEventType() == CanalEntry.EventType.INSERT ||
                rowChange.getEventType() == CanalEntry.EventType.UPDATE)
                ){

            sendDataToKafka(rowChange,TopicName.GMALL_USER_INFO);

        }

    }

    public static void sendDataToKafka(CanalEntry.RowChange rowChange,String topic) throws InterruptedException {

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

            // [1,5)
            int second = new Random().nextInt(5);
            //模拟随机的延迟
            //Thread.sleep(second * 1000);

            //System.out.println(jsonObject);
            MyProducer.sendData(topic,jsonObject.toJSONString());

        }


    }

}
