package test.core.io.nio;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class FileChannelCase {
	
	
	public void test() throws IOException{
		//打开FileChannel
		RandomAccessFile aFile = new RandomAccessFile("data/nio-data.txt", "rw");
		FileChannel inChannel = aFile.getChannel();
		//从FileChannel读取数据
		ByteBuffer buf = ByteBuffer.allocate(48);
		//read()方法返回的int值表示了有多少字节被读到了Buffer中。如果返回-1，表示到了文件末尾。
		int bytesRead = inChannel.read(buf);
		
		//向FileChannel写数据
		String newData = "New String to write to file..." + System.currentTimeMillis();
		ByteBuffer buf2 = ByteBuffer.allocate(48);
		buf.clear();
		buf.put(newData.getBytes());
		buf.flip();
		while(buf.hasRemaining()) {
			inChannel.write(buf2);
		}
		//关闭FileChannel
		inChannel.close();
		
		//FileChannel的position方法
		//有时可能需要在FileChannel的某个特定位置进行数据的读/写操作。可以通过调用position()方法获取FileChannel的当前位置
		long pos = inChannel.position();
		inChannel.position(pos +123);
		/**
		 * FileChannel实例的size()方法将返回该实例所关联文件的大小。 long fileSize = channel.size();
		 * FileChannel的truncate方法可以使用FileChannel.truncate()方法截取一个文件。
		 * 截取文件时，文件将中指定长度后面的部分将被删除。如：channel.truncate(1024);
		 * 
		 * FileChannel的force方法FileChannel.
		 * force()方法将通道里尚未写入磁盘的数据强制写到磁盘上。
		 * 出于性能方面的考虑，操作系统会将数据缓存在内存中，所以无法保证写入到FileChannel里的数据一定会即时写到磁盘上。要保证这一点，需要调用force()方法
		 * channel.force(true);
		 */
	}
	
	/**
	 * FileChannel的transferFrom()方法可以将数据从源通道传输到FileChannel中
	 * @throws IOException 
	 */
	public void transferFrom() throws IOException {
		RandomAccessFile fromFile = new RandomAccessFile("fromFile.txt", "rw");
		FileChannel fromChannel = fromFile.getChannel();

		RandomAccessFile toFile = new RandomAccessFile("toFile.txt", "rw");
		FileChannel toChannel = toFile.getChannel();

		long position = 0;
		long count = fromChannel.size();
		/**
		 * position表示从position处开始向目标文件写入数据，
		 * count表示最多传输的字节数。如果源通道的剩余空间小于 count 个字节，则所传输的字节数要小于请求的字节数
		 */
		toChannel.transferFrom(fromChannel, position, count);
	}
	
	/**
	 * 将数据从FileChannel传输到其他的channel中
	 * @throws IOException
	 */
	public void transferTo() throws IOException {
		RandomAccessFile fromFile = new RandomAccessFile("fromFile.txt", "rw");
		FileChannel      fromChannel = fromFile.getChannel();

		RandomAccessFile toFile = new RandomAccessFile("toFile.txt", "rw");
		FileChannel      toChannel = toFile.getChannel();

		long position = 0;
		long count = fromChannel.size();
		//SocketChannel会一直传输数据直到目标buffer被填满。
		fromChannel.transferTo(position, count, toChannel);

	}
	
	
}
