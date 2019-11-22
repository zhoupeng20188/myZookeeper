package com.zp.hadoop;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

/**
 * @Author zp
 * @create 2019/9/3 11:25
 */
public class ZookeeperTest {
    private static String connectString = "hadoop01:2181,hadoop02:2181,hadoop03:2181";
    private static int sessionTimeout = 2000;
    ZooKeeper zooKeeper = null;
    //    @Test
    @Before
    public void init() throws IOException {
        Watcher watcher = new Watcher() {
            public void process(WatchedEvent watchedEvent) {
                // 监听回调
                // 获取节点数据
                System.out.println("========监听回调============");
                List<String> children = null;
                try {
                    children = zooKeeper.getChildren("/", true);
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
     * 创建节点
     */
    @Test
   public void create() throws KeeperException, InterruptedException {

        // 创建节点
        String s = zooKeeper.create("/testbyjava", "java".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT_SEQUENTIAL);
        System.out.println(s);


    }

    /**
     * 获取节点数据
     */
    @Test
    public void get() throws KeeperException, InterruptedException {
        // 获取节点
        List<String> children = zooKeeper.getChildren("/", true);

        for (String child : children) {
            System.out.println(child);
        }

        // 获取节点里的数据
        byte[] data = zooKeeper.getData("/test", false, new Stat());
        System.out.println(new String(data));
        Thread.sleep(30000);
    }
}
