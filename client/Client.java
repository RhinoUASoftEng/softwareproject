import java.net.*;
import java.io.*;

public class Client {
	public static void main(String args[]) {
		int portNumber = 5000;
		String hostName = "localhost";
		
		
		try (
			Socket sock = new Socket(hostName, portNumber);
			PrintWriter out = new PrintWriter(sock.getOutputStream(), true);
			BufferedReader in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
			) {
				BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
				String fromServer;
				String fromUser;
				fromUser = args[0];
				out.println(fromUser);
				fromServer = in.readLine();
				System.out.println(fromServer);
				/*
				while ((fromServer = in.readLine()) != null) {

					if (fromServer.equals("done")) {
						break;
					}
					System.out.println("Server: " + fromServer);
					/*
					fromUser = stdIn.readLine();
					if (fromUser != null) {
						System.out.println("Client: " + fromUser);
						out.println(fromUser);
					}
					/**/
					//out.println("test");
					
				//}
				
			} catch (IOException e) {
				System.out.println(e);
			}
	}
}
