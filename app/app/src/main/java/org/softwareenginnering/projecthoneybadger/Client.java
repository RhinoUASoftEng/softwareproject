package org.softwareenginnering.projecthoneybadger;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ScrollView;

import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;
public class Client {
    private UUID id;

    public UUID getId()
    {
        return this.id;
    }

    public void setId(UUID id)
    {
        this.id = id;
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


    public Client()
    {
        this.name = "";
        this.email = "";
        this.phone = "";
        this.id = new UUID(0,0);

    }
}
