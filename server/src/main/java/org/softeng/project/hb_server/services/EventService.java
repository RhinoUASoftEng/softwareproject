package org.softeng.project.hb_server.services;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.xml.bind.JAXBElement;

import org.softeng.project.hb_server.model.event;
import org.softeng.project.hb_server.raw.rawevent;


public class EventService {
	
	DataService dataService = new DataService("postgres");
	public final String TABLE_NAME = "events";
	ResultSet rs;
	event temp_event;
	List<event> eventList;
	
	UUID tempID;
	UUID tempclient;
	String tempaddress;
	String tempcity;
	String tempstate;
	String tempzip;
	Date tempDate;
	
	
	public EventService() {
		this.rs = null;
		eventList = new ArrayList();
	}
	
	@SuppressWarnings("null")
	public List<event> getAllEvents() {
		this.rs = dataService.queryAll(TABLE_NAME);		
		try {
			while (this.rs.next()) {
				this.tempID = UUID.fromString(this.rs.getString("ID"));
				this.tempclient = UUID.fromString(this.rs.getString("client_id"));
				this.tempaddress = this.rs.getString("address");
				this.tempcity = this.rs.getString("city");
				this.tempstate = this.rs.getString("state");
				this.temp_event = new event(tempID, new Date(), tempclient, tempaddress, tempcity, tempstate, tempzip, new Date());
				this.eventList.add(temp_event);
			}
			
		} catch (Exception e) {
			System.out.println(e);
		}
		return eventList;
	}

	public List<event> getEvent(UUID eventID) {
		this.rs = dataService.queryOne(TABLE_NAME, eventID);
		try {
			while (this.rs.next()) {
				this.tempID = UUID.fromString(this.rs.getString("ID"));
				this.tempclient = UUID.fromString(this.rs.getString("client_id"));
				this.tempaddress = this.rs.getString("address");
				this.tempcity = this.rs.getString("city");
				this.tempstate = this.rs.getString("state");
				this.temp_event = new event(tempID, new Date(), tempclient, tempaddress, tempcity, tempstate, tempzip, new Date());
				this.eventList.add(temp_event);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return eventList;
	}

	public List<event> createEvent(JAXBElement<rawevent> apievent) {
		tempclient = UUID.fromString(apievent.getValue().getClient_ID());
		tempaddress = apievent.getValue().getAddress();
		tempcity = apievent.getValue().getCity();
		tempstate = apievent.getValue().getState();
		tempzip = apievent.getValue().getZip();
		tempDate = new Date();
		
		temp_event = new event(UUID.randomUUID(), tempDate, tempclient, tempaddress, tempcity, tempstate, tempzip, tempDate);
		
		dataService.insertOneEvent(TABLE_NAME, temp_event);
		this.eventList.add(temp_event);
		return this.eventList;
	}
}
