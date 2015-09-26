package org.SoftwareProject.test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class CreateNewUser extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_user);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_create_new_user, menu);
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

    public void CreateNewAccount(View view)
    {
        EditText username = (EditText) findViewById(R.id.username);
        EditText email = (EditText) findViewById(R.id.email);
        EditText password = (EditText) findViewById(R.id.password);
        EditText confirmpassword = (EditText) findViewById(R.id.confirmpassword);
        String newUserName = username.getText().toString();
        String userEmailAddress = email.getText().toString();
        String userPassword = password.getText().toString();
        String passwordSecurity = confirmpassword.getText().toString();

        if(userPassword.equals(passwordSecurity))
        {

        }

        else
        {
            Toast.makeText(getApplicationContext(), "passwords do not match", Toast.LENGTH_SHORT).show();
            username.setVisibility(View.VISIBLE);
            email.setVisibility(View.VISIBLE);
            password.setVisibility(View.VISIBLE);
            confirmpassword.setVisibility(View.VISIBLE);
        }
    }
}
