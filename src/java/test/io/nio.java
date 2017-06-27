package test.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

import org.junit.Test;

/**
 * nio 核心     channels 通道  buffers 缓冲   selectors 选择器
 * @author woshizbh
 *
 */
public class nio {
	
	private String relativelyPath =System.getProperty("user.dir");
	
	@SuppressWarnings("resource")
//	@Test
	public void nio1() throws IOException {
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
		
		System.out.println(" nio 写入完成");
	}
	
	@Test
	public void nio2() throws IOException {
		RandomAccessFile aFile = new RandomAccessFile(relativelyPath+"\\src\\test\\io\\1.txt", "rw");
		FileChannel inChannel = aFile.getChannel();
		//创建48bytes的缓存区
		ByteBuffer buf = ByteBuffer.allocate(48);
		//从buff读取数据
		int bytesRead = inChannel.read(buf);
		while (bytesRead != -1) {
			
			System.out.println("Read " + bytesRead);
			//make buffer ready for read
			buf.flip();

			while (buf.hasRemaining()) {
				System.out.print((char) buf.get()); // read 1 byte at a time
			}
			
			//make buffer ready for writing
			buf.clear();
			bytesRead = inChannel.read(buf);
		}
		aFile.close();
	}
}
