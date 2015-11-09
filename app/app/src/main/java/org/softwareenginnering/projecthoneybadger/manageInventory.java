package org.softwareenginnering.projecthoneybadger;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import org.softwareenginnering.projecthoneybadger.InventoryScrollListAdapter;
import org.softwareenginnering.projecthoneybadger.inventory;
import android.widget.AdapterView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class manageInventory extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_inventory);
        Intent intent = getIntent();

        this.Inventory = new ArrayList<>();
        this.inventoryScrollListAdapter = new InventoryScrollListAdapter(this,this.Inventory);
        this.getinventoryListView().setAdapter(this.inventoryScrollListAdapter);
        this.getinventoryListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                Intent viewProductIntent = new Intent(manageInventory.this, viewProduct.class);
                inventory selectedInventory = (inventory) getinventoryListView().getItemAtPosition(position);

                viewProductIntent.putExtra("Inventory", selectedInventory.getProductItem());
                viewProductIntent.putExtra("Quantity", Integer.toString(selectedInventory.getQuantity()));
                viewProductIntent.putExtra("Cost", Double.toString(selectedInventory.getCost()));
                viewProductIntent.putExtra("Reorder Limit", Integer.toString(selectedInventory.getReorderLimit()));
                startActivity(viewProductIntent);
            }
        });
    }
    @Override
    protected void onResume()
    {
        super.onResume();
        (new RetrieveInventoriesTask()).execute();
    }


    private ListView getinventoryListView()
    {
        return (ListView) this.findViewById(R.id.listView);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_manage_inventory, menu);
        return true;
    }

    private List<inventory> Inventory;
    private InventoryScrollListAdapter inventoryScrollListAdapter;
    private int quantity;
    private int reorderLimit;
    private int itemReorderCount;
    private String item = "";
    private String itemReorderList = "";
    private String itemReorder = "";
    private class RetrieveInventoriesTask extends AsyncTask<Void, Void, List<inventory>>{
        protected List<inventory> doInBackground(Void ... params) {
            return (new InventoryService()).getInventories();
        }

        protected void onPostExecute(List<inventory> results)
        {
            Inventory.clear();
            boolean emptyString = true;
            itemReorderCount = 0;
            for(inventory Inventories : results)
            {
                quantity = Inventories.getQuantity();
                reorderLimit = Inventories.getReorderLimit();
                if(quantity <= reorderLimit)
                {
                    itemReorderCount++;
                    itemReorder = Inventories.getProductItem();
                    if(emptyString == true) {
                        item = itemReorder;
                        emptyString = false;
                    }
                    else
                    {
                        item = item + ' ' + itemReorder;
                    }
                    itemReorderList = "You have: " + Integer.toString(itemReorderCount) + " to reorder. " + item;
                }
                Inventory.add(Inventories);
            }
            if(itemReorderCount != 0)
            {
                AlertDialog.Builder builder = new AlertDialog.Builder(manageInventory.this);
                builder.setMessage(itemReorderList);
                builder.setTitle(R.string.alert);
                builder.setNeutralButton(R.string.ok,new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        cancel(true);
                    }
                });
                AlertDialog display = builder.create();
                display.show();
                //Toast.makeText(getApplicationContext(), itemReorderList, Toast.LENGTH_LONG).show();
            }
            inventoryScrollListAdapter.notifyDataSetChanged();
        }
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

    public void backMainMenu(View view)
    {
        Intent mainMenuIntent = new Intent(this, MainMenu.class);
        startActivity(mainMenuIntent);
    }

    public void addInventory(View view)
    {
        Intent addInventoryIntent = new Intent(this, addInventory.class);
        startActivity(addInventoryIntent);
    }

    public void searchItems(View view)
    {
        EditText search = (EditText) findViewById(R.id.search_field);
        String searchingItem = search.getText().toString();
        boolean productDoesNotExist = false;
        for(inventory temp : Inventory)
        {
            if(temp.getProductItem().equals(searchingItem))
            {
                productDoesNotExist = false;
                Intent viewProduct = new Intent(manageInventory.this, viewProduct.class);
                viewProduct.putExtra("Inventory", temp.getProductItem());
                viewProduct.putExtra("Quantity", Integer.toString(temp.getQuantity()));
                viewProduct.putExtra("Cost", Double.toString(temp.getCost()));
                viewProduct.putExtra("Reorder Limit", Integer.toString(temp.getReorderLimit()));
                startActivity(viewProduct);
                break;
            }

            else
            {
                productDoesNotExist = true;
            }
        }
        if(productDoesNotExist == true)
        {
            Toast.makeText(getApplicationContext(), "This Item does not exist!", Toast.LENGTH_LONG).show();
        }
    }


}
