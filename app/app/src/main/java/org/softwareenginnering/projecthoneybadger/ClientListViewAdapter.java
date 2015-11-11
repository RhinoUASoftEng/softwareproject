package org.softwareenginnering.projecthoneybadger;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class ClientListViewAdapter extends ArrayAdapter<Client> {
    public View getView(int position, View convertView, ViewGroup parent)
    {
        View view = convertView;
        if(view == null)
        {
            LayoutInflater inflater = LayoutInflater.from(this.getContext());
            view = inflater.inflate(R.layout.activity_view_my_client, parent, false);
        }

        Client client = getItem(position);

        if(client != null)
        {
            TextView textView = (TextView) view.findViewById(R.id.textView);
            if(textView != null)
            {
                textView.setText("Name: " + client.getClientName() + ' ' +  "email: " + client.getEmail() + ' ' + "phone number: " + client.getPhoneNumber());
            }
        }

        return view;
    }

    public ClientListViewAdapter(Context context, List<Client> ClientList)
    {
        super(context,R.layout.activity_view_my_client, ClientList);
    }
}
