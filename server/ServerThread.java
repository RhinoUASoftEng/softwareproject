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
				//out.println(outputLine);
				while ((inputLine = in.readLine()) != null) {
				try {
					Class.forName("org.postgresql.Driver");
					Connection c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/testdb", "postgres", "default");
					runSQL(c, out, in, inputLine);
				} catch (Exception e) {
					System.out.println(e);
				}
				}
				/*
				
				while ((inputLine = in.readLine()) != null) {
					if (inputLine.equals("Done")) {
						break;
					}
					outputLine = "Received: " + inputLine;
					System.out.println(outputLine);
					out.println(outputLine);
				} 
				*/
				//out.println("dummy");
				System.out.println("Connection closed from client.");
				socket.close();
			} catch (IOException e) {
				System.out.println(e);
			}
	}
	
	public void runSQL(Connection c, PrintWriter out, BufferedReader in, String input) {
		String[] inputarray;
		try {
			PreparedStatement pst;
			inputarray = input.split(" ");
			if (inputarray[0].equals("read")) {
				String query = "SELECT * FROM users WHERE email='" + inputarray[1] + "'";
				pst = c.prepareStatement(query);
				//pst.setString(1, inputarray[1]);
				ResultSet rs = pst.executeQuery();
				while (rs.next()) {
					out.println(rs.getString("email"));
				}
				out.println("done");
			}
			else {
				String query = "INSERT INTO users (email) values (?)";
				pst = c.prepareStatement(query);
				pst.setString(1, inputarray[1]);
				pst.executeUpdate();
				out.println("success");
				out.println("done");
			}
		} catch (Exception e) {
			out.println("Error occurred.");
			System.out.println(e);
		} 
	}
}
