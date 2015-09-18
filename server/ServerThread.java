import java.net.*;
import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;

public class ServerThread extends Thread {
	private Socket socket = null;
	
	public ServerThread(Socket socket) {
		super("ServerThread");
		this.socket = socket;
	}
	
	public void run() {
		try (
			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			) {
				String inputLine, outputLine;
				outputLine = "Connected...";
				System.out.println(outputLine);
				out.println(outputLine);
				
				Connection c = new DriverManager.getConnection("jdbc:postgresql://localhost:5432/testdb", "postgres", "defaut");
				
				
				
				while ((inputLine = in.readLine()) != null) {
					if (inputLine.equals("Done")) {
						break;
					}
					outputLine = "Received: " + inputLine;
					System.out.println(outputLine);
					out.println(outputLine);
				} 
				System.out.println("Connection closed from client.");
				socket.close();
			} catch (IOException e) {
				System.out.println(e);
			}
	}
}