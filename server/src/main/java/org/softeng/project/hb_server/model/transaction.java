package org.softeng.project.hb_server.model;

import java.util.Date;
import java.util.UUID;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class transaction {
	@XmlElement
	private UUID ID;
	@XmlElement
	private UUID emp;
	@XmlElement
	private UUID prod;
	@XmlElement
	private Date date_time;
	
	public transaction() {
		
	}
	
	public transaction(UUID ID, UUID emp, UUID prod, Date date_time) {
		this.ID = ID;
		this.emp = emp;
		this.prod = prod;
		this.date_time = date_time;
	}
	
	public UUID getID() {
		return this.ID;
	}
	public void setID(UUID iD) {
		this.ID = iD;
	}
	public UUID getEmp() {
		return this.emp;
	}
	public void setEmp(UUID emp) {
		this.emp = emp;
	}
	public UUID getProd() {
		return this.prod;
	}
	public void setProd(UUID prod) {
		this.prod = prod;
	}
	public Date getDate_time() {
		return this.date_time;
	}
	public void setDate_time(Date date_time) {
		this.date_time = date_time;
	}
	

}
