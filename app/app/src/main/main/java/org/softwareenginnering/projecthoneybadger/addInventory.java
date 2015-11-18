package org.softwareenginnering.projecthoneybadger;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class addInventory extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_inventory);
        Intent intent = getIntent();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_inventory, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void submitInventory(View view)
    {
        InventoryService inventoryService = new InventoryService();
        inventoryService.getInventories();
        EditText name = (EditText) findViewById(R.id.name);
        EditText cost = (EditText) findViewById(R.id.cost);
        EditText vendor = (EditText) findViewById(R.id.vendors);
        EditText reorder = (EditText) findViewById(R.id.reorder);
        EditText quantity = (EditText) findViewById(R.id.quantity);
        String productName = name.getText().toString();
        String productVendor = vendor.getText().toString();
        int numberOfProductsToReorder = Integer.parseInt(reorder.getText().toString());
        int numberOfProductsinStock = Integer.parseInt(quantity.getText().toString());
        Double costPerProduct =  Double.parseDouble(cost.getText().toString());

        inventory newInventory = new inventory();
        newInventory.setId(UUID.randomUUID());
        newInventory.setProductItem(productName);
        newInventory.setVendor(productVendor);
        newInventory.setQuantity(numberOfProductsinStock);
        newInventory.setReorderLimit(numberOfProductsToReorder);
        newInventory.setCost(costPerProduct);

        //inventoryService.setInventories(newInventory);
        (new CreateInventoriesTask()).execute(newInventory);

        Intent manageInventoryintent = new Intent(this, manageInventory.class);
        startActivity(manageInventoryintent);

    }

    public void backToManageInventory(View view)
    {
        Intent manageInventoryintent = new Intent(this, manageInventory.class);
        startActivity(manageInventoryintent);
    }

    private class CreateInventoriesTask extends AsyncTask<inventory, Void, Boolean> {
        protected Boolean doInBackground(inventory... params) {
            (new InventoryService()).create(params[0]);
            return true;
        }
    }
}
