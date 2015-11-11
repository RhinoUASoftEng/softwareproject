package org.softwareenginnering.projecthoneybadger;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ScrollView;
import android.widget.TextView;

import org.softwareenginnering.projecthoneybadger.R;
import org.softwareenginnering.projecthoneybadger.inventory;
import java.util.ArrayList;
import java.util.List;

public class InventoryScrollListAdapter extends ArrayAdapter<inventory> {
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = LayoutInflater.from(this.getContext());
            view = inflater.inflate(R.layout.activity_view_inventory, parent, false);
        }

        inventory inventory = getItem(position);
        String setInventory = "";
        if (inventory != null) {
            TextView textView = (TextView) view.findViewById(R.id.textView);
            if (textView != null) {
                setInventory = "Name: " + inventory.getProductItem() + ' ' + "Quantity: " + Integer.toString(inventory.getQuantity()) + ' ' + "Cost: $" + Double.toString(inventory.getCost());
                textView.setText(setInventory);
            }
        }
            return view;
        }


    public InventoryScrollListAdapter(Context context, List<inventory> inventoryList)
    {
        super(context,R.layout.activity_view_inventory,inventoryList);
    }
}
