package org.softwareenginnering.projecthoneybadger;

import org.softwareenginnering.projecthoneybadger.inventory;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class InventoryService {
    inventory i1;
    inventory i2;
    inventory i3;
    List<inventory> inventories;
    InventoryService()
    {
        i1 = new inventory();
        i2 = new inventory();
        i3 = new inventory();
        i1.setId(UUID.randomUUID());
        i2.setId(UUID.randomUUID());
        i3.setId(UUID.randomUUID());
        i1.setProductItem("i1");
        i2.setProductItem("i2");
        i3.setProductItem("i3");
        i1.setCost(250.00);
        i2.setCost(150.00);
        i3.setCost(35.20);
        i1.setQuantity(20);
        i2.setQuantity(30);
        i3.setQuantity(200);
        i1.setReorderLimit(2);
        i2.setReorderLimit(2);
        i3.setReorderLimit(2);
        inventories = new ArrayList<>();
    }

    public List<inventory> getInventories()
    {
        inventories.add(i1);
        inventories.add(i2);
        inventories.add(i3);

        return inventories;
    }


}
