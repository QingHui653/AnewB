package test.module.NetSocket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class NetSocketServer extends Thread {

	private ServerSocket serverSocket;

	public NetSocketServer(int port) throws IOException {
		serverSocket = new ServerSocket(port);
		serverSocket.setSoTimeout(100000);
	}

	public void run() {
		while (true) {
			try {
				System.out.println("等待客户端连接端口"+ serverSocket.getLocalPort() + "...");
				Socket server = serverSocket.accept();
				System.out.println("客户端连上了 "+ server.getRemoteSocketAddress());
				DataInputStream in = new DataInputStream(server.getInputStream());
				System.out.println("读取客户端发送的数据 " +in.readUTF());
				DataOutputStream out = new DataOutputStream(server.getOutputStream());
				out.writeUTF("客户端又掉线了 "+ server.getLocalSocketAddress() + "\nGoodbye!");
			} catch (IOException e) {
				System.out.println("暂时无人连接导致抛出连接超时异常--");
			}
		}
	}

	public static void main(String[] args) {
		int port =6666;
		try {
			Thread t = new NetSocketServer(port);
			t.start();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
