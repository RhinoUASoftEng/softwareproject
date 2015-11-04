package org.softwareenginnering.projecthoneybadger;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class eventListViewAdapter extends ArrayAdapter<Event> {
    public View getView(int position, View convertView, ViewGroup parent)
    {
        View view = convertView;
        if(view == null)
        {
            LayoutInflater inflater = LayoutInflater.from(this.getContext());
            view = inflater.inflate(R.layout.activity_view_my_event, parent, false);
        }

        Event event = getItem(position);

        if(event != null)
        {
            TextView textView = (TextView) view.findViewById(R.id.textView);
            if(textView != null)
            {
                textView.setText(event.getProductItem());
            }
        }

        return view;
    }

    public eventListViewAdapter(Context context, List<Event> EventList)
    {
        super(context,R.layout.activity_view_my_event, EventList);
    }
}

