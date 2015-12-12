package org.softwareenginnering.projecthoneybadger;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
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
        setContentView(R.layout.activity_manage_clients);
        Intent intent = getIntent();

        this.client = new ArrayList<>();
        this.ClientListViewAdapter = new ClientListViewAdapter(this, this.client);
        this.getClientListView().setAdapter(this.ClientListViewAdapter);
        this.getClientListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final int pos = position;
                Client selectedClient = (Client) getClientListView().getItemAtPosition(position);
                TextView textView = new TextView(manageClients.this);
                textView.setTextColor(Color.parseColor("#7A0101"));
                textView.setTypeface(Typeface.SANS_SERIF);
                textView.setText("Name: " + selectedClient.getClientName() + "\n" + "Address: " + selectedClient.getAddress()
                        + "\n" + "Phone Number: " + selectedClient.getPhoneNumber() + "\n" + "Email: " + selectedClient.getEmail() + "\n");

                AlertDialog.Builder builder = new AlertDialog.Builder(manageClients.this);
                builder.setView(textView);
                builder.setTitle(selectedClient.getClientName());
                builder.setPositiveButton(R.string.editCli, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Intent viewProductIntent = new Intent(manageClients.this, EditClient.class);
                        Client selectedClient = (Client) getClientListView().getItemAtPosition(pos);
                        viewProductIntent.putExtra("UUID", selectedClient.getId());
                        viewProductIntent.putExtra("Name", selectedClient.getClientName());
                        viewProductIntent.putExtra("Address", selectedClient.getAddress());
                        viewProductIntent.putExtra("Phone", selectedClient.getPhoneNumber());
                        viewProductIntent.putExtra("Email", selectedClient.getEmail());
                        startActivity(viewProductIntent);
                    }
                });

                builder.setNegativeButton(R.string.delete, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        DeleteFromServer temp = new DeleteFromServer();
                        temp.sendDataAsync((Client) getClientListView().getItemAtPosition(pos));
                        temp.execute();
                    }
                });
                AlertDialog display = builder.create();
                display.show();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        (new RetrieveClientTask()).execute();
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

    private class RetrieveClientTask extends AsyncTask<Void, Void, List<Client>> {
        protected List<Client> doInBackground(Void... params) {
            return (new ClientService()).getAll();
            //return (new ClientService()).getClients();
        }

        protected void onPostExecute(List<Client> results) {
            client.clear();

            for (Client clients : results) {
                client.add(clients);
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
        Intent addInventoryIntent = new Intent(this, addClient.class);
        startActivity(addInventoryIntent);
    }

    public void searchClients(View view) {
        EditText search = (EditText) findViewById(R.id.search_field);
        String searchingItem = search.getText().toString();
        boolean nonExistingClient = true;
        for (Client temp : client) {
            if (temp.getClientName().equals(searchingItem)) {
                final Client client = temp;
                TextView textView = new TextView(manageClients.this);
                textView.setTextColor(Color.parseColor("#7A0101"));
                textView.setTypeface(Typeface.SANS_SERIF);
                textView.setText("Name: " + client.getClientName() + "\n" + "Address: " + client.getAddress()
                        + "\n" + "Phone Number: " + client.getPhoneNumber() + "\n" + "Email: " + client.getEmail() + "\n");

                AlertDialog.Builder builder = new AlertDialog.Builder(manageClients.this);
                builder.setView(textView);
                builder.setTitle(client.getClientName());
                builder.setPositiveButton(R.string.editCli, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Intent viewProductIntent = new Intent(manageClients.this, EditClient.class);
                        viewProductIntent.putExtra("UUID", client.getId());
                        viewProductIntent.putExtra("Name", client.getClientName());
                        viewProductIntent.putExtra("Address", client.getAddress());
                        viewProductIntent.putExtra("Phone", client.getPhoneNumber());
                        viewProductIntent.putExtra("Email", client.getEmail());
                        startActivity(viewProductIntent);
                    }
                });

                builder.setNegativeButton(R.string.delete, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        DeleteFromServer temp = new DeleteFromServer();
                        temp.sendDataAsync(client);
                        temp.execute();
                    }
                });
                AlertDialog display = builder.create();
                display.show();
            }
        }

        if(nonExistingClient == true) {
            Toast.makeText(getApplicationContext(), "This Client does not exist", Toast.LENGTH_LONG).show();
        }
    }

    private class DeleteFromServer extends AsyncTask<Void, Void, Void> {
        Client obj;

        public void sendDataAsync(Client removeThis) {
            this.obj = removeThis;
        }

        protected Void doInBackground(Void... v) {
            (new ClientService()).delete(obj);
            return null;
        }
    }
}