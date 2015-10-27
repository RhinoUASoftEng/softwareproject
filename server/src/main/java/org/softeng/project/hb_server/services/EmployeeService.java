package org.softeng.project.hb_server.services;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.xml.bind.JAXBElement;

import org.softeng.project.hb_server.model.employee;


public class EmployeeService {
	
	DataService dataService = new DataService("postgres");
	public final String TABLE_NAME = "employees";
	ResultSet rs;
	employee temp_employee;
	List<employee> employeeList;
	UUID tempID;
	String tempf_name;
	String templ_name;
	int tempposition;
	
	public EmployeeService() {
		this.rs = null;
		employeeList = new ArrayList();
	}
	
	/* */
	@SuppressWarnings("null")
	public List<employee> getAllEmployees() {
		this.rs = dataService.queryAll(TABLE_NAME);		
		try {
			
			while (this.rs.next()) {
				this.tempID = UUID.fromString(this.rs.getString("ID"));
				this.tempf_name = this.rs.getString("f_name");
				this.templ_name = this.rs.getString("l_name");
				this.tempposition = this.rs.getInt("position");
				this.temp_employee = new employee(tempID, tempf_name, templ_name, tempposition);
				this.employeeList.add(temp_employee);
			}
			
		} catch (Exception e) {
			System.out.println(e);
		}
		return employeeList;
	}
	/* */

	public List<employee> getEmployee(UUID employeeID) {
		this.rs = dataService.queryOne(TABLE_NAME, employeeID);
		
		try {
			while (this.rs.next()) {
				this.tempID = UUID.fromString(this.rs.getString("ID"));
				this.tempf_name = this.rs.getString("f_name");
				this.templ_name = this.rs.getString("l_name");
				this.tempposition = this.rs.getInt("position");
				this.temp_employee = new employee(tempID, tempf_name, templ_name, tempposition);
				this.employeeList.add(temp_employee);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return employeeList;
	}

	public List<employee> createEmployee(JAXBElement<employee> apiemployee) {
		temp_employee = apiemployee.getValue();
		temp_employee.setID(UUID.randomUUID());
		dataService.insertOneEmployee(TABLE_NAME, temp_employee);
		this.employeeList.add(new employee(tempID, tempf_name, templ_name, tempposition));
		return this.employeeList;
	}
}
