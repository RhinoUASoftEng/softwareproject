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
				
				while ((fromServer = in.readLine()) != null) {
					System.out.println("Server: " + fromServer);
					
					fromUser = stdIn.readLine();
					if (fromUser != null) {
						System.out.println("Client: " + fromUser);
						out.println(fromUser);
					}
				}
			} catch (IOException e) {
				System.out.println(e);
			}
	}
}