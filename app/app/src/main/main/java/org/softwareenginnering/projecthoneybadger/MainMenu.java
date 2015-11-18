package org.softwareenginnering.projecthoneybadger;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        Intent intent = getIntent();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_menu, menu);
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

    public void manageInventory(View view)
    {
        Intent inventoryIntent = new Intent(this, manageInventory.class);
        startActivity(inventoryIntent);
    }

    public void manageEvents(View view)
    {
        Intent eventIntent = new Intent(this, manageEvents.class);
        startActivity(eventIntent);
    }

    public void manageTransactions(View view)
    {
        Intent transactionsIntent = new Intent(this, manageTrnasactions.class);
        startActivity(transactionsIntent);
    }

    public void manageClients(View view)
    {
        Intent clientsIntent = new Intent(this, manageClients.class);
        startActivity(clientsIntent);
    }

}
