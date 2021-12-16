package com.atguigu.redis;

import redis.clients.jedis.*;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class RedisClusterTest {

    public static void main(String[] args) throws IOException {
        Set<HostAndPort> hosts = new HashSet<HostAndPort>();
        HostAndPort hostAndPort1 = new HostAndPort("192.168.1.102", 6379);
        HostAndPort hostAndPort2 = new HostAndPort("192.168.1.102", 6380);
        HostAndPort hostAndPort3 = new HostAndPort("192.168.1.102", 6381);
        hosts.add(hostAndPort1);
        hosts.add(hostAndPort2);
        hosts.add(hostAndPort3);

        //连接池的配置
        JedisPoolConfig config = new JedisPoolConfig();
        //连接池最多能够初始化的连接数
        config.setMaxTotal(4);
        //设置连接池中最多的空闲连接数
        config.setMaxIdle(2);
        //设置连接池中最少又要的空闲连接数
        config.setMinIdle(1);
        JedisCluster jedisCluster = new JedisCluster(hosts, config);

        jedisCluster.set("k1111","v1111");

        jedisCluster.mset("{yy}k11","v11","{yy}k22","v22");

        jedisCluster.close();
    }
}
