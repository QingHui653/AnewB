package newb.c.a_spring.a_module.elasticsearch.v5;

import com.google.gson.JsonObject;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class EsUnitTest {

    private Logger logger = LoggerFactory.getLogger(ElasticsearchCreat.class);

    public final static String HOST = "119.23.231.239";

    public final static int PORT = 9300;//http请求的端口是9200，客户端是9300

    private TransportClient client = null;

    @Before
    public void getConnect() throws UnknownHostException {
        // 需要 配置 好 node.name 与 cluster.name
        Settings settings = Settings
                .builder()
                .put("node.name","qhNode")
                .put("cluster.name","qh")
                //自动嗅探集群Ip
                //当ES服务器监听使用内网服务器IP而访问使用外网IP时，不要使用client.transport.sniff为true，在自动发现时会使用内网IP进行通信，导致无法连接到ES服务器，而直接使用addTransportAddress方法进行指定ES服务器。
//				.put("client.transport.sniff", true)
                .build();

        client = new PreBuiltTransportClient(settings).addTransportAddresses(new InetSocketTransportAddress(InetAddress.getByName(HOST),PORT));
        logger.info("------------------------连接信息:" + client.toString());
    }

    @After
    public void closeConnect() {
        if(null != client) {
            logger.info("------------------------执行关闭连接操作...");
            client.close();
        }
    }


    @Test
    public void addIndex1(){
        IndexResponse response = null;
        try {
            response = client.prepareIndex("msg", "tweet", "1").setSource(XContentFactory.jsonBuilder()
                    .startObject().field("userName", "张三")
                    .field("sendDate", new Date())
                    .field("msg", "你好李四")
                    .endObject()).get();
            logger.info("索引名称:" + response.getIndex() + "\n类型:" + response.getType()+"\n文档ID:" + response.getId() + "\n当前实例状态:" + response.status());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void addIndex2() {
        String jsonStr = "{" +
                "\"userName\":\"张三\"," +
                "\"sendDate\":\"2017-11-30\"," +
                "\"msg\":\"你好李四\"" +
                "}";
        IndexResponse response = client.prepareIndex("weixin", "tweet").setSource(jsonStr, XContentType.JSON).get();
        logger.info("json索引名称:" + response.getIndex() + "\njson类型:" + response.getType()
                + "\njson文档ID:" + response.getId() + "\n当前实例json状态:" + response.status());

    }

    @Test
    public void addIndex3() {
        Map<String, Object> map = new HashMap<String,Object>();
        map.put("userName", "张三");
        map.put("sendDate", new Date());
        map.put("msg", "你好李四");

        IndexResponse response = client.prepareIndex("momo", "tweet").setSource(map).get();

        logger.info("map索引名称:" + response.getIndex() + "\n map类型:" + response.getType()
                + "\n map文档ID:" + response.getId() + "\n当前实例map状态:" + response.status());
    }

    @Test
    public void addIndex4() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("userName", "张三");
        jsonObject.addProperty("sendDate", "2017-11-23");
        jsonObject.addProperty("msg","你好李四");

        IndexResponse response = client.prepareIndex("qq", "tweet").setSource(jsonObject, XContentType.JSON).get();

        logger.info("jsonObject索引名称:" + response.getIndex() + "\n jsonObject类型:" + response.getType()
                + "\n jsonObject文档ID:" + response.getId() + "\n当前实例jsonObject状态:" + response.status());
    }


    @Test
    public void getData1() {
        GetResponse getResponse = client.prepareGet("msg", "tweet", "1").get();
        logger.info("索引库的数据:" + getResponse.getSourceAsString());
    }


    @Test
    public void updateData() {
        JsonObject jsonObject = new JsonObject();

        jsonObject.addProperty("userName", "王五");
        jsonObject.addProperty("sendDate", "2008-08-08");
        jsonObject.addProperty("msg","你好,张三，好久不见");

        UpdateResponse updateResponse = client.prepareUpdate("msg", "tweet", "1")
                .setDoc(jsonObject.toString(),XContentType.JSON).get();

        logger.info("updateResponse索引名称:" + updateResponse.getIndex() + "\n updateResponse类型:" + updateResponse.getType()
                + "\n updateResponse文档ID:" + updateResponse.getId() + "\n当前实例updateResponse状态:" + updateResponse.status());
    }


    @Test
    public void deleteData() {
        DeleteResponse deleteResponse = client.prepareDelete("msg", "tweet", "1").get();

        logger.info("deleteResponse索引名称:" + deleteResponse.getIndex() + "\n deleteResponse类型:" + deleteResponse.getType()
                + "\n deleteResponse文档ID:" + deleteResponse.getId() + "\n当前实例deleteResponse状态:" + deleteResponse.status());
    }
}
