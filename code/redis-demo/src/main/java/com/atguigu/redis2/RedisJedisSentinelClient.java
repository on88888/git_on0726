package com.atguigu.redis2;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisSentinelPool;

import java.util.HashSet;

/**
 * @author leon
 * @ClassName RedisJedisSentinelClient.java
 * @createTime 2021年08月04日 14:53:00
 */
public class RedisJedisSentinelClient {
    public static void main(String[] args) {

        HashSet<String> sentinels = new HashSet<String>();
        sentinels.add("hadoop102:6379");

        JedisSentinelPool jedisSentinelPool = new JedisSentinelPool(
                "mymaster",
                sentinels
                );
        Jedis jedis = jedisSentinelPool.getResource();
        String ping = jedis.ping();
        System.out.println("ping : "+ ping);
        String k1 = jedis.get("k1");
        System.out.println("k1 :"+ k1);

        jedis.close();
    }
}
