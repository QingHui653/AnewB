package com.inmsg.adc_provision.util;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;
import org.apache.xmlrpc.client.XmlRpcCommonsTransportFactory;

/**
 * XmlRpcClient代理类
 * @author zhangzhongguang
 *
 */
public class XmlRpcClientProxy {
	/**
	 * server URL
	 */
	private String serverURL = "";
	
	/**
	 * xmlRpcClient
	 */
	private XmlRpcClient  xmlRpcClient;
	
	/**
	 * 构造函数
	 * @param serverURL
	 * @throws MalformedURLException
	 */
	public XmlRpcClientProxy(String serverURL) throws MalformedURLException {
		this.serverURL = serverURL;
		initClient();
	}
	
	/**
	 * 初始化客户端
	 * @throws MalformedURLException
	 */
	private void initClient() throws MalformedURLException {
		XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();
		config.setServerURL(new URL(serverURL));
		config.setEnabledForExtensions(true);
		config.setConnectionTimeout(60 * 1000);
		config.setReplyTimeout(60 * 1000);

		xmlRpcClient = new XmlRpcClient();

		// use Commons HttpClient as transport
		xmlRpcClient.setTransportFactory(new XmlRpcCommonsTransportFactory(xmlRpcClient));
		// set configuration
		xmlRpcClient.setConfig(config);
	}
	
	/**
	 * 调用远程方法
	 * @param method 方法名
	 * @param params 参数数组
	 * @return
	 * @throws XmlRpcException
	 */
	public Object execute(String method, Object[] params) throws XmlRpcException {
		return xmlRpcClient.execute(method, params);
	}
	
	/**
	 * 调用远程方法
	 * @param method 方法名
	 * @param paramList 参数列表
	 * @return
	 * @throws XmlRpcException
	 */
	public Object execute(String method, List paramList) throws XmlRpcException {
		return xmlRpcClient.execute(method, paramList);
	}
}

