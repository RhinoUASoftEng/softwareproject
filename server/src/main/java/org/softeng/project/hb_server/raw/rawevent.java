package org.softeng.project.hb_server.raw;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class rawevent {
	private String ID;
	private Date date_created;
	private String client_ID;
	private String address;
	private String city;
	private String state;
	private String zip;
	private Date event_date;
	
	public rawevent() {
		
	}
	
	public rawevent(String iD, Date date_created, String client_ID,
			String address, String city, String state, String zip,
			Date event_date) {
		ID = iD;
		this.date_created = date_created;
		this.client_ID = client_ID;
		this.address = address;
		this.city = city;
		this.state = state;
		this.zip = zip;
		this.event_date = event_date;
	}


	public String getID() {
		return ID;
	}


	public void setID(String iD) {
		ID = iD;
	}


	public Date getDate_created() {
		return date_created;
	}


	public void setDate_created(Date date_created) {
		this.date_created = date_created;
	}


	public String getClient_ID() {
		return client_ID;
	}


	public void setClient_ID(String client_ID) {
		this.client_ID = client_ID;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public String getCity() {
		return city;
	}


	public void setCity(String city) {
		this.city = city;
	}


	public String getState() {
		return state;
	}


	public void setState(String state) {
		this.state = state;
	}


	public String getZip() {
		return zip;
	}


	public void setZip(String zip) {
		this.zip = zip;
	}


	public Date getEvent_date() {
		return event_date;
	}


	public void setEvent_date(Date event_date) {
		this.event_date = event_date;
	}
	
}
