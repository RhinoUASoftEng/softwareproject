import java.net.*;
import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.*;

public class ServerThread extends Thread {
	private Socket socket = null;
	
	public ServerThread(Socket socket) {
		super("ServerThread");
		this.socket = socket;
	}
	
	public void run(){
		try (
			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			) {
				String inputLine, outputLine;
				outputLine = "Connected...";
				System.out.println(outputLine);
				out.println(outputLine);
				try {
					Class.forName("/tools/postgesql-9.4-1203.jdbc4.jar/org.postgresql.Driver");
					Connection c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/testdb", "postgres", "default");
				} catch (Exception e) {
					System.out.println(e);
				}
				
				
				
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