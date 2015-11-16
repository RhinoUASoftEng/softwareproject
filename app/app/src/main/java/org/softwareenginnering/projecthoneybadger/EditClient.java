package org.softwareenginnering.projecthoneybadger;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class EditClient extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_client);
        Toast.makeText(getApplicationContext(), "Work in Progress!", Toast.LENGTH_LONG).show();
        Bundle extras = getIntent().getExtras();
        clientID = (UUID) extras.getSerializable("UUID");
        EditText editText = (EditText) findViewById(R.id.name);
        EditText editText1 = (EditText) findViewById(R.id.address);
        EditText editText2 = (EditText) findViewById(R.id.Email);
        EditText editText3 = (EditText) findViewById(R.id.phone);
        if(extras != null)
        {
            editText.setText(extras.getString("Name"));
            editText1.setText(extras.getString("Address"));
            editText2.setText(extras.getString("Email"));
            editText3.setText(extras.getString("Phone"));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_client, menu);
        return true;
    }
    private UUID clientID;
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
        List<Client> clientList = new ArrayList<>();
        ClientService clientService = new ClientService();
        clientList = clientService.getClients();
        EditText name = (EditText) findViewById(R.id.name);
        EditText email = (EditText) findViewById(R.id.Email);
        EditText phone = (EditText) findViewById(R.id.phone);
        EditText address = (EditText) findViewById(R.id.address);
        String clientName = name.getText().toString();
        String clientEmail = email.getText().toString();
        String clientPhone = phone.getText().toString();
        String clientAddress = address.getText().toString();

        for(Client client : clientList) {
            if(clientID.equals(client.getId())) {
                client.setClientName(clientName);
                client.setAddress(clientAddress);
                client.setEmail(clientEmail);
                client.setPhoneNumber(clientPhone);

                clientService.setClients(client);
                break;
            }
        }

        Intent manageClientintent = new Intent(this, manageClients.class);
        startActivity(manageClientintent);
    }

    public void backToManageClient(View view)
    {
        Intent manageClientintent = new Intent(this, manageClients.class);
        startActivity(manageClientintent);
    }
}
