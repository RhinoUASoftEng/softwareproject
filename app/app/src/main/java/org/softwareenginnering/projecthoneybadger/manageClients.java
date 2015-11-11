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

        this.client = new ArrayList<>();
        this.ClientListViewAdapter = new ClientListViewAdapter(this, this.client);
        this.getClientListView().setAdapter(this.ClientListViewAdapter);
        this.getClientListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent viewProductIntent = new Intent(getApplicationContext(), ViewClient.class);
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

    private List<Client> client;
    private ClientListViewAdapter ClientListViewAdapter;

    private class RetrieveInventoriesTask extends AsyncTask<Void, Void, List<Client>> {
        protected List<Client> doInBackground(Void... params) {
            return (new ClientService()).getClients();
        }

        protected void onPostExecute(List<Client> results) {
            client.clear();

            for (Client Inventories : results) {
                client.add(Inventories);
            }

            ClientListViewAdapter.notifyDataSetChanged();
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

        for (Client temp : client) {
            if (temp.getClientName().equals(searchingItem)) {

            }
        }

        Toast.makeText(getApplicationContext(), "This Item does not exist", Toast.LENGTH_LONG).show();
    }
}