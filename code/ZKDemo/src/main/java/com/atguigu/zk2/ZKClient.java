package com.atguigu.zk2;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.List;

/*
    1.创建客户端对象
    2.判断父节点是否存在（有没有必要取决于先启动谁）
    3.获取子节点并监听子节点变化
    4.不能让程序停止
 */
public class ZKClient {
    private static ZooKeeper zk;
    public static void main(String[] args) throws IOException, KeeperException, InterruptedException {
        //1.创建客户端对象
        String connectString = "hadoop102:2181,hadoop103:2181,hadoop104:2181";
        zk = new ZooKeeper(connectString, 4000, new Watcher() {
            public void process(WatchedEvent event) {

            }
        });
        // 3.获取子节点并监听子节点变化
        listener();

        //4.不能让程序停止
        Thread.sleep(Long.MAX_VALUE);
    }

    public static void listener() throws KeeperException, InterruptedException {
        List<String> children = zk.getChildren("/server", new Watcher() {
            public void process(WatchedEvent event) {
                //1.业务逻辑处理（遍历所有子节点-有几台服务器就有几个子节点） 2.再次监听
                try {
                    listener();
                } catch (KeeperException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        //1.业务逻辑处理
        //遍历子节点
        for (String child : children) {
            System.out.println(child);
        }

        System.out.println("===================================================");
    }
}
