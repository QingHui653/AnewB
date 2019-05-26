package newb.c.a_spring.a_module.zookeeper;

/**
 * @Auther:woshizbh
 * @Date: 2018/9/25
 * @Deseription
 * https://www.cnblogs.com/LiZhiW/p/4923693.html
 */
import java.io.IOException;

import org.apache.zookeeper.*;
import org.apache.zookeeper.ZooDefs.Ids;

/**
 * 使用原生 zookeeper 客户端
 * 1.节点类型说明：
 * 节点类型有4种：“PERSISTENT、PERSISTENT_SEQUENTIAL、EPHEMERAL、EPHEMERAL_SEQUENTIAL”其中“EPHEMERAL、EPHEMERAL_SEQUENTIAL”两种是客户端断开连接(Session无效时)节点会被自动删除；“PERSISTENT_SEQUENTIAL、EPHEMERAL_SEQUENTIAL”两种是节点名后缀是一个自动增长序号。
 * 2.节点访问权限说明：
 * 节点访问权限由List<ACL>确定，但是有几个便捷的静态属性可以选择：
 *     Ids.CREATOR_ALL_ACL：只有创建节点的客户端才有所有权限
 *     Ids.OPEN_ACL_UNSAFE：这是一个完全开放的权限，所有客户端都有权限
 *     Ids.READ_ACL_UNSAFE：所有客户端只有读取的
 * 3.节点监听注意点
 * 使用Watcher监听时需要注意：所有的节点监听触发一次之后就不会再触发，需要重新再设置监听。但是当多个客户端同时监听某个节点时，要是这个节点发生变化，所有的客户端都会被触发！
 */
public class ZookeeperUtils{
    //创建一个watcher对象,当我们对Zookeeper进行修改的时候都会触发该事件
    private Watcher watcher = new Watcher() {
        @Override
        public void process(WatchedEvent event) {
            System.out.println("--------ZK Watcher---------");
        }
    };

    //初始化Zookeeper
    private ZooKeeper zooKeeper ;

    public void zookeeperInit() throws IOException {
        //多个ip使用，号分割
        zooKeeper = new ZooKeeper("193.112.44.172:2181", 20000 , watcher);
    }

    public void ZKOption() throws Exception, InterruptedException {
        System.out.println("-----ZK Option-----");
        // 1.判断是否存在
        System.out.println(zooKeeper.exists("/zoo1", false));
        if(zooKeeper.exists("/zool",false)!=null) {
            zooKeeper.delete("/zoo1", -1);
            //2.不存在 则创建 (节点路径、节点数据、节点的访问权限、节点类型)
            zooKeeper.create("/zoo1", "zoo1".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        }
        //添加watcher ，对zoo1进行监控变化-->获取数据 ,这个监控只会对下次操作节点起到作用
        zooKeeper.getData("/zoo1", this.watcher, null);
        //修改zoo1节点
        zooKeeper.setData("/zoo1", "hello".getBytes(), -1);
        //----------------------获取数据----------------------------
        System.out.println("获取第一次数据信息："+zooKeeper.getData("/zoo1", this.watcher, null));
        //-----------------这次操作的节点不会进行监控-----------------------
        zooKeeper.setData("/zoo1", "world".getBytes(), -1);
        //----------------------获取数据----------------------------
        System.out.println("获取第二次数据信息："+zooKeeper.getData("/zoo1", this.watcher, null));
        //查看节点状态
        System.out.println("节点状态："+zooKeeper.getState());

        //查看设置权限 getACL/setACL
//        System.out.println(zooKeeper.getACL("/zool",null));

        //创建子目录
//        zooKeeper.create("/zoo1/path01", "data01/data01".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);

        //获取子节点
//        System.out.println(zooKeeper.getChildren("/path01",false));

        //删除节点
        //-1代表version版本号，-1是删除所有版本
        zooKeeper.delete("/zoo1", -1);
        System.out.println(zooKeeper.exists("/zoo1", false));
    }

    //关闭Zookeeper
    public void closeZookeeper() throws Exception {
        zooKeeper.close();
    }


    public static void main(String[] args) throws Exception {
        ZookeeperUtils utils = new ZookeeperUtils();
        utils.zookeeperInit();
        utils.ZKOption();
        utils.closeZookeeper();
    }

}
