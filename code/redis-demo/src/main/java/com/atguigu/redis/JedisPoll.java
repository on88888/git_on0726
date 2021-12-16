package com.atguigu.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.Set;

public class JedisPoll {
    //使用jedis连接池
    public static void main(String[] args) {

        //连接池的配置
        JedisPoolConfig config = new JedisPoolConfig();
        //连接池最多能够初始化的连接数
        config.setMaxTotal(4);
        //设置连接池中最多的空闲连接数
        config.setMaxIdle(2);
        //设置连接池中最少又要的空闲连接数
        config.setMinIdle(1);

        //redis主机
        JedisPool pool = new JedisPool(config, "hadoop102",6381);

        //从连接池中获取jedis连接
        Jedis jedis = pool.getResource();

        System.out.println(jedis.ping());

        Set<String> keys = jedis.keys("*");
        for (String key : keys) {
            String value = jedis.get(key);
            System.out.println("key: " + key +"  , value: " + value);
        }
        //归还连接给连接池
        jedis.close();
    }
}
