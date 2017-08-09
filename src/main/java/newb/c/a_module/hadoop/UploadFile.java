package newb.c.a_module.hadoop;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

public class UploadFile {

	public static void main(String[] args) throws IllegalArgumentException, IOException, InterruptedException, URISyntaxException {
			Configuration conf = new Configuration();
			conf.set("fs.defaultFS", "hdfs://hadoop-master:9000");
			// 获得hadoop系统的连接
			FileSystem fs = FileSystem.get(new URI("hdfs://hadoop-master:9000"), conf,"root");
			fs.copyFromLocalFile(new Path("D:/2.xls"), new Path("/input"));
			fs.close();
	}
}
