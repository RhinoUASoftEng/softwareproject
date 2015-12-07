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
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class manageEvents extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_event);
        Intent intent = getIntent();

        this.Events = new ArrayList<>();
        this.eventListViewAdapter = new eventListViewAdapter(this,this.Events);
        this.getEventListView().setAdapter(this.eventListViewAdapter);
        this.getEventListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final int pos = position;
                Event selectedEvent = (Event) getEventListView().getItemAtPosition(position);
                TextView textView = new TextView(manageEvents.this);
                textView.setTextColor(Color.parseColor("#7A0101"));
                textView.setTypeface(Typeface.SANS_SERIF);
                textView.setText("Event: " + selectedEvent.getName() + "\n" + "Address: " + selectedEvent.getAddress()
                        + "\n" + "Time: " + selectedEvent.getTime() + "\n" + "Date: " + selectedEvent.getDate() + "\n"
                + "Employee: " + selectedEvent.getEmployee() + "\n");

                AlertDialog.Builder builder = new AlertDialog.Builder(manageEvents.this);
                builder.setView(textView);
                builder.setTitle(selectedEvent.getName());
                builder.setPositiveButton(R.string.editEv, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Intent viewProductIntent = new Intent(manageEvents.this, EditEvent.class);
                        Event selectedEvent = (Event) getEventListView().getItemAtPosition(pos);
                        viewProductIntent.putExtra("UUID", selectedEvent.getId());
                        viewProductIntent.putExtra("Name", selectedEvent.getName());
                        viewProductIntent.putExtra("Address", selectedEvent.getAddress());
                        viewProductIntent.putExtra("Employee", selectedEvent.getEmployee());
                        viewProductIntent.putExtra("Time",selectedEvent.getTime());
                        viewProductIntent.putExtra("Date",selectedEvent.getDate());
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
    protected void onResume()
    {
        super.onResume();
        (new RetrieveEventsTask()).execute();
    }


    private ListView getEventListView()
    {
        return (ListView) this.findViewById(R.id.eventListView);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_manage_inventory, menu);
        return true;
    }

    private List<Event> Events;
    private eventListViewAdapter eventListViewAdapter;

    private class RetrieveEventsTask extends AsyncTask<Void, Void, List<Event>> {
        protected List<Event> doInBackground(Void ... params) {
            return (new EventService()).getAll();
            //return (new EventService()).getEvents();
        }

        protected void onPostExecute(List<Event> results)
        {
            Events.clear();

            for(Event Eventees : results)
            {
                Events.add(Eventees);
            }

            eventListViewAdapter.notifyDataSetChanged();
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

    public void backMainMenu(View view)
    {
        Intent mainMenuIntent = new Intent(this, MainMenu.class);
        startActivity(mainMenuIntent);
    }

    public void addEvent(View view)
    {
        Intent addEventIntent = new Intent(this,addEvents.class);
        startActivity(addEventIntent);
    }

    public void searchEvents(View view)
    {
        EditText search = (EditText) findViewById(R.id.search_field);
        String searchingEvent = search.getText().toString();
        boolean nonExistingEvent = true;
        for(Event temp : Events)
        {
            if(temp.getName().equals(searchingEvent))
            {
                final Event event = temp;
                Intent viewEventIntent = new Intent(getApplicationContext(), viewEvent.class);
                nonExistingEvent = false;
                AlertDialog.Builder builder = new AlertDialog.Builder(manageEvents.this);
                TextView textView = new TextView(manageEvents.this);
                textView.setTextColor(Color.parseColor("#7A0101"));
                textView.setTypeface(Typeface.SANS_SERIF);
                textView.setText("Event: " + event.getName() + "\n" + "Address: " + event.getAddress()
                        + "\n" + "Time: " + event.getTime() + "\n" + "Date: " + event.getDate() + "\n"
                        + "Employee: " + event.getEmployee() + "\n");
                builder.setView(textView);
                builder.setTitle(event.getName());
                builder.setPositiveButton(R.string.editProduct, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Intent viewProductIntent = new Intent(manageEvents.this, EditEvent.class);
                        viewProductIntent.putExtra("UUID", event.getId());
                        viewProductIntent.putExtra("Name", event.getName());
                        viewProductIntent.putExtra("Address", event.getAddress());
                        viewProductIntent.putExtra("Employee", event.getEmployee());
                        viewProductIntent.putExtra("Time", event.getTime());
                        viewProductIntent.putExtra("Date", event.getDate());
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


        if(nonExistingEvent == true)
        {
            Toast.makeText(getApplicationContext(), "This Event does not exist", Toast.LENGTH_LONG).show();
        }
    }


}
