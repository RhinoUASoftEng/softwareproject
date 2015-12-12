package org.softwareenginnering.projecthoneybadger;

import java.util.UUID;
public class Client {
    private UUID ID;

    public UUID getId()
    {
        return this.ID;
    }

    public void setId(UUID id)
    {
        this.ID = id;
    }

    private String name;

    public String getClientName()
    {
        return this.name;
    }

    public Client setClientName(String Name)
    {
        this.name = Name;
        return this;
    }
    private String email;
    public String getEmail()
    {
        return this.email;
    }

    public Client setEmail(String Email)
    {
        this.email = Email;
        return this;
    }

    private String phone;

    public Client setPhoneNumber(String Number)
    {
        this.phone = Number;
        return this;
    }

    public String getPhoneNumber()
    {
        return this.phone;
    }
    private String address;
    public Client setAddress(String address)
    {
        this.address = address;
        return this;
    }

    public String getAddress()
    {
        return this.address;
    }

    public Client()
    {
        this.name = "";
        this.email = "";
        this.phone = "";
        this.address = "";
        this.ID = new UUID(0,0);

    }
}
