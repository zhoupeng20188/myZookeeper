package com.zp.hadoop;

import org.apache.zookeeper.*;

import java.io.IOException;
import java.util.List;

/**
 * @Author zp
 * @create 2019/9/3 11:25
 */
public class ZkClient {
    private static String connectString = "hadoop01:2181,hadoop02:2181,hadoop03:2181";
    private static int sessionTimeout = 2000;
    ZooKeeper zooKeeper = null;

    public static void main(String[] args) throws Exception {
        ZkClient client = new ZkClient();
        client.init();
        client.get();
        Thread.sleep(Long.MAX_VALUE);
    }

    public void init() throws IOException {
        Watcher watcher = new Watcher() {
            public void process(WatchedEvent watchedEvent) {
                // 监听回调
                // 获取节点数据
                System.out.println("========监听回调============");
                List<String> children = null;
                try {
                    children = zooKeeper.getChildren("/servers", true);
                } catch (KeeperException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                for (String child : children) {
                    System.out.println(child);
                }
            }
        };
        zooKeeper = new ZooKeeper(connectString, sessionTimeout, watcher);
    }
    /**
     * 获取节点数据
     */
    public void get() throws KeeperException, InterruptedException {
        // 获取节点
        List<String> children = zooKeeper.getChildren("/servers", true);

        for (String child : children) {
            System.out.println(child);
        }
    }

}
