package test.core.io.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class ServerSocketChannelCase {
	
	public void test() throws IOException{
		//打开 ServerSocketChannel
		ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();

		serverSocketChannel.socket().bind(new InetSocketAddress(9999));
		
		//监听新进来的连接
		while(true){
		    SocketChannel socketChannel = serverSocketChannel.accept();

		    //do something with socketChannel...
		}
		
		/**
		 * 在非阻塞模式下，accept() 方法会立刻返回，如果还没有新进来的连接,返回的将是null。 因此，需要检查返回的SocketChannel是否是null.
		 * serverSocketChannel.configureBlocking(false);
		 * while(true){
		    SocketChannel socketChannel = serverSocketChannel.accept();
			if(socketChannel != null){
        		//do something with socketChannel...
    			}
			}
		 */
		
		
		
	}
}
