package com.example.shoppingapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.ListIterator;

public class MainActivity extends AppCompatActivity {
    private NoteViewModel noteViewModel;
    private Button joinNowButton, loginButton;
    Button settingsbtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final TextView textView = (TextView)findViewById(R.id.textview1);
        final TextView textView2 = (TextView)findViewById(R.id.textView2);
        final TextView textView3 = (TextView)findViewById(R.id.textView3);
        Button loginbtn = (Button) findViewById(R.id.loginbtn);
        Button registerbtn = (Button) findViewById(R.id.registerbtn);
        settingsbtn =(Button)findViewById(R.id.settingsbtn);

        settingsbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             Intent intent = new Intent(MainActivity.this,ProfileActivity.class);
             startActivity(intent);
            }
        });



        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });



        //This object is for testing and is used many lines below in order to test the
        //noteViewModel.useThatCreateUser(john) method. it works
        //The important thing about the data manupulation is the timing, as you don't want
        //to manipulate the data while the data is loading.
        User john = new User(4, "john", false, "12345"); //Testing

        //This User object intents to be used to test and demonstrate the updateing process of an object.
        //please notice that the id here is 5, while on updating we will use another id.
        User alina = new User(5, "Alina", false, "12345"); //Testing

        //We taking Bob in order to delete him from the Database below.
        //if no bob is shown that means that the useThatDeleteUser() works.
        //bob is usually located in position 2.
        User userToBeDeleted = DataHolder.arrayAllUsers.get(2); //Testing

        //We taking item3 in order to delete it from the Database below.
        //if no item3 is shown that means that the useThatDeleteItem() works.
        //item3 is usually located in position 2.
        Item itemToBeDeleted = DataHolder.arrayAllItems.get(2); //Testing

        //We taking the Note with title3 in order to delete it from the Database below.
        //if no title3 is shown that means that the useThatDeleteNote() works.
        //title3 is usually located in position 2.
        Note noteToBeDeleted = DataHolder.arrayAllNotes.get(2); //Testing

        //This object is for testing and is used many lines below in order to test the
        //noteViewModel.useThatCreateItem(item4) method. it works.
        //The important thing about the data manupulation is the timing, as you don't want
        //to manipulate the data while the data is loading.
        Item item4 = new Item(4, "Item 4", "Description 4");

        //This Item object intents to be used to test and demonstrate the updateing process of an object.
        //please notice that the id here is 5, while on updating we will use another id.
        Item item5 = new Item(5, "Item 5", "Description 5");

        //This object is for testing and is used many lines below in order to test the
        //noteViewModel.useThatCreateNote(note4) method. it works.
        //The important thing about the data manupulation is the timing, as you don't want
        //to manipulate the data while the data is loading.
        Note note4 = new Note(4, "Title 4", "Description 4", 1);

        //This Note object intents to be used to test and demonstrate the updateing process of an object.
        //please notice that the id here is 5, while on updating we will use another id.
        Note note5 = new Note(5, "Title 5", "Description 5", 1);






        noteViewModel = ViewModelProviders.of(this).get(NoteViewModel.class);
        noteViewModel.getAllNotes().observe(this, new Observer<List<Note>>() {
            @Override
            public void onChanged(@Nullable List<Note> notes) {
//                //Tests the handshake by detecting the if the entities are updated.
//                //I wish to implement better testing scripts bur I did enough for now.
//                //Toast.makeText(MainActivity.this, "onChanged", Toast.LENGTH_SHORT).show();
//                //this is how we take the objects from the list. But we need some kind of itteration.
//                //Note note = noteViewModel.getAllNotes().getValue().get(0);
//                //String msg = note.getTitle();
                String msg2 = "Notes With LiveData: ";
//                //String msg3 = "This is an ArrayList: ";
//                //Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
//                //notes.listIterator();
//
//
                // Getting ListIterator
                ListIterator<Note> notesIterator = notes.listIterator();
//
//                 //Traversing elements
                //int count = 0;
                if(notesIterator != null){
                    while(notesIterator.hasNext()){
                        //msg = String.valueOf(count);
                        //count++;
                        //Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
                        msg2 = msg2 + notesIterator.next().getTitle() + " / ";
                        //Toast.makeText(MainActivity.this, msg2, Toast.LENGTH_SHORT).show();
                    }
                }


//                if(noteViewModel.arrayAllNotes != null){
//                    for(Note note : noteViewModel.arrayAllNotes){
//                        msg2 = msg2 + note.getTitle() + " / ";
//                    }
//                }
                textView.setText(msg2);
//
//                //====== Test Script ================================
//                ArrayList<User> theUserArray = noteViewModel.arrayAllUsers;
//                ArrayList<Item> theItemArray = noteViewModel.arrayAllItems;
//                ArrayList<Note> theNoteArray = noteViewModel.arrayAllNotes;
//
//                String msg3 = "The test runs: ";
//                if(theNoteArray != null){
//                    for(Note note1: theNoteArray){
//                        msg3 = msg3 + note1.getTitle() + " / " ;
//                    }
//                }
//
//
//                if(theUserArray != null){
//                    msg3 = msg3 + theUserArray.size();
//                    for(User user1: theUserArray){
//                        msg3 = msg3 + user1.getName() + " / " ;
//                    }
//                }
//
//
//                if(theItemArray != null){
//                    for(Item note1: theItemArray){
//                        msg3 = msg3 + note1.getTitle() + " / " ;
//                    }
//                }
//
//                Toast.makeText(MainActivity.this, msg3 + " lol " + msg2, Toast.LENGTH_LONG).show();
//                //===============================

            }
        });


        noteViewModel.getAllUsers().observe(this, new Observer<List<User>>() {
            @Override
            public void onChanged(@Nullable List<User> users) {
                String msg4 = " Users With LiveData: ";


                // Getting ListIterator
                ListIterator<User> usersIterator = users.listIterator();

                 //Traversing elements
                if(usersIterator != null){
                    while(usersIterator.hasNext()){
                        //msg = String.valueOf(count);
                        //count++;
                        //Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
                        msg4 = msg4 + usersIterator.next().getName() + " / ";
                        //Toast.makeText(MainActivity.this, msg2, Toast.LENGTH_SHORT).show();
                    }
                }

                textView2.setText(msg4);

            }
        });

        noteViewModel.getAllItems().observe(this, new Observer<List<Item>>() {
            @Override
            public void onChanged(@Nullable List<Item> items) {
                String msg4 = "Items With LiveData: ";

                // Getting ListIterator
                ListIterator<Item> itemsIterator = items.listIterator();

                //Traversing elements
                if(itemsIterator != null){
                    while(itemsIterator.hasNext()){
                        //msg = String.valueOf(count);
                        //count++;
                        //Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
                        msg4 = msg4 + itemsIterator.next().getTitle() + " / ";
                        //Toast.makeText(MainActivity.this, msg2, Toast.LENGTH_SHORT).show();
                    }
                }

                textView3.setText(msg4);

            }
        });

        //====== Test Script ================================

        String msg2 = "Second Test ";


        //Adding a user using the useThat interface
        noteViewModel.useThatCreateUser(john); //it works

        //Adding an item using the useThat interface
        noteViewModel.useThatCreateItem(item4); //it works

        //Adding a note using the useThat interface
        noteViewModel.useThatCreateNote(note4); //it works

        //Here we are taking the id from Alice. which I know that is in positon 1.
        int tmpId = DataHolder.arrayAllUsers.get(1).getId();

        //here we are giving the id from Alice to Alina
        alina.setId(tmpId);

        //Here we are updating the User Alice, with the new User Alina which has the same id
        //to have the same id is important.
        //after this Alice will dissapear and in her positon will be Alina
        noteViewModel.useThatUpdateUser(alina); //it works!

        //Here we are taking the id from item1. which I know that is in positon 0.
        int tmpId2 = DataHolder.arrayAllItems.get(0).getId();

        //Here we are giving the id from item1 to item5
        item5.setId(tmpId2);

        //Here we are updating the item1, with the new Item item5 which has the same id
        //to have the same id is important.
        //after this item1 will dissapear and in its positon will be item5
        noteViewModel.useThatUpdateItem(item5); //it works!

        //Here we are taking the id from title1. which I know that is in positon 0.
        int tmpId3 = DataHolder.arrayAllNotes.get(0).getId();

        //Here we are giving the id from item1 to item5
        note5.setId(tmpId3);

        //Here we are updating the Note title1, with the new Note note5 which has the same id
        //to have the same id is important.
        //after this item1 will dissapear and in its positon will be item5
        noteViewModel.useThatUpdateNote(note5); //it works!

        //This method deletes the userToBeDeleted that we created above.
        //This user is Bob which is usually located in position 2;
        noteViewModel.useThatDeleteUser(userToBeDeleted); //It works

        //This method deletes the itemToBeDeleted that we created above.
        //This Item is item3 which is usually located in position 2;
        noteViewModel.useThatDeleteItem(itemToBeDeleted); //It works

        //This method deletes the noteToBeDeleted that we created above.
        //This Note is title3 which is usually located in position 2;
        noteViewModel.useThatDeleteNote(noteToBeDeleted); //It works

//        ArrayList<User> theUserArray = noteViewModel.arrayAllUsers;
//        ArrayList<Item> theItemArray = noteViewModel.arrayAllItems;
//        ArrayList<Note> theNoteArray = noteViewModel.arrayAllNotes;

        String msg3 = "The test runs: ";
        if(DataHolder.arrayAllNotes != null){
            for(Note note1: DataHolder.arrayAllNotes){
                msg3 = msg3 + note1.getTitle() + " / " ;
            }
        }


        if(DataHolder.arrayAllUsers != null){
            msg3 = msg3 + DataHolder.arrayAllUsers.size();
            for(User user1: DataHolder.arrayAllUsers){
                msg3 = msg3 + user1.getName() + " / " ;
            }
        }


        if(DataHolder.arrayAllItems != null){
            for(Item note1: DataHolder.arrayAllItems){
                msg3 = msg3 + note1.getTitle() + " / " ;
            }
        }
//        if(DataHolder.arrayAllItems != null){
//            msg3 = msg3 + DataHolder.arrayAllItems.get(0).getTitle();
//        }

        Toast.makeText(MainActivity.this, msg3 + " lol " + msg2, Toast.LENGTH_LONG).show();

        //All the Tests are concluded and every data manipulation works as it should.
        //===============================
    }

}