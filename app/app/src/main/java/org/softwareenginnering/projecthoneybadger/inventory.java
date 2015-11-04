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

public class inventory {
    private UUID id;

    public UUID getId()
    {
        return this.id;
    }

    public void setId(UUID id)
    {
        this.id = id;
    }

    private String productItem;

    public String getProductItem()
    {
        return this.productItem;
    }

    public inventory setProductItem(String productItem)
    {
        this.productItem = productItem;
        return this;
    }
    private double Cost;
    public Double getCost()
    {
        return this.Cost;
    }

    public inventory setCost(double cost)
    {
        this.Cost = cost;
        return this;
    }

    private int reorder;

    public inventory setReorderLimit(int reOrder)
    {
        this.reorder = reOrder;
        return this;
    }

    public int getReorderLimit()
    {
        return this.reorder;
    }

    private int quantity;

    public inventory setQuantity(int quantity)
    {
        this.quantity = quantity;
        return this;
    }

    public int getQuantity()
    {
        return this.quantity;
    }

    public inventory()
    {
        this.productItem = "";
        this.Cost = 0;
        this.quantity = 0;
        this.reorder = 0;
        this.id = new UUID(0,0);

    }
}
