package com.example.shoppingapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import android.app.Application;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

//Robert: The main reason why ViewModel exists. As a placeholder for the repository.
//Feel free to use ModelView as your main dataholder. I may do the same.
public class NoteViewModel extends AndroidViewModel {
    private NoteRepository repository;
    private LiveData<List<Note>> allNotes; //for testing

    //Feel free to GET your Data from here. Don't add things to the lists manually. use the methods.
    private LiveData<List<User>> allUsers;
    private LiveData<List<Item>> allItems;
    ArrayList<Note> arrayAllNotes;
    ArrayList<User> arrayAllUsers;
    ArrayList<Item> arrayAllItems;

    public NoteViewModel(@NonNull Application application) {
        super(application);
        repository = new NoteRepository(application);
        allNotes = repository.getAllNotes(); //testing
        allUsers = repository.getAllUsers(); //users
        allItems = repository.getAllItems(); //items
        arrayAllNotes = getArrayAllNotes();
    }

    //Insert a note. For testing purposes.
    public void insert(Note note) {
        repository.insert(note);
    }

    //Insert a User. Feel free to use it.
    public void insertUser(User user) {
        repository.insertUser(user);
    }

    //Insert an Item. Feel free to use it.
    public void insertItem(Item item) {
        repository.insertItem(item);
    }

    //Updates a note. For testing purposes
    public void update(Note note) {
        repository.update(note);
    }

    //Updates a User. Feel free to use it.
    public void updateUser(User user) {
        repository.updateUser(user);
    }

    //Updates an Item. Feel free to use it.
    public void updateItem(Item item) {
        repository.updateItem(item);
    }


    //Deletes a Note. For testing purposes
    public void delete(Note note) {
        repository.delete(note);
    }

    //Deletes a User. Feel free to use it.
    public void deleteUser(User user) {
        repository.deleteUser(user);
    }

    //Deletes an Item. Feel free to use it.
    public void deleteItem(Item item) {
        repository.deleteItem(item);
    }

    //Deleting all notes. Testing purposes.
    public void deleteAllNotes() {
        repository.deleteAllNotes();
    }

    //Deleting all users. Try to avoid it.
    public void deleteAllUsers() {
        repository.deleteAllUsers();
    }

    //Deleting all items. Try to avoid it.
    public void deleteAllItems() {
        repository.deleteAllItems();
    }

    //populates the allnotes with all the notes. It uses LiveData. (For testing purposes)
    public LiveData<List<Note>> getAllNotes() {
        return allNotes;
    }

    //populates the allUsers with all the notes. It uses LiveData.
    //Feel free to use it.
    public LiveData<List<User>> getAllUsers() {
        return allUsers;
    }

    //populates the allUsers with all the notes. It uses LiveData.
    //Feel free to use it.
    public LiveData<List<Item>> getAllItems() {
        return allItems;
    }

    ArrayList<Note> getArrayAllNotes(){
        final ArrayList<Note> tempAr  = new ArrayList<>();
        //Note theNote = new Note("Yolo", "yoloyolo", 666);
        //tempAr.add(theNote);
        allNotes.observeForever(new Observer<List<Note>>()
        {
           @Override
            public void onChanged(@Nullable List<Note> notes) {
               ListIterator<Note> notesIterator = notes.listIterator();
               while(notesIterator.hasNext()){
                   tempAr.add(notesIterator.next());
               }

        }}
        );
        return tempAr;
    }


//    //turns a LiveData List of Nots objects to a ArrayList of Note objects
//    void getArrayAllNotes(){
//
//        Note theNote = new Note("Yolo", "yoloyolo", 666);
//        arrayAllNotes.add(theNote);
//
////        getAllNotes().observeForever(new Observer<List<Note>>() {
////            @Override
////            public void onChanged(@Nullable List<Note> notes) {
////
////                //this is how we take the objects from the list. But we need some kind of itteration.
////                //Note note = getAllNotes().getValue().get(0);
////                //String msg = note.getTitle();
////                //String msg2 = "";
////                //Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
////                //notes.listIterator();
////
////                // Getting ListIterator
////                //ListIterator<Note> notesIterator = notes.listIterator();
////                Note theNote = new Note("Yolo", "yoloyolo", 666);
////                arrayAllNotes.add(theNote);
////
////                // Traversing elements
//////                int count = 0;
//////                while(notesIterator.hasNext()){
//////                    //Note theNote = notesIterator.next();
//////                    Note theNote = new Note("Yolo", "yoloyolo", 666);
//////                    arrayAllNotes.add(theNote);
//////
//////                }
////
////                //Toast.makeText(MainActivity.this, msg2, Toast.LENGTH_SHORT).show();
////            }
////        });
//    }
}