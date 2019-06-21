package newb.c.a_spring.a_module.hadoop.hdfs;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.BlockLocation;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.LocatedFileStatus;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.fs.RemoteIterator;
import org.apache.hadoop.io.IOUtils;
import org.junit.Before;
import org.junit.Test;

/**
 * JAVA 操作 HDFS
 * 上传下载 会 报这个 错误 could only be replicated to 0 nodes instead of minReplication (=1).  There are 1 datanode(s) running and 1 node(s) are excluded in this operation.
 * 暂时 确认为 hadoop配置 错误 导致.
 */
public class HdfsTest {

    FileSystem fs = null;

    /**
     * 初始化FileSystem
     */
    @Before
    public void init() throws Exception {
        // 构造一个配置参数对象，设置一个参数：我们要访问的hdfs的URI
        // 从而FileSystem.get()方法就知道应该是去构造一个访问hdfs文件系统的客户端，以及hdfs的访问地址
        // new Configuration();的时候，它就会去加载jar包中的hdfs-default.xml
        // 然后再加载classpath下的hdfs-site.xml
        Configuration conf = new Configuration();
        conf.set("dfs.replication","1");
        //conf.set("fs.defaultFS", "hdfs://hdp-node01:9000");
        /**
         * 参数优先级： 1、客户端代码中设置的值 2、classpath下的用户自定义配置文件 3、然后是服务器的默认配置
         */
        //conf.set("dfs.replication", "3");

        // 获取一个hdfs的访问客户端，根据参数，这个实例应该是DistributedFileSystem的实例
        // fs = FileSystem.get(conf);

        // 如果这样去获取，那conf里面就可以不要配"fs.defaultFS"参数，而且，这个客户端的身份标识已经是hadoop用户
//        fs = FileSystem.get(new URI("hdfs://193.112.44.172:9000"), conf, "root");
        fs = FileSystem.get(new URI("hdfs://192.168.1.103:9000"), conf, "root");
    }

    /**
     * 往hdfs上传文件
     */
    @Test
    public void testAddFileToHdfs() throws Exception {
        // 要上传的文件所在的本地路径
        Path src = new Path("e:/1.txt");
        // 要上传到hdfs的目标路径
        Path dst = new Path("/");
        fs.copyFromLocalFile(src, dst);

        fs.close();
    }

    /**
     * 从hdfs中复制文件到本地文件系统
     */
    @Test
    public void testDownloadFileToLocal() throws IllegalArgumentException, IOException {
//        fs.copyToLocalFile(new Path("/input/upload.jar"), new Path("e:/"));
        fs.copyToLocalFile(new Path("/user/root/input/core-site.xml"), new Path("e:/"));
        fs.close();
    }

    @Test
    public void testDownload() throws IOException {
        String uri = "hdfs://193.112.44.172:9000/input/upload.jar";
        Configuration conf = new Configuration();
        FileSystem fs = FileSystem.get(URI.create(uri), conf);
        InputStream in = null;
        try {
            in = fs.open(new Path(uri));
            IOUtils.copyBytes(in, System.out, 4096, false);
        } finally {
            IOUtils.closeStream(in);
        }
    }

    @Test
    public void testUpload() throws IOException, URISyntaxException, InterruptedException {
        Configuration conf = new Configuration();
        conf.set("fs.defaultFS", "hdfs://hadoop:9000");
        // 获得hadoop系统的连接
        FileSystem fs = FileSystem.get(new URI("hdfs://hadoop:9000"), conf,"root");
        fs.copyFromLocalFile(new Path("E:/出库.json"), new Path("/input"));
        fs.close();
    }

    /**
     * 在hfds中创建目录、删除目录、重命名
     */
    @Test
    public void testMkdirAndDeleteAndRename() throws IllegalArgumentException, IOException {
        // 创建目录
        fs.mkdirs(new Path("/a1/b1/c1"));

        // 删除文件夹 ，如果是非空文件夹，参数2必须给值true
        fs.delete(new Path("/aaa"), true);

        //删除文件
        fs.delete(new Path("/1.txt"), true);

        // 重命名文件或文件夹
        fs.rename(new Path("/a1"), new Path("/a2"));
    }

    /**
     * 查看目录信息，只显示文件
     */
    @Test
    public void testListFiles() throws FileNotFoundException, IllegalArgumentException, IOException {
        // 思考：为什么返回迭代器，而不是List之类的容器
        RemoteIterator<LocatedFileStatus> listFiles = fs.listFiles(new Path("/"), true);

        while (listFiles.hasNext()) {
            LocatedFileStatus fileStatus = listFiles.next();

            System.out.println(fileStatus.getPath().getName());
            System.out.println(fileStatus.getBlockSize());
            System.out.println(fileStatus.getPermission());
            System.out.println(fileStatus.getLen());
            BlockLocation[] blockLocations = fileStatus.getBlockLocations();
            for (BlockLocation bl : blockLocations) {
                System.out.println("block-length:" + bl.getLength() + "--" + "block-offset:" + bl.getOffset());
                String[] hosts = bl.getHosts();
                for (String host : hosts) {
                    System.out.println(host);
                }
            }
            System.out.println("--------------分割线--------------");
        }
    }

    /**
     * 查看文件及文件夹信息
     */
    @Test
    public void testListAll() throws FileNotFoundException, IllegalArgumentException, IOException {
        FileStatus[] listStatus = fs.listStatus(new Path("/"));
        String flag = "d--             ";

        for (FileStatus fstatus : listStatus) {
            if (fstatus.isFile())
                flag = "f--         ";
            System.out.println(flag + fstatus.getPath().getName());
        }
    }
}
