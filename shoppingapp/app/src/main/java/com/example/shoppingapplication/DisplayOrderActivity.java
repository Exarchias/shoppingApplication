package com.example.shoppingapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class DisplayOrderActivity extends AppCompatActivity {
    ListView listViewNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_order);
        listViewNote = (ListView)findViewById(R.id.listViewNote);
        populateNoteListView();
    }


    void populateNoteListView(){
        // Construct the data source
        ArrayList<Note> arrayOfNotes = new ArrayList<>();
        for(Note order : DataHolder.arrayAllNotes){
            if(order.getUserId() == DataHolder.userInFocus.getId()){
                arrayOfNotes.add(order);
            }
        }
        // Create the adapter to convert the array to views
        RNoteAdapter noteAdapter = new RNoteAdapter(DisplayOrderActivity.this, arrayOfNotes);
        // Attach the adapter to a ListView
        listViewNote.setAdapter(noteAdapter);
    }
}
