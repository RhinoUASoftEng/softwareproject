package org.softwareenginnering.projecthoneybadger;

        import android.app.AlertDialog;
        import android.content.DialogInterface;
        import android.content.Intent;
        import android.graphics.Color;
        import android.graphics.Typeface;
        import android.os.AsyncTask;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.LayoutInflater;
        import android.view.Menu;
        import android.view.MenuItem;
        import android.view.View;
        import android.widget.EditText;
        import android.widget.ListView;
        import org.softwareenginnering.projecthoneybadger.InventoryScrollListAdapter;
        import org.softwareenginnering.projecthoneybadger.inventory;
        import android.widget.AdapterView;
        import android.widget.TextView;
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
                final int pos = position;
                inventory selectedInventory = (inventory) getinventoryListView().getItemAtPosition(position);
                TextView textView = new TextView(manageInventory.this);
                textView.setTextColor(Color.parseColor("#7A0101"));
                textView.setTypeface(Typeface.SANS_SERIF);
                textView.setText("Inventory: " + selectedInventory.getProductItem() + "\n" + "Vendor: " + selectedInventory.getVendor()
                        + "\n" + "Cost: $" + selectedInventory.getCost() + "\n" + "Quantity: " + selectedInventory.getQuantity() + "\n"
                        + "Reorder Limit: " + selectedInventory.getReorderLimit());

                AlertDialog.Builder builder = new AlertDialog.Builder(manageInventory.this);
                builder.setView(textView);
                builder.setTitle(selectedInventory.getProductItem());
                builder.setPositiveButton(R.string.editProduct, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Intent viewProductIntent = new Intent(manageInventory.this,EditInventory.class);
                        inventory selectedInventory = (inventory) getinventoryListView().getItemAtPosition(pos);
                        viewProductIntent.putExtra("UUID", selectedInventory.getId());
                        viewProductIntent.putExtra("Inventory", selectedInventory.getProductItem());
                        viewProductIntent.putExtra("Quantity", Integer.toString(selectedInventory.getQuantity()));
                        viewProductIntent.putExtra("Cost", Double.toString(selectedInventory.getCost()));
                        viewProductIntent.putExtra("Vendor", selectedInventory.getVendor());
                        viewProductIntent.putExtra("Reorder Limit", Integer.toString(selectedInventory.getReorderLimit()));
                        startActivity(viewProductIntent);
                    }
                });

                builder.setNegativeButton(R.string.delete, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        DeleteFromServer temp = new DeleteFromServer();
                        temp.sendDataAsync((inventory) getinventoryListView().getItemAtPosition(pos));
                        temp.execute();
                    }
                });
                AlertDialog display = builder.create();
                display.show();
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
    private int quantity = 0;
    private int reorderLimit = 0;
    private int itemReorderCount = 0;
    private String item = "";
    private String itemReorderList = "";
    private String itemReorder = "";
    private class RetrieveInventoriesTask extends AsyncTask<Void, Void, List<inventory>>{
        protected List<inventory> doInBackground(Void ... params) {
            return (new InventoryService()).getAll();
            //return (new InventoryService()).getInventories();

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
                    if(emptyString) {
                        item = itemReorder;
                        emptyString = false;
                    }
                    else
                    {
                        item = item + ',' + ' ' + itemReorder;
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
                final inventory searchingInventory = temp;
                productDoesNotExist = false;
                TextView textView = new TextView(manageInventory.this);
                textView.setTextColor(Color.parseColor("#7A0101"));
                textView.setTypeface(Typeface.SANS_SERIF);
                textView.setText("Inventory: " + temp.getProductItem() + "\n" + "Vendor: " + temp.getVendor()
                        + "\n" + "Cost: $" + temp.getCost() + "\n" + "Quantity: " + temp.getQuantity() + "\n" + "Reorder Limit: " + temp.getReorderLimit());
                //startActivity(viewProductIntent);

                AlertDialog.Builder builder = new AlertDialog.Builder(manageInventory.this);
                builder.setView(textView);
                builder.setTitle(temp.getProductItem());
                builder.setNeutralButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //cancel(true);
                    }
                });
                builder.setPositiveButton(R.string.editProduct, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Intent viewProductIntent = new Intent(manageInventory.this,EditInventory.class);
                        viewProductIntent.putExtra("UUID", searchingInventory.getId());
                        viewProductIntent.putExtra("Inventory", searchingInventory.getProductItem());
                        viewProductIntent.putExtra("Quantity", Integer.toString(searchingInventory.getQuantity()));
                        viewProductIntent.putExtra("Cost", Double.toString(searchingInventory.getCost()));
                        viewProductIntent.putExtra("Vendor", searchingInventory.getVendor());
                        viewProductIntent.putExtra("Reorder Limit", Integer.toString(searchingInventory.getReorderLimit()));
                        startActivity(viewProductIntent);
                    }
                });

                builder.setNegativeButton(R.string.delete, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        DeleteFromServer temp = new DeleteFromServer();
                        temp.sendDataAsync(searchingInventory);
                        temp.execute();

                    }
                });
                AlertDialog display = builder.create();
                display.show();
                break;
            }
            else
            {
                productDoesNotExist = true;
            }
        }
        if(productDoesNotExist)
        {
            Toast.makeText(getApplicationContext(), "This Item does not exist!", Toast.LENGTH_LONG).show();
        }
    }

    private class DeleteFromServer extends AsyncTask<Void, Void, Void> {
        inventory obj;

        public void sendDataAsync(inventory removeThis) {
            this.obj = removeThis;
        }

        protected Void doInBackground(Void... v) {
            (new InventoryService()).delete(obj);
            return null;
        }
    }
}
