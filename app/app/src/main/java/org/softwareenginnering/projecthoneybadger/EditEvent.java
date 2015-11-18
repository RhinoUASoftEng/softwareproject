package org.softwareenginnering.projecthoneybadger;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
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

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

public class EditEvent extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_event);
        Bundle extras = getIntent().getExtras();
        eventid = (UUID) extras.getSerializable("UUID");
        EditText editText = (EditText) findViewById(R.id.name);
        EditText editText1 = (EditText) findViewById(R.id.Employee);
        EditText editText2 = (EditText) findViewById(R.id.address);
        Button button1 = (Button) findViewById(R.id.setTime);
        Button button2 = (Button) findViewById(R.id.setDate);
        if(extras != null)
        {
            editText.setText(extras.getString("Name"));
            editText1.setText(extras.getString("Employee"));
            editText2.setText(extras.getString("Address"));
            button1.setText(extras.getString("Time"));
            button2.setText(extras.getString("Date"));
        }

        //Dr. P - begin (Default initialization for the Activity's private properties. Help to prove that we actually are setting the values.)
        this.hour = -1;
        this.minute = -1;
        //Dr. P - end
        this.year = -1;
        this.month = -1;
        this.day = -1;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_edit_event, menu);
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
    private UUID eventid;

    public void backManageEvent(View view)
    {
        Intent manageEventIntent = new Intent(this,manageEvents.class);
        startActivity(manageEventIntent);
    }

    public void addEvents(View view)
    {
        List<Event> existingEvent = new ArrayList<>();
        EventService editEvent = new EventService();
        existingEvent = editEvent.getEvents();
        EditText name = (EditText) findViewById(R.id.name);
        EditText address = (EditText) findViewById(R.id.address);
        EditText employee = (EditText) findViewById(R.id.Employee);
        Button timeButton = (Button) findViewById(R.id.setTime);
        Button dateButton = (Button) findViewById(R.id.setDate);
        Event newEvent = new Event();

        String dateText = dateButton.getText().toString();
        String timeText = timeButton.getText().toString();
        String newAddress = address.getText().toString();
        String newName = name.getText().toString();
        String employeeAssigned = employee.getText().toString();
        for(Event events : existingEvent) {
            if(eventid.equals(events.getId())) {
                newEvent.setAddress(newAddress);
                newEvent.setName(newName);
                newEvent.setDate(dateText);
                newEvent.setTime(timeText);
                newEvent.setEmployee(employeeAssigned);
                break;
            }
        }

        Intent intent = new Intent(this, manageEvents.class);
        startActivity(intent);
    }

    public void setTime(View view)
    {
        //Dr. P - begin (Commented out your code and used a TimePickerDialog with our event listener)
//        Toast.makeText(getApplicationContext(), "Work in Progress!", Toast.LENGTH_LONG).show();
//        Button timeButton = (Button) findViewById(R.id.setTime);
//        DialogFragment newFragment = new TimePickerFragment();
//        newFragment.show(getFragmentManager(), "TimePicker");
        Calendar c = Calendar.getInstance();
        (new TimePickerDialog(this, timePickerListener, c.get(Calendar.HOUR_OF_DAY), c.get(Calendar.MINUTE), false)).show();
        //Dr. P - end
    }

    public void setDate(View view)
    {
        //Toast.makeText(getApplicationContext(), "Work in Progress!", Toast.LENGTH_LONG).show();
        //DialogFragment newFragment = new DatePickerFragment();
        //newFragment.show(getFragmentManager(), "DatePicker");

        Calendar getDate = Calendar.getInstance();
        (new DatePickerDialog(this,datePickerListener, getDate.get(Calendar.YEAR),getDate.get(Calendar.MONTH),getDate.get(Calendar.DAY_OF_MONTH))).show();
    }

    //Dr. P - begin (Declare our Activity's private properties and the TimePickerDialog event listener)
    private int hour;
    private int minute;
    private int year;
    private int month;
    private int day;
    private int theme;

    private TimePickerDialog.OnTimeSetListener timePickerListener = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int selectedHour, int selectedMinute) {
            hour = selectedHour;
            minute = selectedMinute;
            Button timeButtonText = (Button) findViewById(R.id.setTime);
            timeButtonText.setText(Integer.toString(hour) + ":" + Integer.toString(minute));
        }
    };
    //Dr. P - end
    private DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int selectedyear, int selectedMonth, int selectedDay)
        {
            year = selectedyear;
            month = selectedMonth;
            day = selectedDay;
            Button getDate = (Button) findViewById(R.id.setDate);
            getDate.setText(Integer.toString(day) + "-" + Integer.toString(month) + "-" + Integer.toString(year));
        }
    };

}
