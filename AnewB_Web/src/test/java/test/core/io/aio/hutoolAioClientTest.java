package test.core.io.aio;

/**
 * @Auther:b
 * @Date: 2019/5/29
 * @Deseription
 */
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;

import cn.hutool.core.lang.Console;
import cn.hutool.core.util.StrUtil;
import cn.hutool.socket.aio.AioClient;
import cn.hutool.socket.aio.AioSession;
import cn.hutool.socket.aio.SimpleIoAction;

public class hutoolAioClientTest {
    public static void main(String[] args) {
        AioClient client = new AioClient(new InetSocketAddress("localhost", 8899), new SimpleIoAction() {

            @Override
            public void doAction(AioSession session, ByteBuffer data) {
                if(data.hasRemaining()) {
                    Console.log(StrUtil.utf8Str(data));
                    session.read();
                }
                Console.log("OK");
            }
        });

        client.write(ByteBuffer.wrap("Hello".getBytes()));
        client.read();
        //此时关闭长连接 会在 接受消息是 报错
//        client.close();
    }
}
