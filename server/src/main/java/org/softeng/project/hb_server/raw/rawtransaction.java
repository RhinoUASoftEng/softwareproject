package org.softeng.project.hb_server.raw;

import java.util.Date;
import java.util.UUID;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class rawtransaction {
	private String ID;
	private String emp;
	private String prod;
	private Date date_time;
	
	public rawtransaction() {
		
	}
	
	public rawtransaction(String iD, String emp, String prod, Date date_time) {
		super();
		ID = iD;
		this.emp = emp;
		this.prod = prod;
		this.date_time = date_time;
	}
	
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public String getEmp() {
		return emp;
	}
	public void setEmp(String emp) {
		this.emp = emp;
	}
	public String getProd() {
		return prod;
	}
	public void setProd(String prod) {
		this.prod = prod;
	}
	public Date getDate_time() {
		return date_time;
	}
	public void setDate_time(Date date_time) {
		this.date_time = date_time;
	}
	
}