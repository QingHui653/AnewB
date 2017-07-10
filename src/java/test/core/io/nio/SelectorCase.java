package test.core.io.nio;

import java.io.IOException;
import java.nio.channels.Channel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * 使用Selector能够处理多个通道
 * @author woshizbh
 *
 */
public class SelectorCase {
	
	public void test() throws IOException {
		//[1]Selector的创建
		Selector selector = Selector.open();
		//与Selector一起使用时，Channel必须处于非阻塞模式下。
		//FileChannel与Selector一起使用，因为FileChannel不能切换到非阻塞模式。而套接字通道都可以
		SocketChannel channel =SocketChannel.open();
		channel.configureBlocking(false);
		//[2]向Selector注册通道
		//Connect Accept Read Write 事件
		//int interestSet = SelectionKey.OP_READ | SelectionKey.OP_WRITE;
		//register()方法会返回一个SelectionKey对象。
		//这个对象包含了一些你感兴趣的属性：interest集合 ready集合 Channel Selector 附加的对象（可选）
		SelectionKey key = channel.register(selector,SelectionKey.OP_READ);
		
		int interestSet = key.interestOps();
		//可以看到，用“位与”操作interest 集合和给定的SelectionKey常量，可以确定某个确定的事件是否在interest 集合中。
		boolean isInterestedInAccept  = (interestSet & SelectionKey.OP_ACCEPT) == SelectionKey.OP_ACCEPT;
		boolean isInterestedInConnect = (interestSet & SelectionKey.OP_CONNECT)==SelectionKey.OP_CONNECT;
		boolean isInterestedInRead    = (interestSet & SelectionKey.OP_READ)==SelectionKey.OP_READ;
		boolean isInterestedInWrite   = (interestSet & SelectionKey.OP_WRITE)==SelectionKey.OP_WRITE;
		//(1)ready 集合是通道已经准备就绪的操作的集合。在一次选择(Selection)之后，你会首先访问这个ready set
		//在readyCase中
		int readySet = key.readyOps();
		//也可以使用
		key.isAcceptable();
		key.isConnectable();
		key.isReadable();
		key.isWritable();
		
		/**
		 * [3]从SelectionKey访问Channel和Selector
		 */
		Channel  channel2  = key.channel();
		Selector selector2 = key.selector();
		
		//(2)附加的对象
		//1.可以将一个对象或者更多信息附着到SelectionKey上，这样就能方便的识别某个给定的通道。例如，可以附加 与通道一起使用的Buffer，或是包含聚集数据的某个对象
		String remark ="CC";
		key.attach(remark);
		Object attachedObj = key.attachment();
		//2.用register()方法向Selector注册Channel的时候附加对象
		key = channel.register(selector, SelectionKey.OP_READ, remark);
		
		/**
		 * [4]通过Selector选择通道
		 * 一旦向Selector注册了一或多个通道，就可以调用几个重载的select()方法。
		 * 这些方法返回你所感兴趣的事件（如连接、接受、读或写）已经准备就绪的那些通道
		 * int select() 阻塞到至少有一个通道在你注册的事件上就绪了 , select()方法返回的int值表示有多少通道已经就绪。亦即，自上次调用select()方法后有多少通道变成就绪状态
		 * int select(long timeout) 和select()一样，除了最长会阻塞timeout毫秒(参数),
		 * int selectNow() selectNow()不会阻塞，不管什么通道就绪都立刻返回
		 */
		
		/**
		 * [5]调用selector的selectedKeys()方法，访问“已选择键集（selected key set）”中的就绪通道
		 * 当像Selector注册Channel时，Channel.register()方法会返回一个SelectionKey 对象。这个对象代表了注册到该Selector的通道。可以通过SelectionKey的selectedKeySet()方法访问这些对象。
		 */
		 Set<SelectionKey> selectedKeys = selector.selectedKeys();
		 
		 /**
		  * 这个循环遍历已选择键集中的每个键，并检测各个键所对应的通道的就绪事件。
注意每次迭代末尾的keyIterator.remove()调用。Selector不会自己从已选择键集中移除SelectionKey实例。必须在处理完通道时自己移除。下次该通道变成就绪时，Selector会再次将其放入已选择键集中。
SelectionKey.channel()方法返回的通道需要转型成你要处理的类型，如ServerSocketChannel或SocketChannel
		  */
		 Iterator<SelectionKey> keyIterator = selectedKeys.iterator();
		 while(keyIterator.hasNext()) {
		     SelectionKey selectionkey = keyIterator.next();
		     if(selectionkey.isAcceptable()) {
		         // a connection was accepted by a ServerSocketChannel.
		     } else if (selectionkey.isConnectable()) {
		         // a connection was established with a remote server.
		     } else if (selectionkey.isReadable()) {
		         // a channel is ready for reading
		     } else if (selectionkey.isWritable()) {
		         // a channel is ready for writing
		     }
		     keyIterator.remove();
		 }
		 
		 /**
		  * (3) wakeUp()
		 某个线程调用select()方法后阻塞了，即使没有通道已经就绪，也有办法让其从select()方法返回。只要让其它线程在第一个线程调用select()方法的那个对象上调用Selector.wakeup()方法即可。阻塞在select()方法上的线程会立马返回。
		 如果有其它线程调用了wakeup()方法，但当前没有线程阻塞在select()方法上，下个调用select()方法的线程会立即“醒来（wake up）”。
		  */
		 
		 /**
		  * (4)close()
用完Selector后调用其close()方法会关闭该Selector，且使注册到该Selector上的所有SelectionKey实例无效。通道本身并不会关闭。
		  */

	}
	
	public void case1() throws IOException {
		Selector selector = Selector.open();
		SocketChannel channel =SocketChannel.open();
		channel.configureBlocking(false);
		SelectionKey key = channel.register(selector, SelectionKey.OP_READ);
		while(true) {
		  int readyChannels = selector.select();
		  if(readyChannels == 0) continue;
		  Set selectedKeys = selector.selectedKeys();
		  Iterator<SelectionKey> keyIterator = selectedKeys.iterator();
		  while(keyIterator.hasNext()) {
		    key = keyIterator.next();
		    if(key.isAcceptable()) {
		        // a connection was accepted by a ServerSocketChannel.
		    } else if (key.isConnectable()) {
		        // a connection was established with a remote server.
		    } else if (key.isReadable()) {
		        // a channel is ready for reading
		    } else if (key.isWritable()) {
		        // a channel is ready for writing
		    }
		    keyIterator.remove();
		  }
		}
	}
}
