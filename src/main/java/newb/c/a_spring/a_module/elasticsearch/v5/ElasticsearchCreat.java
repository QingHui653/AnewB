package newb.c.a_spring.a_module.elasticsearch.v5;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ElasticsearchCreat {

    private Logger logger = LoggerFactory.getLogger(ElasticsearchCreat.class);

    public final static String HOST = "119.23.231.239";

    public final static int PORT = 9300;//http请求的端口是9200，客户端是9300

    /**
     * 测试Elasticsearch客户端连接
     * @return void
     * @throws UnknownHostException
     */
    @SuppressWarnings("resource")
    @Test
    public void test1() throws UnknownHostException {
        //创建客户端
        TransportClient client = new PreBuiltTransportClient(Settings.EMPTY)
                .addTransportAddresses(new InetSocketTransportAddress(InetAddress.getByName(HOST),PORT));

        logger.debug("Elasticsearch connect info:" + client.toString());

        //关闭客户端
        client.close();
    }
}
