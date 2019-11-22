package com.zp.watch;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;

/**
 * @Author zp
 * @create 2019/11/22 10:21
 */
public class GetDataWatcher {
    // 通过getData监听，当节点的值被修改时会进入到回调里。
    public static void main(String[] args) throws IOException, KeeperException, InterruptedException {
        ZooKeeper zkCli = new ZooKeeper("localhost:2181", 3000, new Watcher() {

            //监听回调
            @Override
            public void process(WatchedEvent event) {

            }
        });

        byte[] data = zkCli.getData("/zk", new Watcher() {
            //监听的具体内容
            @Override
            public void process(WatchedEvent event) {
                System.out.println("监听路径为：" + event.getPath());
                System.out.println("监听的类型为：" + event.getType());
                System.out.println("数据被2货修改了！！！");
            }
        }, null);

        System.out.println(new String(data));

        Thread.sleep(Long.MAX_VALUE);
    }
}
