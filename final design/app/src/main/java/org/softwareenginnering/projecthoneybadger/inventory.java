package org.softwareenginnering.projecthoneybadger;



import java.util.UUID;

public class inventory {
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

    public String getProductItem()
    {
        return this.name;
    }

    public inventory setProductItem(String productItem)
    {
        this.name = productItem;
        return this;
    }
    private String vendor;
    public inventory setVendor(String vendor)
    {
        this.vendor = vendor;
        return this;
    }

    public String getVendor()
    {
        return vendor;
    }
    private double cost;
    public Double getCost()
    {
        return this.cost;
    }

    public inventory setCost(double cost)
    {
        this.cost = cost;
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

    private int count;

    public inventory setQuantity(int quantity)
    {
        this.count = quantity;
        return this;
    }

    public int getQuantity()
    {
        return this.count;
    }

    public inventory()
    {
        this.name = "";
        this.vendor = "";
        this.cost = 0;
        this.count = 0;
        this.reorder = 0;
        this.ID = new UUID(0,0);

    }
}
