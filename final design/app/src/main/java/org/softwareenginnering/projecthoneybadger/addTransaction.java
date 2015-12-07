package org.softwareenginnering.projecthoneybadger;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;
import java.util.UUID;

public class addTransaction extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_transaction);
        Intent intent = getIntent();
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

    public void submitTransaction(View view)
    {
        Calendar currentDate = Calendar.getInstance();
        TransactionService TransactionService = new TransactionService();
        TransactionService.getTransactions();
        EditText item = (EditText) findViewById(R.id.Item);
        EditText amount = (EditText) findViewById(R.id.Amount);
        EditText employee = (EditText) findViewById(R.id.Employee);
        String product = item.getText().toString();
        String amountPurchased = amount.getText().toString();
        String employeeOnDuty = employee.getText().toString();

        Transaction newTransaction = new Transaction();
        newTransaction.setId(UUID.randomUUID());
        newTransaction.setItem(product);
        newTransaction.setAmount(amountPurchased);
        newTransaction.setEmployee(employeeOnDuty);
        newTransaction.setDate(currentDate.toString());
       TransactionService.setTransaction(newTransaction);

        Intent manageTransactionintent = new Intent(this, manageTrnasactions.class);
        startActivity(manageTransactionintent);
    }


    public void backToManageTransaction(View view)
    {
        Intent manageTransactionintent = new Intent(this, manageTrnasactions.class);
        startActivity(manageTransactionintent);
    }

}
