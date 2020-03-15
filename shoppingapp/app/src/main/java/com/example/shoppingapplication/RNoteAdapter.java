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

public class RNoteAdapter extends ArrayAdapter<Note> {
    String title = "no title";
    String description = "no description";
    String price = "0.0";
    Context context;
    ArrayList<Note> notes;
    public RNoteAdapter(@NonNull Context context, @NonNull ArrayList<Note> notes) {
        super(context, 0, notes);
        this.context = context;
        this.notes = notes;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Note note = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.rnote, parent, false);
        }
        // Lookup view for data population
        TextView textViewNoteTitle = (TextView) convertView.findViewById(R.id.textViewNoteTitle);
        TextView textViewNoteDescription = (TextView) convertView.findViewById(R.id.textViewNoteDescription);
        //TextView textViewItemPrice = (TextView) convertView.findViewById(R.id.textViewNotePrice);
        //TextView tvHome = (TextView) convertView.findViewById(R.id.tvHome);
        // Populate the data into the template view using the data object
        assert note != null; //Not sure how necessary it is.
        if(note != null){
            title = note.getTitle();
            description = note.getDescription();
            //price = String.valueOf(note.get());
        }
        textViewNoteTitle.setText(title);
        textViewNoteDescription.setText(description);
        //textViewItemPrice.setText(price);
        //tvName.setText(user.name);
        //tvHome.setText(user.hometown);
        // Return the completed view to render on screen
        return convertView;
    }
}
