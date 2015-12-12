package org.softwareenginnering.projecthoneybadger;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.UUID;

public class addClient extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_client);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_client, menu);
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

    public void submitClient(View view)
    {
        ClientService clientService = new ClientService();
        clientService.getClients();
        EditText name = (EditText) findViewById(R.id.name);
        EditText email = (EditText) findViewById(R.id.Email);
        EditText phone = (EditText) findViewById(R.id.phone);
        EditText address = (EditText) findViewById(R.id.address);
        String clientName = name.getText().toString();
        String clientEmail = email.getText().toString();
        String clientPhone = phone.getText().toString();
        String clientAddress = address.getText().toString();


        Client newClient = new Client();
        newClient.setId(UUID.randomUUID());
        newClient.setClientName(clientName);
        newClient.setAddress(clientAddress);
        newClient.setEmail(clientEmail);
        newClient.setPhoneNumber(clientPhone);

        //clientService.setClients(newClient);

        AddToServer temp = new AddToServer();
        temp.sendDataAsync(newClient);
        temp.execute();

        Intent manageClientintent = new Intent(this, manageClients.class);
        startActivity(manageClientintent);
    }

    public void backToManageClient(View view)
    {
        Intent manageClientintent = new Intent(this, manageClients.class);
        startActivity(manageClientintent);
    }

    private class AddToServer extends AsyncTask<Void, Void, Void> {
        Client obj;

        public void sendDataAsync(Client addThis){
            this.obj = addThis;
        }
        protected Void doInBackground(Void... v) {
            (new ClientService()).create(obj);
            return null;
        }
    }
}
