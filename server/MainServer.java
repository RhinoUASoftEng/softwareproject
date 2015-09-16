import java.net.*;
import java.io.*;

public class MainServer {
	
	int portNumber;
	boolean listening;
	
	MainServer() {
		int portNumber = 5000;
		listening = true;
	}
	
	void start() {
		System.out.println("Waiting...");
		try (ServerSocket serversock = new ServerSocket(5000)) {
			while (listening) {
				new ServerThread(serversock.accept()).start();
			}
		} catch (IOException e) {
			System.out.println(e);
			System.exit(-1);
		}
	}
}