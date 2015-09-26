package org.SoftwareProject.test;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.lang.Object;
import java.net.Socket;
import java.lang.String;
import java.net.UnknownHostException;

public class MyActivity extends AppCompatActivity {

    String addr = "52.89.113.105";
    int prt = 5000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_my, menu);
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

    public void sendToServer(View view) {
        EditText data = (EditText) findViewById(R.id.test);
        TextView textIn = (TextView) findViewById(R.id.textin);
        Socket socket = null;
        DataInputStream fromServer = null;
        DataOutputStream toServer = null;
        try {
            socket = new Socket(addr, prt);
            fromServer = new DataInputStream(socket.getInputStream());
            toServer = new DataOutputStream(socket.getOutputStream());
            toServer.writeUTF(data.getText().toString());
            textIn.setText(fromServer.readUTF());
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if (socket != null) {
                try {
                    socket.close();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            if(toServer != null)
            {
                try {
                    toServer.close();
                }
                catch(IOException e) {
                 e.printStackTrace();
            }
            }
                if(fromServer != null)
                {
                    try {
                        fromServer.close();
                    }
                    catch(IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }


    public void loginPage(View view)
    {
        Intent intent = new Intent(this, LoginPage.class);
        startActivity(intent);
    }

    public void createUserPage(View view)
    {
        Intent createUserIntent = new Intent(this, CreateNewUser.class);
        startActivity(createUserIntent);
    }
}


