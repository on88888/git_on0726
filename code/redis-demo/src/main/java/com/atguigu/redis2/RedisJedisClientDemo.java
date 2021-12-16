package com.atguigu.redis2;

import redis.clients.jedis.Jedis;

/**
 * @author leon
 * @ClassName RedisJedisClientDemo.java
 * @createTime 2021年08月04日 14:27:00
 */
public class RedisJedisClientDemo {
    public static void main(String[] args) {

        Jedis jedis = new Jedis("hadoop102", 6380);
        jedis.flushAll();
        jedis.set("k1", "v1");
        jedis.mset("k2", "23", "k3", "v3");
        String k2 = jedis.get("k2");
        System.out.println("k2 : "+ k2);
        jedis.incrBy("k2", 20);
        String incredK2 = jedis.get("k2");
        System.out.println("incredK2 : "+ incredK2);
        jedis.close();

    }
}
