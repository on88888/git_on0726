package com.atguigu.kafka.demo.partitioner;

import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;

import java.util.Map;

/**
 * @author leon
 * @ClassName CustomPartition.java
 * @createTime 2021年09月30日 09:34:00
 */
public class CustomPartition implements Partitioner {
    @Override
    public int partition(String topic, Object key, byte[] keyBytes, Object value, byte[] valueBytes, Cluster cluster) {

        // 1. 解析key
        String keyStr = key.toString();
        // 2. 获取分区个数
        Integer number = cluster.partitionCountForTopic(topic);
        //3. 计算分区
        int partition = Math.abs(keyStr.hashCode()) % number;
        return partition;
    }

    @Override
    public void close() {

    }

    @Override
    public void configure(Map<String, ?> configs) {

    }
}
