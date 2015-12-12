package org.softwareenginnering.projecthoneybadger;

import java.util.UUID;


public class Event {
    private UUID ID;

    public UUID getId()
    {
        return this.ID;
    }

    public void setId(UUID id)
    {
        this.ID = id;
    }

    private String address;

    public String getAddress()
    {
        return this.address;
    }

    public Event setAddress(String Address)
    {
        this.address = Address;
        return this;
    }

    private String Client_ID;

    public String getName()
    {
        return this.Client_ID;
    }

    public Event setName(String Name)
    {
        this.Client_ID = Name;
        return this;
    }
    private String event_date;
    public String getDate()
    {
        return this.event_date;
    }

    public Event setDate(String Date)
    {
        this.event_date = Date;
        return this;
    }

    private String time;

    public Event setTime(String Time)
    {
        this.time = Time;
        return this;
    }

    private String employee;

    public Event setEmployee(String Employee)
    {
        this.employee = Employee;
        return this;
    }

    public String getEmployee()
    {
        return this.employee;
    }
    public String getTime()
    {
        return this.time;
    }


    public Event()
    {
        this.Client_ID = "";
        this.address = "";
        this.event_date = "";
        this.time = "";
        this.employee = "";
        this.ID = new UUID(0,0);

    }
}
