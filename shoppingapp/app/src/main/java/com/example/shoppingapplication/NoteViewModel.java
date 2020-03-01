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
    ArrayList<Note> arrayAllNotes; //testing
    ArrayList<User> arrayAllUsers; //users as a normal Arraylist
    ArrayList<Item> arrayAllItems; //items as a normal ArrayList

    public NoteViewModel(@NonNull Application application) {
        super(application);
        repository = new NoteRepository(application);
        allNotes = repository.getAllNotes(); //testing
        allUsers = repository.getAllUsers(); //users as LiveData
        allItems = repository.getAllItems(); //items
        //arrayAllNotes = getArrayAllNotes(); //testing
        //arrayAllUsers = getArrayAllUsers(); //users as a normal Arraylist
        //arrayAllItems = getArrayAllItems(); //items as a normal ArrayList

        //====== Populate the Data base here with hardcoded items ======================
        deleteAllNotes(); //it is important otherwise it will fill the db with unlimited notes.
        deleteAllItems(); //it is important otherwise it will fill the db with unlimited items.
        deleteAllUsers(); //it is important otherwise it will fill the db with unlimited users.
        insert(new Note(1, "Title 1", "Description 1", 1));
        insert(new Note(2,"Title 2", "Description 2", 2));
        insert(new Note(3, "Title 3", "Description 3", 3));
        insertUser(new User(1, "Admin", false, "12345"));
        insertUser(new User(2, "Alice", false, "12345"));
        insertUser(new User(3, "Bob", false, "12345"));
        insertItem(new Item(1, "Item 1", "Description 1"));
        insertItem(new Item(2, "item 2", "Description 2"));
        insertItem(new Item(3, "item 3", "Description 3"));
//        DataHolder.arrayAllNotes.add(new Note(1, "Title 1", "Description 1", 1));
//        DataHolder.arrayAllNotes.add(new Note(2,"Title 2", "Description 2", 2));
//        DataHolder.arrayAllNotes.add(new Note(3, "Title 3", "Description 3", 3));
//        DataHolder.arrayAllUsers.add(new User(1, "Admin", false, "12345"));
//        DataHolder.arrayAllUsers.add(new User(2, "Alice", false, "12345"));
//        DataHolder.arrayAllUsers.add(new User(3, "Bob", false, "12345"));
//        DataHolder.arrayAllItems.add(new Item(1, "Item 1", "Description 1"));
//        DataHolder.arrayAllItems.add(new Item(2, "item 2", "Description 2"));
//        DataHolder.arrayAllItems.add(new Item(3, "item 3", "Description 3"));
        //syncLiveDataLists();
        //==============================================================================
    }

    //Transfer all the data from the ArrayLists to the SQLite and its LiveData Lists
    void syncLiveDataLists(){
        transferUsersFromArray();
        transferItemsFromArray();
        transferNotesFromArray();
    }


    //transfers the users from the arrayList to the SQLite.
    //It goes through the Querry because the LiveData List should not be accessed directly.
    void transferUsersFromArray(){
        for (User user: arrayAllUsers){
            insertUser(user);
            //repository.userDao.insert(user);
        }
    }

    //transfers the items from the arrayList to the SQLite.
    //It goes through the SQLite to the List because the LiveData List should not be accessed directly.
    void transferItemsFromArray(){
        for (Item item: arrayAllItems){
            insertItem(item);
            //repository.itemDao.insert(item);
        }
    }

    //transfers the notes from the arrayList to the SQLite.
    //It goes through the SQLite to the List because the LiveData List should not be accessed directly.
    void transferNotesFromArray(){
        for (Note note: arrayAllNotes){
            insert(note);
            //repository.noteDao.insert(note);
        }
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

    //Returns an arrayList with all the Note objects from the LiveData List "allNotes". TESTED
    ArrayList<Note> getArrayAllNotes(){
        if(allNotes != null){
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
        } else {
            return null;
        }
    }

    //

    //Returns an arrayList with all the User objects from the LiveData List "allUsers". NEEDS to BE TESTED
    ArrayList<User> getArrayAllUsers(){
        final ArrayList<User> tempAr  = new ArrayList<>();
        if(allUsers != null){
            //final ArrayList<User> tempAr  = new ArrayList<>();
            allUsers.observeForever(new Observer<List<User>>()
                                    {
                                        @Override
                                        public void onChanged(@Nullable List<User> users) {
                                            ListIterator<User> usersIterator = users.listIterator();
                                            while(usersIterator.hasNext()){
                                                tempAr.add(usersIterator.next());
                                            }

                                        }}
            );
            return tempAr;
        } else {
            tempAr.add(new User(5000, "Mrempty", false, "12345"));
            return tempAr;
        }
    }


    //Returns an arrayList with all the Item objects from the LiveData List "allItems". NEEDS to BE TESTED
    ArrayList<Item> getArrayAllItems(){
        if(allItems != null){
            final ArrayList<Item> tempAr  = new ArrayList<>();
            allItems.observeForever(new Observer<List<Item>>()
                                    {
                                        @Override
                                        public void onChanged(@Nullable List<Item> items) {
                                            ListIterator<Item> itemsIterator = items.listIterator();
                                            while(itemsIterator.hasNext()){
                                                tempAr.add(itemsIterator.next());
                                            }

                                        }}
            );
            return tempAr;
        } else {
            return null;
        }
    }


}