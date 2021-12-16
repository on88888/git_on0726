package com.atguigu.zk2;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;

/*
    1.创建客户端对象
    2.判断父节点是否存在，如果不存在则创建
    3.创建临时节点
 */
public class ZKServer {
    public static void main(String[] args) throws IOException, KeeperException, InterruptedException {
        //1.创建客户端对象
        String connectString = "hadoop102:2181,hadoop103:2181,hadoop104:2181";
        ZooKeeper zk = new ZooKeeper(connectString, 4000, new Watcher() {
            public void process(WatchedEvent event) {

            }
        });

        //2.判断父节点是否存在，如果不存在则创建
        Stat exists = zk.exists("/server", false);
        if (exists==null){//父节点不存则创建
            zk.create("/server","".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        }

        //3.创建临时节点
        zk.create("/server/" + args[0],args[1].getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE,CreateMode.EPHEMERAL);

        //4.不能让程序结束
        Thread.sleep(Long.MAX_VALUE);
    }
}
