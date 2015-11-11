package org.softwareenginnering.projecthoneybadger;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class viewInventory extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_inventory);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_view_inventory, menu);
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

    public void updateItems(View view)
    {
        Toast.makeText(getApplicationContext(), "Work in Progress!", Toast.LENGTH_LONG).show();
       /* AlertDialog.Builder builder = new AlertDialog.Builder(viewInventory.this);
        LayoutInflater layoutInflater = viewInventory.this.getLayoutInflater();
        builder.setView(layoutInflater.inflate(R.layout.activity_update_items, null));
        builder.setTitle(R.string.editProduct);
        builder.setNeutralButton(R.string.cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                //cancel(true);
            }
        });
        builder.setPositiveButton(R.string.editProduct, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                //cancel(true);
            }
        });

        builder.setNegativeButton(R.string.delete, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id)
            {
                //cancel(true);
            }
        });
        AlertDialog display = builder.create();
        display.show();*/
    }

}
