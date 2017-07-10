package test.core.io.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class SocketChannelCase {
	
	public void test() throws IOException{
		//打开 SocketChannel
		SocketChannel channel = SocketChannel.open();
		channel.connect(new InetSocketAddress("http://jenkov.com", 80));
		//从 SocketChannel 读取数据
		ByteBuffer buf = ByteBuffer.allocate(48);
		int bytesRead = channel.read(buf);
		//写入 SocketChannel
		String newData = "New String to write to file..." + System.currentTimeMillis();
		ByteBuffer buf2 = ByteBuffer.allocate(48);
		buf.clear();
		buf.put(newData.getBytes());
		buf.flip();
		while(buf.hasRemaining()) {
		    channel.write(buf2);
		}
		//关闭 SocketChannel
		channel.close();
		
		//可以设置 SocketChannel 为非阻塞模式（non-blocking mode）.设置之后，就可以在异步模式下调用connect(), read() 和write()了
		channel.configureBlocking(false);
		channel.connect(new InetSocketAddress("http://jenkov.com", 80));
		while(! channel.finishConnect() ){
		    //wait, or do something else...
		}

	}
}
