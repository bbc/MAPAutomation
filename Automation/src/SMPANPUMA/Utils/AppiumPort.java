package SMPANPUMA.Utils;

import java.net.ServerSocket;

public class AppiumPort {

		public int create() throws Exception {
			ServerSocket socket = new ServerSocket(0);
			socket.setReuseAddress(true);
			int port = socket.getLocalPort();
			socket.close();
			return port;
		}

	}



