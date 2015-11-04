package org.softwareenginnering.projecthoneybadger;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class manageClients extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_inventory);
        Intent intent = getIntent();

        this.Inventory = new ArrayList<>();
        this.inventoryScrollListAdapter = new InventoryScrollListAdapter(this, this.Inventory);
        this.getClientListView().setAdapter(this.inventoryScrollListAdapter);
        this.getClientListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent viewProductIntent = new Intent(getApplicationContext(), viewProduct.class);
                inventory selectedInventory = (inventory) getClientListView().getItemAtPosition(position);

                viewProductIntent.putExtra(getString(R.string.Inventory), selectedInventory.getId().toString());
                startActivity(viewProductIntent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        (new RetrieveInventoriesTask()).execute();
    }


    private ListView getClientListView() {
        return (ListView) this.findViewById(R.id.clientListView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_manage_inventory, menu);
        return true;
    }

    private List<inventory> Inventory;
    private InventoryScrollListAdapter inventoryScrollListAdapter;

    private class RetrieveInventoriesTask extends AsyncTask<Void, Void, List<inventory>> {
        protected List<inventory> doInBackground(Void... params) {
            return (new InventoryService()).getInventories();
        }

        protected void onPostExecute(List<inventory> results) {
            Inventory.clear();

            for (inventory Inventories : results) {
                Inventory.add(Inventories);
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

    public void backMainMenu(View view) {
        Intent mainMenuIntent = new Intent(this, MainMenu.class);
        startActivity(mainMenuIntent);
    }

    public void addClients(View view) {
        Intent addInventoryIntent = new Intent(this, addInventory.class);
        startActivity(addInventoryIntent);
    }

    public void searchClients(View view) {
        EditText search = (EditText) findViewById(R.id.search_field);
        String searchingItem = search.getText().toString();

        for (inventory temp : Inventory) {
            if (temp.getProductItem().equals(searchingItem)) {

            }
        }

        Toast.makeText(getApplicationContext(), "This Item does not exist", Toast.LENGTH_LONG).show();
    }
}