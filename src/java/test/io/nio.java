package test.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

import org.junit.Test;

public class nio {
	
	@SuppressWarnings("resource")
	@Test
	public void bio1() throws IOException {
	    String relativelyPath=System.getProperty("user.dir");
	    File file= new File(relativelyPath+"\\src\\test\\io\\1.txt");
	    File file2= new File(relativelyPath+"\\src\\test\\io\\2.txt");
		FileChannel fni =new FileInputStream(file).getChannel();
		FileChannel fno =new FileOutputStream(file2).getChannel();
		
		ByteBuffer buff=ByteBuffer.allocate(10000);
		
		while (fni.read(buff)!=-1) {
			buff.flip();//写入buff
			fno.write(buff);//写入文件
			buff.clear();//清理buffer，继续下一轮
		}
		fni.close();
		fno.close();
		
		System.out.println(" bio1  nio 写入完成");
	}
}
