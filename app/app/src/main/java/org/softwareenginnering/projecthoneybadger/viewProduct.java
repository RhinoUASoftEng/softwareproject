package org.softwareenginnering.projecthoneybadger;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import org.softwareenginnering.projecthoneybadger.inventory;
import java.util.UUID;
public class viewProduct extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_product);

        Bundle extras = getIntent().getExtras();

        if(extras != null)
        {
            this.inventory = extras.getString("Inventory") + ' ' + extras.getString("Quantity") + ' ' + extras.getString("Cost") + ' ' + extras.getString("Reorder Limit");
        }

        TextView textView = (TextView) findViewById(R.id.textView);

        textView.setText(inventory);
    }
    private String inventory;
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_view_product, menu);
        return true;
    }

    private UUID inventoryid;

    protected void onResume() {
        super.onResume();

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

    private TextView postinventoryOnPage()
    {
        return (TextView) this.findViewById(R.id.textView);
    }


    public void returnManageInventory(View view)
    {
        Intent manageInventoryintent = new Intent(this, manageInventory.class);
        startActivity(manageInventoryintent);
    }
}
