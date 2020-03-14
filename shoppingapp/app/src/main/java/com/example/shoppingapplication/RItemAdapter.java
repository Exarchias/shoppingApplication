package com.example.shoppingapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

public class RItemAdapter extends ArrayAdapter<Item> {
    public RItemAdapter(@NonNull Context context, @NonNull ArrayList<Item> items) {
        super(context, 0, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Item item = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.ritem, parent, true);
        }
        // Lookup view for data population
        TextView textViewItemTitle = (TextView) convertView.findViewById(R.id.textViewItemTitle);
        TextView textViewItemDescription = (TextView) convertView.findViewById(R.id.textViewItemDescription);
        TextView textViewItemPrice = (TextView) convertView.findViewById(R.id.textViewItemPrice);
        //TextView tvHome = (TextView) convertView.findViewById(R.id.tvHome);
        // Populate the data into the template view using the data object
        textViewItemTitle.setText(item.getTitle());
        textViewItemDescription.setText(item.getDescription());
        textViewItemPrice.setText((int) item.getPrice());
        //tvName.setText(user.name);
        //tvHome.setText(user.hometown);
        // Return the completed view to render on screen
        return convertView;
    }
}
