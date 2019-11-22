package com.zp.hadoop;

import org.apache.zookeeper.*;

import java.io.IOException;

/**
 * @Author zp
 * @create 2019/9/3 11:25
 */
public class ZkServer {
    private static String connectString = "hadoop01:2181,hadoop02:2181,hadoop03:2181";
    private static int sessionTimeout = 2000;
    ZooKeeper zooKeeper = null;

    public static void main(String[] args) throws Exception {
        ZkServer server = new ZkServer();
        server.init();
        server.create(args[0]);

        Thread.sleep(Long.MAX_VALUE);

    }

    public void init() throws IOException {
        Watcher watcher = new Watcher() {
            public void process(WatchedEvent watchedEvent) {

            }
        };
        zooKeeper = new ZooKeeper(connectString, sessionTimeout, watcher);
    }



    /**
     * 创建节点
     */
    public void create(String hostname) throws KeeperException, InterruptedException {

        // 创建节点
        String s = zooKeeper.create("/servers/server", hostname.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
        System.out.println(hostname + "is online");


    }

}
