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
import org.softwareenginnering.projecthoneybadger.TransactionListViewAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class manageTrnasactions extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_transactions);
        Intent intent = getIntent();

        this.Transaction = new ArrayList<>();
        this.TransactionListViewAdapter = new TransactionListViewAdapter(this, this.Transaction);
        this.getEventListView().setAdapter(this.TransactionListViewAdapter);
        this.getEventListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent viewEventIntent = new Intent(getApplicationContext(), ViewTransactions.class);
                Transaction selectedTransaction = (Transaction) getEventListView().getItemAtPosition(position);

                viewEventIntent.putExtra("Item", selectedTransaction.getItem());
                viewEventIntent.putExtra("Amount", selectedTransaction.getAmount());
                viewEventIntent.putExtra("Employee", selectedTransaction.getEmployee());
                viewEventIntent.putExtra("Date", selectedTransaction.getDate());
                startActivity(viewEventIntent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        (new RetrieveEventsTask()).execute();
    }


    private ListView getEventListView() {
        return (ListView) this.findViewById(R.id.transacListView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_manage_inventory, menu);
        return true;
    }

    private List<Transaction> Transaction;
    private TransactionListViewAdapter TransactionListViewAdapter;

    private class RetrieveEventsTask extends AsyncTask<Void, Void, List<Transaction>> {
        protected List<Transaction> doInBackground(Void... params) {
            return (new TransactionService()).getTransactions();
        }

        protected void onPostExecute(List<Transaction> results) {
            Transaction.clear();

            for (Transaction transaction : results) {
                Transaction.add(transaction);
            }

            TransactionListViewAdapter.notifyDataSetChanged();
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

    public void addTransaction(View view) {
        Intent addEventIntent = new Intent(this, addTransaction.class);
        startActivity(addEventIntent);
    }

    public void searchTransactions(View view) {
        EditText search = (EditText) findViewById(R.id.search_field);
        String searchingEvent = search.getText().toString();
        boolean nonExistingTransaction = true;
        for (Transaction temp : Transaction) {
            if (temp.getItem().equals(searchingEvent)) {
                Intent viewEventIntent = new Intent(getApplicationContext(), ViewTransactions.class);
                nonExistingTransaction = false;
                viewEventIntent.putExtra("Item", temp.getItem());
                viewEventIntent.putExtra("Amount", temp.getAmount());
                viewEventIntent.putExtra("Employee", temp.getEmployee());
                viewEventIntent.putExtra("Date", temp.getDate());
                startActivity(viewEventIntent);
            }
        }
        if (nonExistingTransaction == true) {
            Toast.makeText(getApplicationContext(), "This Transaction does not exist", Toast.LENGTH_LONG).show();
        }
    }
}
