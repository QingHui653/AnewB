package test.module.NetSocket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class NetSocketClient {

	public static void main(String[] args) {
		String serverName = "localhost";
		int port = 6666;

		System.out.println("Connecting to " + serverName + " on port " + port);
		try {
			Socket client = new Socket(serverName, port);
			System.out.println("正在尝试连接 "
					+ client.getRemoteSocketAddress());
			OutputStream outToServer = client.getOutputStream();

			DataOutputStream out = new DataOutputStream(outToServer);

			out.writeUTF("老子™终于连上了 " + client.getLocalSocketAddress());

			InputStream inFromServer = client.getInputStream();

			DataInputStream in = new DataInputStream(inFromServer);
			
			System.out.println("MD,一连上就把老子提了 " +" 读取服务器发送的信息 "+in.readUTF());
	        client.close();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
