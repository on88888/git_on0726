package com.atguigu.zk;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

/*
    通过代码操作Zookeeper:
    1.创建客户端对象
    2.具体操作
    3.关闭资源
 */
public class ZKDemo {
    private ZooKeeper zk;
    /*
        1.创建客户端对象
     */
    @Before
    public void before() throws IOException {
        /*
        ZooKeeper(String connectString, int sessionTimeout, Watcher watcher)
        connectString : zk服务器地址
        sessionTimeout : session超时时间（一般为两倍的心跳时间）
        watcher : 一个监听器对象，当ZK服务器需要有事件通知客户端事就会调用该对象中的process方法
                总监听事件对象一般不用。
         */
        String connectString = "hadoop102:2181,hadoop103:2181,hadoop104:2181";
        zk = new ZooKeeper(connectString, 4000, new Watcher() {
            /*
                在该方法中实现当监听事件发生后需要处理的业务逻辑代码。
             */
            public void process(WatchedEvent event) {

            }
        });
    }
    /*
        3.关闭资源
     */
    @After
    public void after(){
        if (zk != null){
            try {
                zk.close();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    /*
        创建子节点
     */
    @Test
    public void test() throws KeeperException, InterruptedException {
        /*
         create(final String path, byte data[], List<ACL> acl,CreateMode createMode)
         path : 节点的路径
         data : 节点中的数据
         acl ： 访问控制权限(节点权限)
         createMode ：节点的类型
         */
        String s = zk.create("/xiyouji/longge", "longgeliaobude".getBytes(),
                ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);

        System.out.println(s);
    }

    /*
        判断子节点是否存在
     */
    @Test
    public void test2() throws KeeperException, InterruptedException {
        /*
            exists(String path, boolean watch)
            path : 节点路径
            watch : 是否使用总监听器对象
            注意：如果返回的对象为null则说明节点不存在，不为null则存在
         */
        Stat exists = zk.exists("/xiyouji/longge123", false);
        System.out.println(exists==null?"不存在" : "存在");
    }
    /*
        获取子节点并监听子节点变化
     */
    @Test
    public void test3() throws KeeperException, InterruptedException {
        /*
            getChildren(final String path, Watcher watcher)
            path : 节点的路径
            watcher :  一个监听器对象，当ZK服务器需要有事件(子节点变化事件)通知客户端时就会调用该对象中的process方法
                    process方法：在该方法中实现当监听事件（子节点变化事件)发生后需要处理的业务逻辑代码。
         */
        List<String> children = zk.getChildren("/xiyouji", new Watcher() {
            public void process(WatchedEvent event) {
                System.out.println("节点发生改变了");
            }
        });
        //遍历子节点
        for (String child : children) {
            System.out.println(child);
        }
        //不能让程序结束否则无法接受事件的消息
        Thread.sleep(Long.MAX_VALUE);
    }


    @Test
    public void test4() throws KeeperException, InterruptedException {
        listener();
        //不能让程序结束否则无法接受事件的消息
        Thread.sleep(Long.MAX_VALUE);
    }
    public void listener() throws KeeperException, InterruptedException {
        List<String> children = zk.getChildren("/xiyouji", new Watcher() {
            public void process(WatchedEvent event) {
                //当监听发生时：1.业务逻辑处理   2.再次注册监听
                System.out.println("节点发生改变了");
                try {
                    listener();
                } catch (KeeperException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        //遍历子节点
        for (String child : children) {
            System.out.println(child);
        }
    }
}











