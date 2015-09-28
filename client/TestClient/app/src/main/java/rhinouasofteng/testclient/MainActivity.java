package rhinouasofteng.testclient;

import android.app.Activity;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.net.*;
import java.io.*;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = (Button)findViewById(R.id.button);
        clickListener Listener = new clickListener();
        button.setOnClickListener(Listener);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
}

class clickListener implements View.OnClickListener {
    public void onClick(View v) {
        int portNumber = 5000;
        String hostName = "localhost";

        try (
                Socket sock = new Socket(hostName, portNumber);
                PrintWriter out = new PrintWriter(sock.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
                ) {

            String fromServer;
            String fromUser;
            //System.out.println("test");
            fromUser = "read ben";
            out.println(fromUser);
            while ((fromServer = in.readLine()) != null) {
                // something should happen here
                System.out.println(fromServer);
            }
        } catch (IOException e) {
            //something should happen here also but whatever
            System.out.println(e);
        }

        }
}
