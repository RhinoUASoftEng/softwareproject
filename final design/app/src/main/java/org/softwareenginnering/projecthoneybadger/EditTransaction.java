package org.softwareenginnering.projecthoneybadger;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

public class EditTransaction extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_transaction);
        Bundle extras = getIntent().getExtras();
        transactionID = (UUID) extras.getSerializable("UUID");
        EditText editText = (EditText) findViewById(R.id.Item);
        EditText editText1 = (EditText) findViewById(R.id.Amount);
        EditText editText2 = (EditText) findViewById(R.id.Employee);
        TextView textView = (TextView) findViewById(R.id.TitleTR);
        if(extras != null)
        {
            textView.setText(extras.getString("Employee"));
            editText.setText(extras.getString("Item"));
            editText1.setText(extras.getString("Amount"));
            editText2.setText(extras.getString("Employee"));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_transaction, menu);
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
    UUID transactionID;
    public void submitTransaction(View view)
    {
        Calendar currentDate = Calendar.getInstance();
        List<Transaction> transactionList = new ArrayList<>();
        TransactionService TransactionService = new TransactionService();
        transactionList = TransactionService.getTransactions();
        EditText item = (EditText) findViewById(R.id.Item);
        EditText amount = (EditText) findViewById(R.id.Amount);
        EditText employee = (EditText) findViewById(R.id.Employee);
        String product = item.getText().toString();
        String amountPurchased = amount.getText().toString();
        String employeeOnDuty = employee.getText().toString();
        for(Transaction transaction : transactionList) {
            if(transactionID.equals(transaction.getId())) {
                transaction.setItem(product);
                transaction.setAmount(amountPurchased);
                transaction.setEmployee(employeeOnDuty);
                transaction.setDate(currentDate.toString());
                TransactionService.setTransaction(transaction);
                break;

            }
        }

        Intent manageTransactionintent = new Intent(this, manageTrnasactions.class);
        startActivity(manageTransactionintent);
    }


    public void backToManageTransaction(View view)
    {
        Intent manageTransactionintent = new Intent(this, manageTrnasactions.class);
        startActivity(manageTransactionintent);
    }
}
