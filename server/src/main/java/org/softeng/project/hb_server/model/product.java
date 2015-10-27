package org.softeng.project.hb_server.model;

import java.util.UUID;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class product {
	private UUID ID;
	private String name;
	
	public product() {
		
	}
	
	public product(UUID ID, String name) {
		this.ID = ID;
		this.name = name;
	}
	
	public UUID getID() {
		return ID;
	}
	public void setID(UUID iD) {
		ID = iD;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
