package org.softwareenginnering.projecthoneybadger;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;
import java.util.UUID;

public class addEvents extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_events);
        Intent intent = getIntent();
        Toast.makeText(getApplicationContext(), "Work in Progress!", Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_events, menu);
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

    public void backManageEvent(View view)
    {
        Intent manageEventIntent = new Intent(this,manageEvents.class);
        startActivity(manageEventIntent);
    }

    public void addEvents(View view)
    {
        EditText name = (EditText) findViewById(R.id.name);
        EditText address = (EditText) findViewById(R.id.address);
        Button timeButton = (Button) findViewById(R.id.setTime);
        Button dateButton = (Button) findViewById(R.id.setDate);
        Event newEvent = new Event();

        String dateText = dateButton.getText().toString();
        String timeText = timeButton.getText().toString();
        String newAddress = address.getText().toString();
        String newName = name.getText().toString();

        newEvent.setId(UUID.randomUUID());
        newEvent.setAddress(newAddress);
        newEvent.setName(newName);
        newEvent.setDate(dateText);
        newEvent.setTime(timeText);

    }

    public void setTime(View view)
    {
        Toast.makeText(getApplicationContext(), "Work in Progress!", Toast.LENGTH_LONG).show();
        Button timeButton = (Button) findViewById(R.id.setTime);
        DialogFragment newFragment = new TimePickerFragment();
        newFragment.show(getFragmentManager(), "TimePicker");

    }

    public void setDate(View view)
    {
        Toast.makeText(getApplicationContext(), "Work in Progress!", Toast.LENGTH_LONG).show();
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getFragmentManager(), "DatePicker");
    }

    public static class TimePickerFragment extends DialogFragment
            implements TimePickerDialog.OnTimeSetListener {
        String time = "";
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current time as the default values for the picker
            final Calendar c = Calendar.getInstance();
            int hour = c.get(Calendar.HOUR_OF_DAY);
            int minute = c.get(Calendar.MINUTE);

            // Create a new instance of TimePickerDialog and return it
            return new TimePickerDialog(getActivity(), this, hour, minute,
                    DateFormat.is24HourFormat(getActivity()));
        }

        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            String integerTime = Integer.toString(hourOfDay) + ':' + Integer.toString(minute);
            this.time = integerTime;
        }
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
