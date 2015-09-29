package rhinouasofteng.testclient;

import android.app.Activity;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.net.*;
import java.io.*;

public class MainActivity extends Activity
{
    EditText email;
    TextView response;
    Button add;
    Button find;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        email = (EditText)findViewById(R.id.editText);
        email.setHint("user@email.com");
        response = (TextView)findViewById(R.id.textView);
        response.setGravity(Gravity.CENTER);
        add = (Button)findViewById(R.id.button1);
        add.setOnClickListener(new connectListener("write ", email, response));
        find = (Button)findViewById(R.id.button2);
        find.setOnClickListener(new connectListener("read ", email, response));

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings)
            return true;

        return super.onOptionsItemSelected(item);
    }
}

class connectListener implements View.OnClickListener
{
    EditText email;
    TextView response;
    String instruction;

    public connectListener(String instruction, EditText email, TextView response)
    {
        this.instruction = instruction;
        this.email = email;
        this.response = response;
    }

    public void onClick(View v)
    {
        int portNumber = 5000;
        String hostName = "52.89.113.105";

        try (
                Socket sock = new Socket(hostName, portNumber);
                PrintWriter out = new PrintWriter(sock.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
            )
        {
            out.println(instruction + email.getText());
            response.setText(in.readLine());
        } catch (IOException e)
        {
            System.out.println(e);
        }
    }
}
