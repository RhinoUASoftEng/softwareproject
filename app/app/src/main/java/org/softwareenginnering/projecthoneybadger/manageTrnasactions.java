package org.softwareenginnering.projecthoneybadger;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.ArrayList;

public class manageTrnasactions extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_transactions);
        ScrollView manageTransactions = (ScrollView) findViewById(R.id.scrollView);
        addViewsToScrollView(manageTransactions);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_manage_trnasactions, menu);
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

    private void addViewsToScrollView(ScrollView scrollV) {
        ArrayList<TextView> textViewsToAdd = makeStringsIntoViews(getList());
        LinearLayout layoutHoldingTextViews = new LinearLayout(this);
        layoutHoldingTextViews.setOrientation(LinearLayout.VERTICAL);
        for (int i = 0; i < textViewsToAdd.size(); i++)
            layoutHoldingTextViews.addView(textViewsToAdd.get(i));
        scrollV.addView(layoutHoldingTextViews);
    }

    private ArrayList<String> getList() {
        String temp = "I am a test";
        String temp2 = "I too am a test";
        ArrayList<String> result = new ArrayList<String>();
        result.add(temp);
        result.add(temp2);
        return result;
    }

    private ArrayList<TextView> makeStringsIntoViews(ArrayList<String> input) {
        ArrayList<TextView> result = new ArrayList<TextView>();
        for (int i = 0; i < input.size(); i++) {
            TextView temp = new TextView(this);
            temp.setText(input.get(i));
            result.add(temp);
        }
        return result;
    }

    public void backMainMenu(View view)
    {
        Intent mainMenuIntent = new Intent(this, MainMenu.class);
        startActivity(mainMenuIntent);
    }
}
