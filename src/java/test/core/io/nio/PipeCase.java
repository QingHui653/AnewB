package test.core.io.nio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Pipe;

/**
 * Java NIO 管道是2个线程之间的单向数据连接。
 * Pipe有一个source通道和一个sink通道。数据会被写到sink通道，从source通道读取。
 * @author woshizbh
 *
 */
public class PipeCase {
	
	public void test() throws IOException{
		//创建管道
		Pipe pipe = Pipe.open();
		//向管道写数据
		//访问sink通道
		Pipe.SinkChannel sinkChannel = pipe.sink();
		
		String newData = "New String to write to file..." + System.currentTimeMillis();
		ByteBuffer buf = ByteBuffer.allocate(48);
		buf.clear();
		buf.put(newData.getBytes());

		buf.flip();

		while(buf.hasRemaining()) {
		    sinkChannel.write(buf);
		}
		
		//从管道读取数据
		Pipe.SourceChannel sourceChannel = pipe.source();
		ByteBuffer buf2 = ByteBuffer.allocate(48);
		int bytesRead = sourceChannel.read(buf2);
	}
}
