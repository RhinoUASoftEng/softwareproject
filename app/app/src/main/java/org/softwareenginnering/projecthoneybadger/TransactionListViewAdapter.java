package org.softwareenginnering.projecthoneybadger;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;


public class TransactionListViewAdapter extends ArrayAdapter<Transaction>{
    public View getView(int position, View convertView, ViewGroup parent)
    {
        View view = convertView;
        if(view == null)
        {
            LayoutInflater inflater = LayoutInflater.from(this.getContext());
            view = inflater.inflate(R.layout.activity_view_my_transactions, parent, false);
        }

        Transaction transaction = getItem(position);

        if(transaction != null)
        {
            TextView textView = (TextView) view.findViewById(R.id.textView);
            if(textView != null) {
                textView.setText("Item: " + transaction.getItem() + ' ' + "employee: " + transaction.getEmployee() + ' ' + "Amount: $" + transaction.getAmount());
            }
        }

        return view;
    }

    public TransactionListViewAdapter(Context context, List<Transaction> transactionList)
    {
        super(context,R.layout.activity_view_my_transactions, transactionList);
    }
}
