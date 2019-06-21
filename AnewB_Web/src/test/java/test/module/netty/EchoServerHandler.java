package test.module.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.util.CharsetUtil;
import io.netty.util.ReferenceCountUtil;

@ChannelHandler.Sharable
public class EchoServerHandler extends ChannelInboundHandlerAdapter {

    @Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) {
	    // 默默地丢弃收到的数据
        // 或者直接打印
        System.out.println("Yes, A new client in = " + ctx.name());
//		((ByteBuf) msg).release();
		ByteBuf in = (ByteBuf) msg;
        try {
            // 打印客户端输入，传输过来的的字符
            System.out.println("有新的信息输入");
            System.out.print(in.toString(CharsetUtil.UTF_8));
            ctx.writeAndFlush(Unpooled.copiedBuffer("Welcome!!", CharsetUtil.UTF_8));
        } finally {
            ReferenceCountUtil.release(msg); // (2)
        }

	}

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.writeAndFlush(Unpooled.EMPTY_BUFFER)//4
                .addListener(ChannelFutureListener.CLOSE);
    }
	
	@Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) { // (4)
        // 当出现异常就关闭连接
        cause.printStackTrace();
        ctx.close();
    }
}
