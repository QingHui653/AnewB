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
 * 公司使用的短信密锁  短信公司给的包在con.inmsg下
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
	 *  短信调用api发送短信
	 * @param phone
	 * @return
	 */
	public String sms(String phone) {
		String yzm =(int)(Math.random()*900000+100000)+"";
		String content="【仁智招聘】尊敬的用户:,您的验证码是"+yzm+",请尽快验证,温馨提示:请妥善保管,不要随便泄露给他人。";
		XmlRpcTest test = new XmlRpcTest();
		test.testGetBalance(); ///查询短信剩余
		test.testAddTask();   //增加任务单
		test.testAddsmsContent(content); //短信内容
		test.testAddWhiteList(phone); //手机.
		return yzm;
	}
	
	
	
	public static void main(String[] args) {
		XmlRpcTest test = new XmlRpcTest();
		String content2="【仁智招聘】尊敬的用户:,您的验证码是156301,请尽快验证,";
		test.testGetBalance(); ///查询短信剩余
//		//新增任务单 
		test.testAddTask();   //增加任务单
//		//添加短信内容
		test.testAddsmsContent("【仁智招聘】尊敬的用户:,您的验证码是156301,请尽快验证"); //短信内容
//		//提交手机号码
		test.testAddWhiteList("15173961640"); //手机.
		test.sms("15173961640");
		
	}
	
	
	
	
	private void testGetBalance() {
		//查询余额
		System.out.println("开始测试TaskService.getBalance 接口");
		Map paramMap = new HashMap();
		try {
			
			//根据设置参数数据
			paramMap.put("Customer_id", Customer_id);          //写死
			paramMap.put("Corp_Account", Corp_Account);		   //写死
			paramMap.put("Token", StringUtil.MD5Encode(token));//写死
		
			XmlRpcClientProxy proxy = new XmlRpcClientProxy(xmlUrl);
			Object result = proxy.execute("TaskService.getBalance", new Object[]{paramMap});
			//解析xml结果
			System.out.println("测试结果：" + result);
			
			String xmlResult = (String)result;
			Document document = DocumentHelper.parseText(xmlResult);
			Element rootElement = document.getRootElement();
			String code = rootElement.elementTextTrim("Code");
			System.out.println("响应吗："+code);
			String responseMessage = rootElement.elementTextTrim("ResponseMessage");
			System.out.println("响应消息："+responseMessage);
			
			String balance = rootElement.elementTextTrim("Balance");
			System.out.println("余额："+balance);
		} catch (XmlRpcException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//测试获得任务单列表
	//其他的方法，解析和处理方式类似。
	private void testGetTaskList() {
		//获取任务单
		System.out.println("开始测试GetTaskList 接口");
		Map paramMap = new HashMap();
		try {
			//根据设置参数数据
			paramMap.put("Customer_id", Customer_id);		   //写死
			paramMap.put("Corp_Account", Corp_Account);		   //写死
			paramMap.put("START", 0);
			paramMap.put("END", 10);
			paramMap.put("Token", StringUtil.MD5Encode(token));//写死
			//不查询具体发送任务时，此处填写为0
			paramMap.put("Task_id", "0");
			
			
			
			XmlRpcClientProxy proxy = new XmlRpcClientProxy(xmlUrl);
			Object result = proxy.execute("TaskService.GetTaskList", new Object[]{paramMap});
			//解析xml结果
			System.out.println("测试结果：" + result);
			
			String xmlResult = (String)result;
			Document document = DocumentHelper.parseText(xmlResult);
			Element rootElement = document.getRootElement();
			String code = rootElement.elementTextTrim("Code");
			System.out.println("响应吗："+code);
			String responseMessage = rootElement.elementTextTrim("ResponseMessage");
			System.out.println("响应消息："+responseMessage);
			Element taskListElement = rootElement.element("TaskList");
			int count = Integer.parseInt(taskListElement.attributeValue("total"));
			System.out.println("获得总记录数：" + count);
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
					System.out.println("获得任务单数据，任务单id：" + taskId + "，状态：" + status + "，标题：" + title + "，提交时间：" + submit_Time + "，下发时间：" + send_Time + "成功提交数：" + sum_Send + ",接收成功数：" + succ_Send);
				}
			}
		} catch (XmlRpcException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//提交任务
	private void testAddTask() {
		System.out.println("开始测试AddTask 接口");
		Map paramMap = new HashMap();
		try {
			//根据设置参数数据
			paramMap.put("Customer_id", Customer_id);
			paramMap.put("Corp_Account", Corp_Account);
			paramMap.put("Agreement_id", Agreement_id);
			paramMap.put("Type", 2);
			paramMap.put("Token", StringUtil.MD5Encode(token));
			//如下需要指定下发时间，则该处填写
			paramMap.put("SendTime", null);   //时间写死,马上发送
			
			
			
			XmlRpcClientProxy proxy = new XmlRpcClientProxy(xmlUrl);
			Object result = proxy.execute("TaskService.AddTask", new Object[]{paramMap});
			//解析xml结果
			System.out.println("测试结果：" + result);
			
			String xmlResult = (String)result;
			Document document = DocumentHelper.parseText(xmlResult);
			Element rootElement = document.getRootElement();
			String code = rootElement.elementTextTrim("Code");
			System.out.println("响应吗："+code);
			String responseMessage = rootElement.elementTextTrim("ResponseMessage");
			System.out.println("响应消息："+responseMessage);
			Element taskListElement = rootElement.element("TaskList");

				List<Element> taskList =taskListElement.elements("Task");
				for (Element taskElement :taskList) {
					String taskId = taskElement.elementTextTrim("TaskID");
					task_id = Integer.parseInt(taskId);
					System.out.println("创建任务单，任务单id：" + taskId );
				}
		} catch (XmlRpcException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//提交短信内容
	private void testAddsmsContent(String content) {
			//短信内容,写死
			System.out.println("开始测试AddContent 接口");
			Map paramMap = new HashMap();
			try {
				
				//根据设置参数数据
				paramMap.put("Customer_id", Customer_id);
				paramMap.put("Corp_Account", Corp_Account);
				paramMap.put("TaskID", task_id);
				paramMap.put("SmsType", 0);
				paramMap.put("SmsContent", content);
//				paramMap.put("SmsContent", "【迅分期】测试短信");
//				paramMap.put("SmsType", 0);
//				paramMap.put("SmsContent", "【迅分期】测试长短信测试长短信测试长短信测试长短信测试长短信测试长短信测试长短信测试长短信测试长短信测试长短信测试长短信测试长短信测试长短信测试长短信");
				paramMap.put("Token", StringUtil.MD5Encode(token));
				
				
				XmlRpcClientProxy proxy = new XmlRpcClientProxy(xmlUrl);
				Object result = proxy.execute("TaskService.AddContent", new Object[]{paramMap});
				//解析xml结果
				System.out.println("测试结果：" + result);
				
				String xmlResult = (String)result;
				Document document = DocumentHelper.parseText(xmlResult);
				Element rootElement = document.getRootElement();
				String code = rootElement.elementTextTrim("Code");
				System.out.println("响应吗："+code);
				String responseMessage = rootElement.elementTextTrim("ResponseMessage");
				System.out.println("响应消息："+responseMessage);
			} catch (XmlRpcException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	//提交彩信内容
	private void testAddmmsContent() {
		System.out.println("开始测试AddContent 接口");
		Map paramMap = new HashMap();
		try {
			//读取彩信包内容
			FileInputStream fis = null;
			int size = 0;
			byte[] bytes = null;
			fis = new FileInputStream("D:\\mmsContent.zip");
			size = fis.available();
			bytes = new byte[size];
			fis.read(bytes);
			fis.close();
			
			//根据设置参数数据
			paramMap.put("Customer_id", Customer_id);
			paramMap.put("Corp_Account", Corp_Account);
			paramMap.put("TaskID", task_id);
			paramMap.put("MmsType", 1);
			paramMap.put("Token", StringUtil.MD5Encode(token));
			//彩信包內容
			paramMap.put("Mms", Hex.encodeHexString(bytes));
			
			
			
			XmlRpcClientProxy proxy = new XmlRpcClientProxy(xmlUrl);
			Object result = proxy.execute("TaskService.AddContent", new Object[]{paramMap});
			//解析xml结果
			System.out.println("测试结果：" + result);
			
			String xmlResult = (String)result;
			Document document = DocumentHelper.parseText(xmlResult);
			Element rootElement = document.getRootElement();
			String code = rootElement.elementTextTrim("Code");
			System.out.println("响应吗："+code);
			String responseMessage = rootElement.elementTextTrim("ResponseMessage");
			System.out.println("响应消息："+responseMessage);
		} catch (XmlRpcException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//提交手机号码
	private void testAddWhiteList(String phone) {
		// 
		System.out.println("开始测试AddWhiteList 接口");
		Map paramMap = new HashMap();
		try {
			
			//根据设置参数数据
			paramMap.put("Customer_id", Customer_id);
			paramMap.put("Corp_Account", Corp_Account);
			paramMap.put("TaskID", task_id);
			paramMap.put("Token", StringUtil.MD5Encode(token));
			//手机号码
//			paramMap.put("Mobile", "15173961640");
			paramMap.put("Mobile", phone);
			
			
			
			XmlRpcClientProxy proxy = new XmlRpcClientProxy(xmlUrl);
			Object result = proxy.execute("TaskService.AddWhiteList", new Object[]{paramMap});
			//解析xml结果
			System.out.println("测试结果：" + result);
			
			String xmlResult = (String)result;
			Document document = DocumentHelper.parseText(xmlResult);
			Element rootElement = document.getRootElement();
			String code = rootElement.elementTextTrim("Code");
			System.out.println("响应吗："+code);
			String responseMessage = rootElement.elementTextTrim("ResponseMessage");
			System.out.println("响应消息："+responseMessage);
		} catch (XmlRpcException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
