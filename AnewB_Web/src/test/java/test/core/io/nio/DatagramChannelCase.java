package test.core.io.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketException;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

/**
 * DatagramChannel是一个能收发UDP包的通道。因为UDP是无连接的网络协议，所以不能像其它通道那样读取和写入。它发送和接收的是数据包。
 * @author woshizbh
 *
 */
public class DatagramChannelCase {
	
	public void test() throws IOException{
		//打开 DatagramChannel
		DatagramChannel channel = DatagramChannel.open();
		channel.socket().bind(new InetSocketAddress(9999));
		//通过receive()方法从DatagramChannel接收数据
		ByteBuffer buf = ByteBuffer.allocate(48);
		buf.clear();
		channel.receive(buf);
		//通过send()方法从DatagramChannel发送数据
		
		String newData = "New String to write to file..." + System.currentTimeMillis();

		ByteBuffer buf2 = ByteBuffer.allocate(48);
		buf.clear();
		buf.put(newData.getBytes());
		buf.flip();

		int bytesSent = channel.send(buf2, new InetSocketAddress("jenkov.com", 80));
		
		/**
		 * 当连接后，也可以使用read()和write()方法，就像在用传统的通道一样。只是在数据传送方面没有任何保证。这里有几个例子：
		int bytesRead = channel.read(buf);
		int bytesWritten = channel.write(but);

		 */
	}
}
