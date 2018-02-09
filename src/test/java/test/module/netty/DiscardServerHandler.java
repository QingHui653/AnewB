package test.module.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.ReferenceCountUtil;

public class DiscardServerHandler extends ChannelHandlerAdapter{
	
	public void channelRead(ChannelHandlerContext ctx, Object msg) {
		 // 默默地丢弃收到的数据
//		((ByteBuf) msg).release();
		ByteBuf in = (ByteBuf) msg;
        try {
        	
        	ctx.write("ccc");
        	System.out.println("有新的信息输入");
            while (in.isReadable()) { // (1)
                System.out.print((char) in.readByte());
                System.out.flush();
            }
        } finally {
            ReferenceCountUtil.release(msg); // (2)
        }
	}
	
	@Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) { // (4)
        // 当出现异常就关闭连接
        cause.printStackTrace();
        ctx.close();
    }
}
