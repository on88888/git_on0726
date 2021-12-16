package com.atguigu.redis2;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * @author leon
 * @ClassName RedisJedisClientPoolDemo.java
 * @createTime 2021年08月04日 14:42:00
 */
public class RedisJedisClientPoolDemo {
    public static void main(String[] args) {

        GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
        // 1. 支持的最大连接数
        poolConfig.setMaxTotal(200);
        // 2. 最大的空闲连接数
        poolConfig.setMaxIdle(100);
        // 3. 最大的等待时长
        poolConfig.setMaxWaitMillis(600);
        // 4. 检测连接是否正常在取出的时候
        poolConfig.setTestOnBorrow(true);
        // 5. 构造连接池
        JedisPool jedisPool = new JedisPool(poolConfig, "hadoop102", 6379);

        // 6.从连接池中获取连接
        Jedis jedis = jedisPool.getResource();
        // 7.操作
        String ping = jedis.ping();
        System.out.println("ping:"+ping);

        // 8. 关闭
        jedis.close();

    }
}
