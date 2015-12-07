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
                final int pos = position;
                Transaction selectedClient = (Transaction) getEventListView().getItemAtPosition(position);
                TextView textView = new TextView(manageTrnasactions.this);
                textView.setTextColor(Color.parseColor("#7A0101"));
                textView.setTypeface(Typeface.SANS_SERIF);
                textView.setText("Item: " + selectedClient.getItem() + "\n" + "Amount $: " + selectedClient.getAmount()
                        + "\n" + "Employee: " + selectedClient.getEmployee() + "\n" + "Date of Transaction: " + selectedClient.getDate() + "\n");

                AlertDialog.Builder builder = new AlertDialog.Builder(manageTrnasactions.this);
                builder.setView(textView);
                builder.setTitle(selectedClient.getItem());
                builder.setPositiveButton(R.string.editTr, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Intent viewProductIntent = new Intent(manageTrnasactions.this, EditTransaction.class);
                        Transaction selectedClient = (Transaction) getEventListView().getItemAtPosition(pos);
                        viewProductIntent.putExtra("UUID", selectedClient.getId());
                        viewProductIntent.putExtra("Item", selectedClient.getItem());
                        viewProductIntent.putExtra("Amount", selectedClient.getAmount());
                        viewProductIntent.putExtra("Employee", selectedClient.getEmployee());
                        viewProductIntent.putExtra("Date", selectedClient.getDate());
                        startActivity(viewProductIntent);
                    }
                });

                builder.setNegativeButton(R.string.delete, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //TODO setup HTTPRequest DELETE to server
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
            return (new TransactionService()).getAll();
            //return (new TransactionService()).getTransactions();
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
                final Transaction transaction = temp;
                nonExistingTransaction = false;
                TextView textView = new TextView(manageTrnasactions.this);
                textView.setTextColor(Color.parseColor("#7A0101"));
                textView.setTypeface(Typeface.SANS_SERIF);
                textView.setText("Item: " + transaction.getItem() + "\n" + "Amount $: " + transaction.getAmount()
                        + "\n" + "Employee: " + transaction.getEmployee() + "\n" + "Date of Transaction: " + transaction.getDate() + "\n");

                AlertDialog.Builder builder = new AlertDialog.Builder(manageTrnasactions.this);
                builder.setView(textView);
                builder.setTitle(transaction.getItem());
                builder.setPositiveButton(R.string.editTr, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Intent viewProductIntent = new Intent(manageTrnasactions.this, EditTransaction.class);
                        viewProductIntent.putExtra("UUID", transaction.getId());
                        viewProductIntent.putExtra("Item", transaction.getItem());
                        viewProductIntent.putExtra("Amount", transaction.getAmount());
                        viewProductIntent.putExtra("Employee", transaction.getEmployee());
                        viewProductIntent.putExtra("Date", transaction.getDate());
                        startActivity(viewProductIntent);
                    }
                });

                builder.setNegativeButton(R.string.delete, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //TODO setup HTTPRequest DELETE to server
                    }
                });
                AlertDialog display = builder.create();
                display.show();
            }
        }
        if (nonExistingTransaction == true) {
            Toast.makeText(getApplicationContext(), "This Transaction does not exist", Toast.LENGTH_LONG).show();
        }
    }
}
