package org.softwareenginnering.projecthoneybadger;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class ViewTransactions extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_transactions);

        Bundle extras = getIntent().getExtras();
        TextView textView = (TextView) findViewById(R.id.textView);
        TextView textView1 = (TextView) findViewById(R.id.textView2);
        TextView textView2 = (TextView) findViewById(R.id.textView3);
        TextView textView3 = (TextView) findViewById(R.id.textView4);
        if(extras != null)
        {
            textView.setText("Item purchased: " + extras.getString("Item"));
            textView1.setText("Employee on Staff: " + extras.getString("Employee"));
            textView2.setText("For: $" + extras.getString("Amount"));
            textView3.setText("On: " + extras.getString("Date"));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_view_transactions, menu);
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

    public void returnManageTransaction(View view)
    {
        Intent intent = new Intent(this, manageTrnasactions.class);
        startActivity(intent);
    }
}
