package newb.c.a_spring.a_module.elasticsearch;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;

/**
 * 必须使用非root 用户启动 用户名es
 * head插件访问地址 /_plugin/head/
 *
 */

public class ESTools {
	
	public final static Client client =  build();
	
	/**
	 * 创建一次
	 * @return
	 */
	private static Client build(){
		if(null != client){
			return client;
		}
		Client client = null;
		String ip = "119.23.231.239";
//		String ip = "127.0.0.1";
		System.out.println("获取ESIP地址"+ip);
		try {
			System.out.println("创建Elasticsearch Client 开始");
			Settings settings = Settings
				.settingsBuilder()
				  .put("node.name","qhNode")
					.put("cluster.name","qh")
					//自动嗅探集群Ip
					//当ES服务器监听使用内网服务器IP而访问使用外网IP时，不要使用client.transport.sniff为true，在自动发现时会使用内网IP进行通信，导致无法连接到ES服务器，而直接使用addTransportAddress方法进行指定ES服务器。
//						.put("client.transport.sniff", true)
							.build();
//			Settings settings = Settings.builder().put("node.name","qhNode").put("cluster.name","qh").build();
			client = TransportClient.builder().settings(settings).build()
			.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(ip), 9300));
//			client = TransportClient.builder().build()
//			.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(ip), 9300));
			System.out.println("创建Elasticsearch Client 结束");
		} catch (Exception e) {
			System.out.println("创建Client异常");
			e.printStackTrace();
		}
		return client;
	}
	
	/**
	 * 关闭
	 */
	public static void close(){
		if(null != client){
			try {
				client.close();
			} catch (Exception e) {
				
			}
		}
	}
	
	
	@SuppressWarnings("unused")
	private void creatClient() throws UnknownHostException {
//		import static org.elasticsearch.node.NodeBuilder.*;
		//节点方式创建。
//		Node node = nodeBuilder().clusterName("yourclustername").node();
//		Client client = node.client();
		/*
		还有很多节点方式的创建方式，查看下面的官网地址。
		https://www.elastic.co/guide/en/elasticsearch/client/java-api/2.0/node-client.html
		*/
		
		
		//第二种
		
		/**
		 * 指定 ip地址创建
		 */
		// on startup
		Client client2 = TransportClient.builder().build()
		        .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("host1"), 9300))
		        .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("host2"), 9300));
		// on shutdown
		client2.close();
		
		//第三种
		//按集群名称创建
		Settings settings = Settings.settingsBuilder()
		        .put("cluster.name", "sojson-application").build();
		Client client = TransportClient.builder().settings(settings).build();
		//Add transport addresses and do something with the client...
		
		//第四种
		//同一内网Ip段，嗅的方式自己查找，组成集群。
		Settings settings4 = Settings.settingsBuilder()
		        .put("client.transport.sniff", true).build();
		TransportClient client4 = TransportClient.builder().settings(settings).build();
		/*
		客户端允许嗅其余的集群,它将数据节点添加到列表的机器使用。在这种情况下要注意,将使用的IP地址的其他节点开始(“publish”地址)。启用它,设置client.transport.sniff为 true:
		*/
	}
}
