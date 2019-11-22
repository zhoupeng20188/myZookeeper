package com.zp.watch;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;

/**
 * @Author zp
 * @create 2019/11/22 10:47
 */
public class PersistenceWatcher {
    private static ZooKeeper zkCli;
    public static void main(String[] args) throws IOException, InterruptedException {
        // 永久监听
        zkCli = new ZooKeeper("localhost:2181", 4000, new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {
                byte[] data = new byte[0];
                try {
                    data = zkCli.getData("/zk",true, null);
                } catch (KeeperException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(new String(data));
            }
        });
        Thread.sleep(Long.MAX_VALUE);
    }
}
