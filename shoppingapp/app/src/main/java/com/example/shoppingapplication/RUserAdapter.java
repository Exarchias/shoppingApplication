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

public class RUserAdapter extends ArrayAdapter<User> {
    String name = "John Doe";
    String telephone = "+461234567890";
    String email = "example@example.com";
    String id = "5000";
    Context context;
    ArrayList<User> users;
    public RUserAdapter(@NonNull Context context, @NonNull ArrayList<User> users) {
        super(context, 0, users);
        this.context = context;
        this.users = users;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data User for this position
        User user = getItem(position); //Beware it should be getItem. it has nothing to do with Item class.
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.ruser, parent, false);
        }
        // Lookup view for data population
        TextView textViewUserName = (TextView) convertView.findViewById(R.id.textViewUserName);
        TextView textViewUserTelephone = (TextView) convertView.findViewById(R.id.textViewUserTelephone);
        TextView textViewUserDescription = (TextView) convertView.findViewById(R.id.textViewUserEmail);
        TextView textViewUserPrice = (TextView) convertView.findViewById(R.id.textViewUserId);
        //TextView tvHome = (TextView) convertView.findViewById(R.id.tvHome);
        // Populate the data into the template view using the data object
        assert user != null; //Not sure how necessary it is.
        if(user != null){
            name = user.getName();
            telephone = user.getTelephone();
            email = user.getEmail();
            id = String.valueOf(user.getId());
        }
        textViewUserName.setText(name);
        textViewUserTelephone.setText(telephone);
        textViewUserDescription.setText(email);
        textViewUserPrice.setText(id);
        //tvName.setText(user.name);
        //tvHome.setText(user.hometown);
        // Return the completed view to render on screen
        return convertView;
    }
}
