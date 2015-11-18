package org.softwareenginnering.projecthoneybadger;

import java.util.UUID;

public class Transaction {
    private UUID id;

    public UUID getId()
    {
        return this.id;
    }

    public void setId(UUID id)
    {
        this.id = id;
    }

    private String employee;
    private String date;
    private String item;
    private String amount;
    public Transaction setEmployee(String Employee)
    {
        this.employee = Employee;
        return this;
    }

    public Transaction setAmount(String Amount)
    {
        this.amount = Amount;
        return this;
    }

    public String getAmount()
    {
        return this.amount;
    }
    public String getEmployee()
    {
        return this.employee;
    }

    public Transaction setDate(String date)
    {
        this.date = date;
        return this;
    }

    public String getDate()
    {
        return this.date;
    }

    public Transaction setItem(String item)
    {
        this.item = item;
        return this;
    }

    public String getItem()
    {
        return this.item;
    }

    public Transaction()
    {
        this.id = new UUID(0,0);
        this.item = "";
        this.employee = "";
        this.amount = "";
    }


}
