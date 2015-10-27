package org.softeng.project.hb_server.services;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.xml.bind.JAXBElement;

import org.softeng.project.hb_server.model.transaction;
import org.softeng.project.hb_server.raw.rawtransaction;


public class TransactionService {
	
	DataService dataService = new DataService("postgres");
	public final String TABLE_NAME = "transactions";
	ResultSet rs;
	transaction temp_transaction;
	List<transaction> transactionList;
	
	UUID tempID;
	UUID tempEMP;
	UUID tempPROD;
	Date tempDate_time;
	
	public TransactionService() {
		this.rs = null;
		transactionList = new ArrayList();
	}
	
	/* */
	@SuppressWarnings("null")
	public List<transaction> getAllTransactions() {
		
		this.rs = dataService.queryAll(TABLE_NAME);
		//List<transaction> transactionList = new ArrayList();
		
		try {
			
			while (this.rs.next()) {
				this.tempID = UUID.fromString(this.rs.getString("ID"));
				this.tempEMP = UUID.fromString(this.rs.getString("emp_ID"));
				this.tempPROD = UUID.fromString(this.rs.getString("product_ID"));
				this.tempDate_time = this.rs.getDate(4);
				this.temp_transaction = new transaction(tempID, tempEMP, tempPROD, tempDate_time);
				this.transactionList.add(temp_transaction);
			}
			
		} catch (Exception e) {
			System.out.println(e);
		}
		return transactionList;
	}
	/* */

	public List<transaction> getTransaction(UUID transactionID) {
		this.rs = dataService.queryOne(TABLE_NAME, transactionID);
		//List<transaction> transactionList = new ArrayList();
		
		try {
			while (this.rs.next()) {
				this.tempID = UUID.fromString(this.rs.getString("ID"));
				this.tempEMP = UUID.fromString(this.rs.getString("emp_ID"));
				this.tempPROD = UUID.fromString(this.rs.getString("product_ID"));
				this.tempDate_time = this.rs.getDate(4);
				this.temp_transaction = new transaction(tempID, tempEMP, tempPROD, tempDate_time);
				this.transactionList.add(temp_transaction);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return transactionList;
	}

	public List<transaction> createTransaction(JAXBElement<rawtransaction> apitransaction) {
		tempID = UUID.fromString(apitransaction.getValue().getID());
		tempEMP = UUID.fromString(apitransaction.getValue().getEmp());
		tempPROD = UUID.fromString(apitransaction.getValue().getProd());
		tempDate_time = new Date();

		temp_transaction = new transaction();
		temp_transaction.setID(UUID.randomUUID());
		temp_transaction.setEmp(tempEMP);
		temp_transaction.setProd(tempPROD);
		temp_transaction.setDate_time(tempDate_time);
		
		dataService.insertOneTransaction(TABLE_NAME, temp_transaction);
		this.transactionList.add(new transaction(tempID, tempEMP, tempPROD, tempDate_time));
		return this.transactionList;
	}
}
