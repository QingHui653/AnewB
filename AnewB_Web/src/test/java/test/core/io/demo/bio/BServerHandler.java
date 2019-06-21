package test.core.io.demo.bio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import test.core.io.demo.Calculator;

/**
 * bio客户端
 * 
 * @author woshizbh 用于处理客户端的socket连接
 */
public class BServerHandler implements Runnable {

	private static Logger log = LoggerFactory.getLogger(BServerHandler.class);

	private Socket socket;

	public BServerHandler(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {
		BufferedReader in = null;
		PrintWriter out = null;
		try {
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new PrintWriter(socket.getOutputStream());
			String expression;
			String result;
			while (true) {
				// 通过BufferedReader读取一行
				// 如果已经读到输入流尾部，返回null,退出循环
				// 如果得到非空值，就尝试计算结果并返回
				if ((expression = in.readLine()) == null)
					break;
				log.info("服务器收到消息：" + expression);
				try {
					result = Calculator.cal(expression).toString();
				} catch (Exception e) {
					result = "计算错误：" + e.getMessage();
				}
				log.info("server计算结果为：" + result);
				out.println(result);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 一些必要的清理工作
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				in = null;
			}
			if (out != null) {
				out.close();
				out = null;
			}
			if (socket != null) {
				try {
					socket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				socket = null;
			}
		}
	}
}
