package test.NetSocket;

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
			System.out.println("Just connected to "
					+ client.getRemoteSocketAddress());
			OutputStream outToServer = client.getOutputStream();

			DataOutputStream out = new DataOutputStream(outToServer);

			out.writeUTF("Hello from " + client.getLocalSocketAddress());

			InputStream inFromServer = client.getInputStream();

			DataInputStream in = new DataInputStream(inFromServer);
			
			System.out.println("Server says " + in.readUTF());
	        client.close();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
