package newb.c.api;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.binary.Hex;
import org.apache.xmlrpc.XmlRpcException;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.inmsg.adc_provision.util.XmlRpcClientProxy;
import com.inmsg.util.StringUtil;

/**
 * ��˾ʹ�õĶ�������  ���Ź�˾���İ���con.inmsg��
 * @author woshizbh
 *
 */
public class XmlRpcTest {
	static String xmlUrl = "http://www.inmsgcn.com/eepwww/xmlrpc";
	//static String token ="0c70dfe8-7c25-4094-9294-26ad9ce122d0";
	static String token ="2210ede1-b35b-4c3f-b790-bc0d4362fd07";
	static int Customer_id =801047;
	static String Corp_Account ="100047";
	static int Agreement_id =38;
	private int task_id = 1;
	
	/**
	 *  ���ŵ���api���Ͷ���
	 * @param phone
	 * @return
	 */
	public String sms(String phone) {
		String yzm =(int)(Math.random()*900000+100000)+"";
		String content="��������Ƹ���𾴵��û�:,������֤����"+yzm+",�뾡����֤,��ܰ��ʾ:�����Ʊ���,��Ҫ���й¶�����ˡ�";
		XmlRpcTest test = new XmlRpcTest();
		test.testGetBalance(); ///��ѯ����ʣ��
		test.testAddTask();   //��������
		test.testAddsmsContent(content); //��������
		test.testAddWhiteList(phone); //�ֻ�.
		return yzm;
	}
	
	
	
	public static void main(String[] args) {
		XmlRpcTest test = new XmlRpcTest();
		String content2="��������Ƹ���𾴵��û�:,������֤����156301,�뾡����֤,";
		test.testGetBalance(); ///��ѯ����ʣ��
//		//�������� 
		test.testAddTask();   //��������
//		//��Ӷ�������
		test.testAddsmsContent("��������Ƹ���𾴵��û�:,������֤����156301,�뾡����֤"); //��������
//		//�ύ�ֻ�����
		test.testAddWhiteList("15173961640"); //�ֻ�.
		test.sms("15173961640");
		
	}
	
	
	
	
	private void testGetBalance() {
		//��ѯ���
		System.out.println("��ʼ����TaskService.getBalance �ӿ�");
		Map paramMap = new HashMap();
		try {
			
			//�������ò�������
			paramMap.put("Customer_id", Customer_id);          //д��
			paramMap.put("Corp_Account", Corp_Account);		   //д��
			paramMap.put("Token", StringUtil.MD5Encode(token));//д��
		
			XmlRpcClientProxy proxy = new XmlRpcClientProxy(xmlUrl);
			Object result = proxy.execute("TaskService.getBalance", new Object[]{paramMap});
			//����xml���
			System.out.println("���Խ����" + result);
			
			String xmlResult = (String)result;
			Document document = DocumentHelper.parseText(xmlResult);
			Element rootElement = document.getRootElement();
			String code = rootElement.elementTextTrim("Code");
			System.out.println("��Ӧ��"+code);
			String responseMessage = rootElement.elementTextTrim("ResponseMessage");
			System.out.println("��Ӧ��Ϣ��"+responseMessage);
			
			String balance = rootElement.elementTextTrim("Balance");
			System.out.println("��"+balance);
		} catch (XmlRpcException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//���Ի�������б�
	//�����ķ����������ʹ���ʽ���ơ�
	private void testGetTaskList() {
		//��ȡ����
		System.out.println("��ʼ����GetTaskList �ӿ�");
		Map paramMap = new HashMap();
		try {
			//�������ò�������
			paramMap.put("Customer_id", Customer_id);		   //д��
			paramMap.put("Corp_Account", Corp_Account);		   //д��
			paramMap.put("START", 0);
			paramMap.put("END", 10);
			paramMap.put("Token", StringUtil.MD5Encode(token));//д��
			//����ѯ���巢������ʱ���˴���дΪ0
			paramMap.put("Task_id", "0");
			
			
			
			XmlRpcClientProxy proxy = new XmlRpcClientProxy(xmlUrl);
			Object result = proxy.execute("TaskService.GetTaskList", new Object[]{paramMap});
			//����xml���
			System.out.println("���Խ����" + result);
			
			String xmlResult = (String)result;
			Document document = DocumentHelper.parseText(xmlResult);
			Element rootElement = document.getRootElement();
			String code = rootElement.elementTextTrim("Code");
			System.out.println("��Ӧ��"+code);
			String responseMessage = rootElement.elementTextTrim("ResponseMessage");
			System.out.println("��Ӧ��Ϣ��"+responseMessage);
			Element taskListElement = rootElement.element("TaskList");
			int count = Integer.parseInt(taskListElement.attributeValue("total"));
			System.out.println("����ܼ�¼����" + count);
			if (count > 0) {
				List<Element> taskList =taskListElement.elements("Task");
				for (Element taskElement :taskList) {
					String taskId = taskElement.elementTextTrim("TaskID");
					String title = taskElement.elementTextTrim("Title");
					String submit_Time = taskElement.elementTextTrim("Submit_Time");
					String send_Time = taskElement.elementTextTrim("Send_Time");
					String status = taskElement.elementTextTrim("Status");
					String sum_Send = taskElement.elementTextTrim("Sum_Send");
					String succ_Send = taskElement.elementTextTrim("Succ_Send");
					System.out.println("����������ݣ�����id��" + taskId + "��״̬��" + status + "�����⣺" + title + "���ύʱ�䣺" + submit_Time + "���·�ʱ�䣺" + send_Time + "�ɹ��ύ����" + sum_Send + ",���ճɹ�����" + succ_Send);
				}
			}
		} catch (XmlRpcException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//�ύ����
	private void testAddTask() {
		System.out.println("��ʼ����AddTask �ӿ�");
		Map paramMap = new HashMap();
		try {
			//�������ò�������
			paramMap.put("Customer_id", Customer_id);
			paramMap.put("Corp_Account", Corp_Account);
			paramMap.put("Agreement_id", Agreement_id);
			paramMap.put("Type", 2);
			paramMap.put("Token", StringUtil.MD5Encode(token));
			//������Ҫָ���·�ʱ�䣬��ô���д
			paramMap.put("SendTime", null);   //ʱ��д��,���Ϸ���
			
			
			
			XmlRpcClientProxy proxy = new XmlRpcClientProxy(xmlUrl);
			Object result = proxy.execute("TaskService.AddTask", new Object[]{paramMap});
			//����xml���
			System.out.println("���Խ����" + result);
			
			String xmlResult = (String)result;
			Document document = DocumentHelper.parseText(xmlResult);
			Element rootElement = document.getRootElement();
			String code = rootElement.elementTextTrim("Code");
			System.out.println("��Ӧ��"+code);
			String responseMessage = rootElement.elementTextTrim("ResponseMessage");
			System.out.println("��Ӧ��Ϣ��"+responseMessage);
			Element taskListElement = rootElement.element("TaskList");

				List<Element> taskList =taskListElement.elements("Task");
				for (Element taskElement :taskList) {
					String taskId = taskElement.elementTextTrim("TaskID");
					task_id = Integer.parseInt(taskId);
					System.out.println("�������񵥣�����id��" + taskId );
				}
		} catch (XmlRpcException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//�ύ��������
	private void testAddsmsContent(String content) {
			//��������,д��
			System.out.println("��ʼ����AddContent �ӿ�");
			Map paramMap = new HashMap();
			try {
				
				//�������ò�������
				paramMap.put("Customer_id", Customer_id);
				paramMap.put("Corp_Account", Corp_Account);
				paramMap.put("TaskID", task_id);
				paramMap.put("SmsType", 0);
				paramMap.put("SmsContent", content);
//				paramMap.put("SmsContent", "��Ѹ���ڡ����Զ���");
//				paramMap.put("SmsType", 0);
//				paramMap.put("SmsContent", "��Ѹ���ڡ����Գ����Ų��Գ����Ų��Գ����Ų��Գ����Ų��Գ����Ų��Գ����Ų��Գ����Ų��Գ����Ų��Գ����Ų��Գ����Ų��Գ����Ų��Գ����Ų��Գ����Ų��Գ�����");
				paramMap.put("Token", StringUtil.MD5Encode(token));
				
				
				XmlRpcClientProxy proxy = new XmlRpcClientProxy(xmlUrl);
				Object result = proxy.execute("TaskService.AddContent", new Object[]{paramMap});
				//����xml���
				System.out.println("���Խ����" + result);
				
				String xmlResult = (String)result;
				Document document = DocumentHelper.parseText(xmlResult);
				Element rootElement = document.getRootElement();
				String code = rootElement.elementTextTrim("Code");
				System.out.println("��Ӧ��"+code);
				String responseMessage = rootElement.elementTextTrim("ResponseMessage");
				System.out.println("��Ӧ��Ϣ��"+responseMessage);
			} catch (XmlRpcException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	//�ύ��������
	private void testAddmmsContent() {
		System.out.println("��ʼ����AddContent �ӿ�");
		Map paramMap = new HashMap();
		try {
			//��ȡ���Ű�����
			FileInputStream fis = null;
			int size = 0;
			byte[] bytes = null;
			fis = new FileInputStream("D:\\mmsContent.zip");
			size = fis.available();
			bytes = new byte[size];
			fis.read(bytes);
			fis.close();
			
			//�������ò�������
			paramMap.put("Customer_id", Customer_id);
			paramMap.put("Corp_Account", Corp_Account);
			paramMap.put("TaskID", task_id);
			paramMap.put("MmsType", 1);
			paramMap.put("Token", StringUtil.MD5Encode(token));
			//���Ű�����
			paramMap.put("Mms", Hex.encodeHexString(bytes));
			
			
			
			XmlRpcClientProxy proxy = new XmlRpcClientProxy(xmlUrl);
			Object result = proxy.execute("TaskService.AddContent", new Object[]{paramMap});
			//����xml���
			System.out.println("���Խ����" + result);
			
			String xmlResult = (String)result;
			Document document = DocumentHelper.parseText(xmlResult);
			Element rootElement = document.getRootElement();
			String code = rootElement.elementTextTrim("Code");
			System.out.println("��Ӧ��"+code);
			String responseMessage = rootElement.elementTextTrim("ResponseMessage");
			System.out.println("��Ӧ��Ϣ��"+responseMessage);
		} catch (XmlRpcException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//�ύ�ֻ�����
	private void testAddWhiteList(String phone) {
		// 
		System.out.println("��ʼ����AddWhiteList �ӿ�");
		Map paramMap = new HashMap();
		try {
			
			//�������ò�������
			paramMap.put("Customer_id", Customer_id);
			paramMap.put("Corp_Account", Corp_Account);
			paramMap.put("TaskID", task_id);
			paramMap.put("Token", StringUtil.MD5Encode(token));
			//�ֻ�����
//			paramMap.put("Mobile", "15173961640");
			paramMap.put("Mobile", phone);
			
			
			
			XmlRpcClientProxy proxy = new XmlRpcClientProxy(xmlUrl);
			Object result = proxy.execute("TaskService.AddWhiteList", new Object[]{paramMap});
			//����xml���
			System.out.println("���Խ����" + result);
			
			String xmlResult = (String)result;
			Document document = DocumentHelper.parseText(xmlResult);
			Element rootElement = document.getRootElement();
			String code = rootElement.elementTextTrim("Code");
			System.out.println("��Ӧ��"+code);
			String responseMessage = rootElement.elementTextTrim("ResponseMessage");
			System.out.println("��Ӧ��Ϣ��"+responseMessage);
		} catch (XmlRpcException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
