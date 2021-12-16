package com.atguigu.redis;

import redis.clients.jedis.Jedis;

public class RedisTest {

    public static void main(String[] args) {

        //1、创建Jedis对象
        Jedis jedis = new Jedis("hadoop102",6379);
        //2、使用
        String reponse = jedis.ping();
        System.out.println(reponse);

        jedis.zadd("z1",100,"xxx");
        //3、关闭
        jedis.close();
    }
}
