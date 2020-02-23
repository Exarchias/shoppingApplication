package com.example.shoppingapplication;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class MainActivity extends AppCompatActivity {
    private NoteViewModel noteViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        noteViewModel = ViewModelProviders.of(this).get(NoteViewModel.class);
        noteViewModel.getAllNotes().observe(this, new Observer<List<Note>>() {
            @Override
            public void onChanged(@Nullable List<Note> notes) {
                //Tests the handshake by detecting the if the entities are updated.
                //I wish to implement better testing scripts bur I did enough for now.
                Toast.makeText(MainActivity.this, "onChanged", Toast.LENGTH_SHORT).show();
                //this is how we take the objects from the list. But we need some kind of itteration.
                //Note note = noteViewModel.getAllNotes().getValue().get(0);
                //String msg = note.getTitle();
                String msg2 = "";
                String msg3 = "This is an ArrayList: ";
                //Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
                //notes.listIterator();

                // Getting ListIterator
                //ListIterator<Note> notesIterator = notes.listIterator();

                // Traversing elements
                //int count = 0;
//                while(notesIterator.hasNext()){
//                    //msg = String.valueOf(count);
//                    count++;
//                    //Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
//                    msg2 = msg2 + notesIterator.next().getTitle() + " " + count + " ";
//                    //Toast.makeText(MainActivity.this, msg2, Toast.LENGTH_SHORT).show();
//                }

                ArrayList<Note> theNoteArray = noteViewModel.arrayAllNotes;
//                if(theNoteArray != null){
//                    for(Note note1: theNoteArray){
//                        msg3 = msg3 + note1.getTitle() + " / " ;
//                    }
//                }

                ArrayList<User> theUserArray = noteViewModel.arrayAllUsers;
                if(theUserArray != null){
                    for(User user1: theUserArray){
                        msg3 = msg3 + user1.getName() + " / " ;
                    }
                }


                Toast.makeText(MainActivity.this, msg3, Toast.LENGTH_LONG).show();
            }
        });
    }
}