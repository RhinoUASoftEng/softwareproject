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
	ResultSet rs;
	event temp_event;
	List<event> eventList;
	public final String TABLE_NAME = "events";
	
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
				this.eventList.add(readFromRs(rs));
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
				this.eventList.add(readFromRs(rs));
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
	
	private event readFromRs(ResultSet rs) {
		try {
			temp_event = new event();
			temp_event.setID(UUID.fromString(this.rs.getString("ID")));
			temp_event.setClient_ID(UUID.fromString(this.rs.getString("client_id")));
			temp_event.setAddress(this.rs.getString("address"));
			temp_event.setCity(this.rs.getString("city"));
			temp_event.setState(this.rs.getString("state"));
			temp_event.setZip(this.rs.getString("zip"));			
		} catch (Exception e) {
			System.out.println(e);
		}
		return temp_event;
	}
}
