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

    private String productItem;

    public String getProductItem()
    {
        return this.productItem;
    }

    public Event setProductItem(String productItem)
    {
        this.productItem = productItem;
        return this;
    }
    private double Cost;
    public Double getCost()
    {
        return this.Cost;
    }

    public Event setCost(double cost)
    {
        this.Cost = cost;
        return this;
    }

    private int reorder;

    public Event setReorderLimit(int reOrder)
    {
        this.reorder = reOrder;
        return this;
    }

    public int getReorderLimit()
    {
        return this.reorder;
    }

    private int quantity;

    public Event setQuantity(int quantity)
    {
        this.quantity = quantity;
        return this;
    }

    public int getQuantity()
    {
        return this.quantity;
    }

    public Event()
    {
        this.productItem = "";
        this.Cost = 0;
        this.quantity = 0;
        this.reorder = 0;
        this.id = new UUID(0,0);

    }
}
