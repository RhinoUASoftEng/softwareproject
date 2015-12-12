package org.softwareenginnering.projecthoneybadger;

import java.util.UUID;

public class Transaction {
    private UUID ID;

    public UUID getId()
    {
        return this.ID;
    }

    public void setId(UUID id)
    {
        this.ID = id;
    }

    private String emp_id;
    private String date_time;
    private String product_id;
    private String amount;
    public Transaction setEmployee(String Employee)
    {
        this.emp_id = Employee;
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
        return this.emp_id;
    }

    public Transaction setDate(String date)
    {
        this.date_time = date;
        return this;
    }

    public String getDate()
    {
        return this.date_time;
    }

    public Transaction setItem(String item)
    {
        this.product_id = item;
        return this;
    }

    public String getItem()
    {
        return this.product_id;
    }

    public Transaction()
    {
        this.ID = new UUID(0,0);
        this.product_id = "";
        this.emp_id = "";
        this.amount = "";
    }


}
