package main.java.test.smpUtilityFunctions;

import java.net.ServerSocket;

public class AppiumPortFactory {
	public int create() throws Exception {
		ServerSocket socket = new ServerSocket(0);
		socket.setReuseAddress(true);
		int port = socket.getLocalPort();
		socket.close();
		return port;
	}

}
