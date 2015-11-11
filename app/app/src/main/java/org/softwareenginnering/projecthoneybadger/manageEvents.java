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
import android.widget.ListView;
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
                Intent viewEventIntent = new Intent(getApplicationContext(), viewEvent.class);
                Event selectedEvent = (Event) getEventListView().getItemAtPosition(position);

                viewEventIntent.putExtra("Name", selectedEvent.getName());
                viewEventIntent.putExtra("Address", selectedEvent.getAddress());
                viewEventIntent.putExtra("Time", selectedEvent.getTime());
                viewEventIntent.putExtra("Date", selectedEvent.getDate());
                startActivity(viewEventIntent);
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
            return (new EventService()).getEvents();
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
                Intent viewEventIntent = new Intent(getApplicationContext(), viewEvent.class);
                nonExistingEvent = false;
                viewEventIntent.putExtra("Name", temp.getName());
                viewEventIntent.putExtra("Address", temp.getAddress());
                viewEventIntent.putExtra("Time", temp.getTime());
                viewEventIntent.putExtra("Date", temp.getDate());
                startActivity(viewEventIntent);
            }
        }
        if(nonExistingEvent == true)
        {
            Toast.makeText(getApplicationContext(), "This Event does not exist", Toast.LENGTH_LONG).show();
        }
    }


}
