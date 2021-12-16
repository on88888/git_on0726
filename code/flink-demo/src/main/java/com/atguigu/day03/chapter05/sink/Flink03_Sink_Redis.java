package com.atguigu.day03.chapter05.sink;

import com.atguigu.day03.bean.WaterSensor;
import com.atguigu.day03.chapter05.source.RandomWatersensor;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.redis.RedisSink;
import org.apache.flink.streaming.connectors.redis.common.config.FlinkJedisPoolConfig;
import org.apache.flink.streaming.connectors.redis.common.mapper.RedisCommand;
import org.apache.flink.streaming.connectors.redis.common.mapper.RedisCommandDescription;
import org.apache.flink.streaming.connectors.redis.common.mapper.RedisMapper;

/**
 * @author Hefei
 * @description
 * @project_name flink-0726
 * @package_name com.atguigu.chapter05.sink
 * @since 2021/12/11 10:16
 */
public class Flink03_Sink_Redis {
    public static void main(String[] args) {
        Configuration conf = new Configuration();
        conf.setInteger("rest.port", 10000);
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment(conf);
        env.setParallelism(2);

        FlinkJedisPoolConfig poolConfig = new FlinkJedisPoolConfig
                .Builder()
                .setDatabase(0)
                .setHost("hfaliredis.redis.rds.aliyuncs.com")
                .setPassword("redis0726:Redis0726")
                .build();


        env.addSource(new RandomWatersensor())
                .addSink(new RedisSink<>(
                        poolConfig,
                        new RedisMapper<WaterSensor>() {
                            @Override
                            public RedisCommandDescription getCommandDescription() {
                                return new RedisCommandDescription(RedisCommand.HSET,"watersensor");
                            }

                            @Override
                            public String getKeyFromData(WaterSensor data) {
                                return data.getId();
                            }

                            @Override
                            public String getValueFromData(WaterSensor data) {
                                return data.getVc()+"";
                            }
                        }));

        try {
            env.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}