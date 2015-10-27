package org.softeng.project.hb_server.services;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.xml.bind.JAXBElement;

import org.softeng.project.hb_server.model.client;

public class ClientService {
	
	DataService dataService = new DataService("postgres");
	public final String TABLE_NAME = "clients";
	ResultSet rs;
	client temp_client;
	List<client> clientList;
	
	UUID tempID;
	String tempname;
	String tempaddress;
	String tempcity;
	String tempstate;
	String tempzip;
	String tempphone;
	String tempemail;
	
	public ClientService() {
		this.rs = null;
		clientList = new ArrayList();
	}

	@SuppressWarnings("null")
	public List<client> getAllClients() {
		this.rs = dataService.queryAll(TABLE_NAME);
		try {
			while (this.rs.next()) {
				this.tempID = UUID.fromString(this.rs.getString("ID"));
				this.tempname = this.rs.getString("name");
				this.tempaddress = this.rs.getString("address");
				this.tempcity = this.rs.getString("city");
				this.tempstate = this.rs.getString("state");
				this.tempzip = this.rs.getString("zip");
				this.tempphone = this.rs.getString("phone");
				this.tempemail = this.rs.getString("email");
				this.temp_client = new client(tempID, tempname, tempaddress, tempcity, tempstate, tempzip, tempphone, tempemail);
				this.clientList.add(temp_client);
			}
			
		} catch (Exception e) {
			System.out.println(e);
		}
		return clientList;
	}

	public List<client> getClient(UUID clientID) {
		this.rs = dataService.queryOne(TABLE_NAME, clientID);
		try {
			while (this.rs.next()) {
				this.tempID = UUID.fromString(this.rs.getString("ID"));
				this.tempname = this.rs.getString("name");
				this.tempaddress = this.rs.getString("address");
				this.tempcity = this.rs.getString("city");
				this.tempstate = this.rs.getString("state");
				this.tempzip = this.rs.getString("zip");
				this.tempphone = this.rs.getString("phone");
				this.tempemail = this.rs.getString("email");
				this.temp_client = new client(tempID, tempname, tempaddress, tempcity, tempstate, tempzip, tempphone, tempemail);
				this.clientList.add(temp_client);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return clientList;
	}

	public List<client> createClient(JAXBElement<client> apiclient) {
		temp_client = apiclient.getValue();
		temp_client.setID(UUID.randomUUID());
		
		dataService.insertOneClient(TABLE_NAME, temp_client);
		this.clientList.add(temp_client);
		return this.clientList;
	}
}
