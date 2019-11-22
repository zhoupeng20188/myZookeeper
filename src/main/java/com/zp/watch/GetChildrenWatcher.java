package com.zp.watch;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.List;

public class GetChildrenWatcher {

    static List<String> children = null;

    public static void main(String[] args) throws IOException, KeeperException, InterruptedException {

        // 通过zkCli.getchildren("/",new watch()){}来注册监听，监听的是整个根节点,但是这个监听只能监听一次。
        // 该监听只在节点增加或删除时才会进入回调
        // 线程休眠是为了让监听等待事件发生，不然会随着程序直接运行完。
        ZooKeeper zkCli = new ZooKeeper("localhost:2181",
                3000, new Watcher() {

            //监听回调
            @Override
            public void process(WatchedEvent event) {
                System.out.println("正在监听中.....");
            }
        });

        //监听目录
        children = zkCli.getChildren("/", new Watcher() {

            @Override
            public void process(WatchedEvent event) {

                System.out.println("监听路径为：" + event.getPath());
                System.out.println("监听的类型为：" + event.getType());
                System.out.println("数据被2货修改了！！！");

                for (String c : children) {
                    System.out.println(c);
                }
            }
        });
        System.out.println(children.get(0).getBytes().toString());

        Thread.sleep(Long.MAX_VALUE);

    }


}