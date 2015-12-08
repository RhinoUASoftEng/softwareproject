package org.softwareenginnering.projecthoneybadger;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class EditInventory extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_inventory);
        Bundle extras = getIntent().getExtras();
        inventoryid = (UUID) extras.getSerializable("UUID");
        EditText editText = (EditText) findViewById(R.id.name);
        EditText editText1 = (EditText) findViewById(R.id.vendors);
        EditText editText2 = (EditText) findViewById(R.id.cost);
        EditText editText3 = (EditText) findViewById(R.id.quantity);
        EditText editText4 = (EditText) findViewById(R.id.reorder);
        TextView textView = (TextView) findViewById(R.id.TitleIn);
        if(extras != null)
        {
            textView.setText(extras.getString("Inventory"));
            editText.setText(extras.getString("Inventory"));
            editText1.setText(extras.getString("Vendor"));
            editText2.setText(extras.getString("Cost"));
            editText3.setText(extras.getString("Quantity"));
            editText4.setText(extras.getString("Reorder Limit"));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_edit_inventory, menu);
        return true;
    }
    private UUID inventoryid;
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
        List<inventory> existingProducts;
        InventoryService inventoryService = new InventoryService();
        existingProducts = inventoryService.getInventories();
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

        for(inventory existingInventory : existingProducts) {
            if(inventoryid.equals(existingInventory.getId())) {
                existingInventory.setProductItem(productName);
                existingInventory.setVendor(productVendor);
                existingInventory.setQuantity(numberOfProductsinStock);
                existingInventory.setReorderLimit(numberOfProductsToReorder);
                existingInventory.setCost(costPerProduct);
                //inventoryService.setInventories(existingInventory);
                break;
            }
        }
        Intent manageInventoryintent = new Intent(this, manageInventory.class);
        startActivity(manageInventoryintent);

    }

    public void backToManageInventory(View view)
    {
        Intent manageInventoryintent = new Intent(this, manageInventory.class);
        startActivity(manageInventoryintent);
    }


}
