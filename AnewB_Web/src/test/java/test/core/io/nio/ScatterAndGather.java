package test.core.io.nio;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * scatter 分发 在读操作时将读取的数据写入多个buffer中 
 * gather 聚合  在写操作时将多个buffer的数据写入同一个Channel
 * 
 * @author woshizbh
 *
 */
public class ScatterAndGather {
	
	private String relativelyPath =System.getProperty("user.dir");
	
	/**
	 * (Scattering
	 * Reads在移动下一个buffer前，必须填满当前的buffer，这也意味着它不适用于动态消息(译者注：消息大小不固定)。换句话说，如果存在消息头和消息体
	 * ，消息头必须完成填充（例如 128byte），Scattering Reads才能正常工作。)
	 * @throws IOException
	 */
	@SuppressWarnings({ "unused", "resource" })
	private void scatter() throws IOException {
		RandomAccessFile aFile = new RandomAccessFile(relativelyPath+"\\src\\java\\test\\io\\1.txt", "rw");
		FileChannel inChannel = aFile.getChannel();
		
		
		ByteBuffer header = ByteBuffer.allocate(128);
		ByteBuffer body   = ByteBuffer.allocate(1024);
		ByteBuffer[] bufferArray = { header, body };
		inChannel.read(bufferArray);

	}
	
	/**
	 * write()方法会按照buffer在数组中的顺序，将数据写入到channel，注意只有position和limit之间的数据才会被写入
	 * 因此与Scattering Reads相反，Gathering Writes能较好的处理动态消息。
	 * @throws IOException
	 */
	public void gather() throws IOException {
		RandomAccessFile aFile = new RandomAccessFile(relativelyPath+"\\src\\java\\test\\io\\1.txt", "rw");
		FileChannel inChannel = aFile.getChannel();
		
		ByteBuffer header = ByteBuffer.allocate(128);
		ByteBuffer body   = ByteBuffer.allocate(1024);
		//write data into buffers
		ByteBuffer[] bufferArray = { header, body };
		inChannel.write(bufferArray);
	}
}
