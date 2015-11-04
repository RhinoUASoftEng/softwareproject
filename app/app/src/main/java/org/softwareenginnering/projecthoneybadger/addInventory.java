package org.softwareenginnering.projecthoneybadger;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;

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
        EditText name = (EditText) findViewById(R.id.name);
        EditText cost = (EditText) findViewById(R.id.cost);
        EditText reorder = (EditText) findViewById(R.id.reorder);
        EditText quantity = (EditText) findViewById(R.id.quantity);
        String productName = name.getText().toString();
        int numberOfProductsToReorder = Integer.parseInt(reorder.getText().toString());
        int numberOfProductsinStock = Integer.parseInt(quantity.getText().toString());
        Double costPerProduct =  Double.parseDouble(cost.getText().toString());

        String product = productName + ' ' + Integer.toString(numberOfProductsToReorder) + ' ' + Double.toString(costPerProduct);

    }

    public void backToManageInventory(View view)
    {
        Intent manageInventoryintent = new Intent(this, manageInventory.class);
        startActivity(manageInventoryintent);
    }
}
