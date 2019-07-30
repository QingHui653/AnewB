package newb.c.a_spring.a_module.elasticsearch.v5;

import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.index.query.MultiMatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

public class EsUnitTest {

    private Logger logger = LoggerFactory.getLogger(EsUnitTest.class);

//    public final static String HOST = "119.23.231.239";
//    public final static int PORT = 9300;//http请求的端口是9200，客户端是9300
//    public final static String HOST = "usla.waiwang.men";
//    public final static int PORT = 44130;
    private final static String HOST = "192.168.8.12";
    private final static int PORT = 19300;

    private TransportClient client = null;

    @Before
    public void getConnect() throws UnknownHostException {
        // 需要 配置 好 node.name 与 cluster.name
        Settings settings = Settings
                .builder()
                .put("node.name","els-node1")
                .put("cluster.name","kuaipi")
                //自动嗅探集群Ip
                //当ES服务器监听使用内网服务器IP而访问使用外网IP时，不要使用client.transport.sniff为true，在自动发现时会使用内网IP进行通信，导致无法连接到ES服务器，而直接使用addTransportAddress方法进行指定ES服务器。
				.put("client.transport.sniff", true)
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
    public void addIndex(){
        Map<String,String> map = new HashMap<>();
        map.put("test1_id","1");
        map.put("test1_name","1");
        IndexResponse response = client.prepareIndex("test1", "test1").setId("1").setSource(map).get();
        response = client.prepareIndex("test1", "test1").setId("2").setSource(map).get();
        logger.info("索引名称:" + response.getIndex() + "\n类型:" + response.getType()+"\n文档ID:" +
                response.getId() + "\n当前实例状态:" + response.status()+ "\n当前实例:" + response.toString());
    }


    @Test
    public void getData1() {
        //基本没有只能通过Id来查询
        GetResponse response = client.prepareGet().setIndex("test1").setType("test1").setId("1").get();
        logger.info("索引名称:" + response.getIndex() + "\n类型:" + response.getType()+"\n文档ID:" +
                response.getId() + "\n当前实例:" + response.toString());
    }


    @Test
    public void updateData() {
        Map<String,String> map = new HashMap<>();
        map.put("test1_id","1");
        map.put("test1_name","2");
        //目前更新只能 通过id来更新,不能想
        UpdateResponse response = client.prepareUpdate().setIndex("test1").setType("test1").setId("1").setDoc(map).get();
        logger.info("索引名称:" + response.getIndex() + "\n类型:" + response.getType()+"\n文档ID:" +
                response.getId() + "\n当前实例:" + response.toString());
    }


    @Test
    public void deleteData() {
        DeleteResponse response = client.prepareDelete().setIndex("test1").setType("test1").setId("2").get();
        logger.info("索引名称:" + response.getIndex() + "\n类型:" + response.getType()+"\n文档ID:" +
                response.getId() + "\n当前实例:" + response.toString());
    }

    @Test
    public void find(){
        QueryBuilder query =
                //在全部字段中查询
//                QueryBuilders.queryStringQuery("1")
                //字段配置.
//                QueryBuilders.termQuery("creatorName","Lcc")
                //全文检索
                QueryBuilders.multiMatchQuery("Lcc");
                ;
        MultiMatchQueryBuilder queryBuilder = new MultiMatchQueryBuilder("Lcc");
        SearchResponse response = client.prepareSearch().setIndices("kp_delivery_order_v1").setTypes("KP_DELIVERY_ORDER").setQuery(queryBuilder).get();
        SearchHits hits = response.getHits();
        for (SearchHit hit : hits) {
            System.out.println(hit.getSource());
        }
        logger.info("\n当前实例:" + response.toString());
    }
}
