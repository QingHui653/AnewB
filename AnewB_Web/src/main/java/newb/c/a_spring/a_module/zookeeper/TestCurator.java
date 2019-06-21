package newb.c.a_spring.a_module.zookeeper;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.CuratorTempFramework;
import org.apache.curator.framework.api.CuratorEvent;
import org.apache.curator.framework.api.CuratorListener;
import org.apache.curator.framework.api.CuratorWatcher;
import org.apache.curator.framework.api.UnhandledErrorListener;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.framework.state.ConnectionState;
import org.apache.curator.framework.state.ConnectionStateListener;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.retry.RetryNTimes;
import org.apache.curator.retry.RetryUntilElapsed;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.ZooDefs;
import org.junit.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @Auther:woshizbh
 * @Date: 2018/9/25
 * @Deseription https://www.cnblogs.com/LiZhiW/p/4923693.html
 */

/**
 * Curator几个组成部分
 * 1.Client: 是ZooKeeper客户端的一个替代品, 提供了一些底层处理和相关的工具方法
 * 2.Framework: 用来简化ZooKeeper高级功能的使用, 并增加了一些新的功能, 比如管理到ZooKeeper集群的连接, 重试处理
 * 3.Recipes: 实现了通用ZooKeeper的recipe, 该组件建立在Framework的基础之上
 * 4.Utilities:各种ZooKeeper的工具类
 * 5.Errors: 异常处理, 连接, 恢复等
 * 6.Extensions: recipe扩展
 * Curator主要解决了三类问题
 * 1.封装ZooKeeper client与ZooKeeper server之间的连接处理
 * 2.提供了一套Fluent风格的操作API
 * 3.提供ZooKeeper各种应用场景(recipe, 比如共享锁服务, 集群领导选举机制)的抽象封装
 * inTransaction() 开始是原子ZooKeeper事务. 可以复合create, setData, check, and/or delete 等操作然后调用commit()作为一个原子操作提交
 */
public class TestCurator {

    private static CuratorWatcher curatorWatcher = new CuratorWatcher() {
        @Override
        public void process(WatchedEvent event) throws Exception {
            System.out.println("监控： " + event);
        }
    };

    @Test
    //inTransaction() 开始是原子ZooKeeper事务. 可以复合create, setData, check, and/or delete 等操作然后调用commit()作为一个原子操作提交
    public void test() throws Exception {
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);//刚开始重试间隔为1秒，之后重试间隔逐渐增加，最多重试不超过三次
        RetryPolicy retryPolicy1 = new RetryNTimes(3, 1000);//最大重试次数，和两次重试间隔时间
        RetryPolicy retryPolicy2 = new RetryUntilElapsed(5000, 1000);//会一直重试直到达到规定时间，第一个参数整个重试不能超过时间，第二个参数重试间隔
        //第一种方式
        CuratorFramework client = CuratorFrameworkFactory.newClient("193.112.44.172:2181", new RetryNTimes(10, 5000));
        client.start();// 连接
        // 获取子节点，顺便监控子节点
        List<String> children = client.getChildren().usingWatcher(curatorWatcher).forPath("/");
        System.out.println("children---" + children);
        // 创建节点
        String result = client.create().withMode(CreateMode.PERSISTENT).withACL(ZooDefs.Ids.OPEN_ACL_UNSAFE).forPath("/test", "Data".getBytes());
        System.out.println("test---" + result);
        // 设置节点数据
        client.setData().forPath("/test", "111".getBytes());
        System.out.println("111---" + new String(client.getData().forPath("/test")));

        client.setData().forPath("/test", "222".getBytes());
        System.out.println("222---" + new String(client.getData().forPath("/test")));
        // 删除节点
        System.out.println("exist---" + client.checkExists().forPath("/test"));
        client.delete().withVersion(-1).forPath("/test");
        System.out.println("exist2---" + client.checkExists().forPath("/test"));
        client.close();
        System.out.println("OK！");
    }

    @Test
    public void testListener() throws Exception {
        CuratorFramework client = CuratorFrameworkFactory.newClient("193.112.44.172:2181", new RetryNTimes(10, 5000));
        client.start();
        //CuratorListenable：当使用后台线程操作时，后台线程执行完成就会触发，例如：client.getData().inBackground().forPath("/test");后台获取节点数据，获取完成之后触发。
        client.getCuratorListenable().addListener(new CuratorListener() {
            @Override
            public void eventReceived(CuratorFramework client, CuratorEvent event) throws Exception {
                System.out.println("事件： " + event);
            }
        });
        //ConnectionStateListenable：当连接状态变化时触发。
        client.getConnectionStateListenable().addListener(new ConnectionStateListener() {
            @Override
            public void stateChanged(CuratorFramework client, ConnectionState newState) {
                System.out.println("连接状态事件： " + newState);
            }
        });
        //UnhandledErrorListenable：当后台操作发生异常时触发。
        client.getUnhandledErrorListenable().addListener(new UnhandledErrorListener() {
            @Override
            public void unhandledError(String message, Throwable e) {
                System.out.println("错误事件：" + message);
            }
        });

        client.getData().inBackground().forPath("/test");
        client.close();
        System.out.println("OK！");
    }

    /**
     * 命名空间,临时客户端,重试策略
     */
    @Test
    public void testOther() {
        //第二种 builder
        CuratorTempFramework client = CuratorFrameworkFactory.builder()
                .namespace("MyApp") //命名空间Namespace避免多个应用的节点的名称冲突。 CuratorFramework提供了命名空间的概念，这样CuratorFramework会为它的API调用的path加上命名空间
                .connectString("127.0.0.1:2181")// 连接串
                .retryPolicy(new RetryNTimes(10, 5000))// 重试策略
                .connectionTimeoutMs(100) // 连接超时
                .sessionTimeoutMs(100) // 会话超时
                //CuratorFramework：CuratorTempFramework，一定时间不活动后连接会被关闭。创建builder时不是调用build()而是调用buildTemp()。3分钟不活动连接就被关闭，你也可以指定不活动的时间。
                .buildTemp(100, TimeUnit.MINUTES); // 临时客户端并设置连接时间
        //CuratorTempFramework 只有 getData,close,inTransaction

        /*Retry策略
        retry策略可以改变retry的行为。 它抽象出RetryPolicy接口， 包含一个方法public boolean allowRetry(int retryCount, long elapsedTimeMs);。 在retry被尝试执行前， allowRetry()被调用，并且将当前的重试次数和操作已用时间作为参数. 如果返回true， retry被执行。否则异常被抛出。
        Curator本身提供了几个策略:
        ExponentialBackoffRetry:重试一定次数，每次重试sleep更多的时间
        RetryNTimes:重试N次
        RetryOneTime:重试一次
        RetryUntilElapsed:重试一定的时间*/
    }

    /**
     * 分布式锁
     * @throws Exception
     */
    @Test
    public void testLock() throws Exception {
        CuratorFramework client = CuratorFrameworkFactory.newClient("193.112.44.172:2181", new RetryNTimes(10, 5000));
        client.start();

        String lockPath = "lock";
        InterProcessMutex lock = new InterProcessMutex(client, lockPath);
        if (lock.acquire(1000, TimeUnit.MILLISECONDS)) {
            try {
                // do some work inside of the critical section here
            } finally {
                lock.release();
            }
        }
    }
}
