package com.atguigu.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisSentinelPool;

import java.util.HashSet;
import java.util.Set;

public class SentinelTest {

    public static void main(String[] args) {
        //设置哨兵信息
        Set<String> sentinels = new HashSet<String>();
        //设置哨兵的主机端口
        sentinels.add("hadoop102:36356");

        //连接池的配置
        JedisPoolConfig config = new JedisPoolConfig();
        //连接池最多能够初始化的连接数
        config.setMaxTotal(4);
        //设置连接池中最多的空闲连接数
        config.setMaxIdle(2);
        //设置连接池中最少又要的空闲连接数
        config.setMinIdle(1);
        JedisSentinelPool pool = new JedisSentinelPool("mymaster", sentinels, config);

        //从连接池中获取连接
        Jedis jedis = pool.getResource();

        System.out.println(jedis.ping());

        jedis.set("k1","v1");

        //归还连接
        jedis.close();
    }
}
