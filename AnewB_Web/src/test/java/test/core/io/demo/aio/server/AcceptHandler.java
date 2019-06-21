package test.core.io.demo.aio.server;

import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;

//作为handler接收客户端连接  
public class AcceptHandler implements CompletionHandler<AsynchronousSocketChannel, AsyncServerHandler> {  
  @Override  
  public void completed(AsynchronousSocketChannel channel,AsyncServerHandler serverHandler) {  
      //继续接受其他客户端的请求  
      AServer.clientCount++;  
      System.out.println("AIO连接的客户端数：" + AServer.clientCount);  
      serverHandler.channel.accept(serverHandler, this);  
      //创建新的Buffer  
      ByteBuffer buffer = ByteBuffer.allocate(1024);  
      //异步读  第三个参数为接收消息回调的业务Handler  
      channel.read(buffer, buffer, new ReadHandler(channel));  
  }  
  @Override  
  public void failed(Throwable exc, AsyncServerHandler serverHandler) {  
      exc.printStackTrace();  
      serverHandler.latch.countDown();  
  }  
} 
