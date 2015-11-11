package org.softwareenginnering.projecthoneybadger;

import java.util.UUID;


public class Event {
    private UUID id;

    public UUID getId()
    {
        return this.id;
    }

    public void setId(UUID id)
    {
        this.id = id;
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

    private String name;

    public String getName()
    {
        return this.name;
    }

    public Event setName(String Name)
    {
        this.name = Name;
        return this;
    }
    private String date;
    public String getDate()
    {
        return this.date;
    }

    public Event setDate(String Date)
    {
        this.date = Date;
        return this;
    }

    private String time;

    public Event setTime(String Time)
    {
        this.time = Time;
        return this;
    }

    public String getTime()
    {
        return this.time;
    }


    public Event()
    {
        this.name = "";
        this.address = "";
        this.date = "";
        this.time = "";
        this.id = new UUID(0,0);

    }
}
