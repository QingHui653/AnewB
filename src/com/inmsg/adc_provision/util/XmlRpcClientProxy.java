package com.inmsg.adc_provision.util;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;
import org.apache.xmlrpc.client.XmlRpcCommonsTransportFactory;

/**
 * XmlRpcClient������
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
	 * ���캯��
	 * @param serverURL
	 * @throws MalformedURLException
	 */
	public XmlRpcClientProxy(String serverURL) throws MalformedURLException {
		this.serverURL = serverURL;
		initClient();
	}
	
	/**
	 * ��ʼ���ͻ���
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
	 * ����Զ�̷���
	 * @param method ������
	 * @param params ��������
	 * @return
	 * @throws XmlRpcException
	 */
	public Object execute(String method, Object[] params) throws XmlRpcException {
		return xmlRpcClient.execute(method, params);
	}
	
	/**
	 * ����Զ�̷���
	 * @param method ������
	 * @param paramList �����б�
	 * @return
	 * @throws XmlRpcException
	 */
	public Object execute(String method, List paramList) throws XmlRpcException {
		return xmlRpcClient.execute(method, paramList);
	}
}

