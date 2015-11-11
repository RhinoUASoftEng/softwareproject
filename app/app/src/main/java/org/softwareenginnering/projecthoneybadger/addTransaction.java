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
        Toast.makeText(getApplicationContext(), "Work in Progress!", Toast.LENGTH_LONG).show();
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

       TransactionService.setTransaction(newTransaction);

        Intent manageTransactionintent = new Intent(this, manageTrnasactions.class);
        startActivity(manageTransactionintent);
    }

    public void setDate(View view)
    {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getFragmentManager(), "DatePicker");
    }

    public void backToManageTransaction(View view)
    {
        Intent manageTransactionintent = new Intent(this, manageTrnasactions.class);
        startActivity(manageTransactionintent);
    }

    public static class DatePickerFragment extends DialogFragment
            implements DatePickerDialog.OnDateSetListener {
        String date = "";
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current date as the default date in the picker
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            // Create a new instance of DatePickerDialog and return it
            return new DatePickerDialog(getActivity(), this, year, month, day);
        }
        public void onDateSet(DatePicker view, int year, int month, int day) {
            String integerDate = Integer.toString(year) + ' ' + Integer.toString(month) + ' ' + Integer.toString(day);

            this.date = integerDate;
        }
    }
}
