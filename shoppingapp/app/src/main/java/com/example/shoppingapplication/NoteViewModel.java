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
//        arrayAllNotes = DataHolder.notePopulate(); //testing
//        arrayAllUsers = DataHolder.userPopulate(); //users as a normal Arraylist
//        arrayAllItems = DataHolder.itemPopulate(); //items as a normal ArrayList

        //====== Populate the Data base here with hardcoded items ======================
        deleteAllNotes(); //it is important otherwise it will fill the db with unlimited notes.
        deleteAllItems(); //it is important otherwise it will fill the db with unlimited items.
        deleteAllUsers(); //it is important otherwise it will fill the db with unlimited users.
        pullFromDataHolderToTheArrayLists(); //it works
        syncLiveDataLists(); //it works
        //useThatDeleteEverythingFromEverywhere();//it works!

//        useThatDeleteAllUsers(); //it works!
//        useThatDeleteAllItems(); //it works!
//        useThatDeleteAllNotes(); //it works!

        //transferNotesFromArray(); //it works
        //transferItemsFromArray(); //seems to work
        //transferUsersFromArray(); //seems to work
        //==============================================================================
    }

    //It deletes a User to every data structure (ViewModel, DataHolder, SQLite, Mysql etc)
    //"usethat" methods are the final interface.
    //The important thing about the data manupulation is the timing, as you don't want
    //to manipulate the data while the data is loading.
    void useThatDeleteUser(User user){
        int tmpId = user.getId();
        int positionMV = 0;
        int positionDH = 0;
        deleteUser(user);
        for(User x: arrayAllUsers){
            if(x.getId() == tmpId){
                positionMV = arrayAllUsers.indexOf(x);
            }
        }
        arrayAllUsers.remove(positionMV);
        positionDH = positionMV;
        for(User k: DataHolder.arrayAllUsers){
            if(k.getId() == tmpId){
                positionDH = DataHolder.arrayAllUsers.indexOf(k);
            }
        }
        DataHolder.arrayAllUsers.remove(positionDH);
    }

    //It deletes an Item to every data structure (ViewModel, DataHolder, SQLite, Mysql etc)
    //"usethat" methods are the final interface.
    //The important thing about the data manupulation is the timing, as you don't want
    //to manipulate the data while the data is loading.
    void useThatDeleteItem(Item item){
        int tmpId = item.getId();
        int positionMV = 0;
        int positionDH = 0;
        deleteItem(item);
        for(Item x: arrayAllItems){
            if(x.getId() == tmpId){
                positionMV = arrayAllItems.indexOf(x);
            }
        }
        arrayAllItems.remove(positionMV);
        positionDH = positionMV;
        for(Item k: DataHolder.arrayAllItems){
            if(k.getId() == tmpId){
                positionDH = DataHolder.arrayAllItems.indexOf(k);
            }
        }
        DataHolder.arrayAllItems.remove(positionDH);
    }

    //It deletes a Note to every data structure (ViewModel, DataHolder, SQLite, Mysql etc)
    //"usethat" methods are the final interface.
    //The important thing about the data manupulation is the timing, as you don't want
    //to manipulate the data while the data is loading.
    void useThatDeleteNote(Note note){
        int tmpId = note.getId();
        int positionMV = 0;
        int positionDH = 0;
        delete(note);
        for(Note x: arrayAllNotes){
            if(x.getId() == tmpId){
                positionMV = arrayAllNotes.indexOf(x);
            }
        }
        arrayAllNotes.remove(positionMV);
        positionDH = positionMV;
        for(Note k: DataHolder.arrayAllNotes){
            if(k.getId() == tmpId){
                positionDH = DataHolder.arrayAllNotes.indexOf(k);
            }
        }
        DataHolder.arrayAllNotes.remove(positionDH);
    }

    //Updates a specific user to every data structure (ViewModel, DataHolder, SQLite, Mysql etc)
    //"usethat" methods are the final interface.
    //The important thing about the data manupulation is the timing, as you don't want
    //to manipulate the data while the data is loading.
    //ALSO IMPORTANT in order to update correctly, the new object that updates the old
    // should have the same id with the old one.
    void useThatUpdateUser(User user){
        int tmpId = user.getId();
        int positionMV = 0;
        int positionDH = 0;
        updateUser(user);
        for(User x: arrayAllUsers){
            if(x.getId() == tmpId){
                positionMV = arrayAllUsers.indexOf(x);
            }
        }
        arrayAllUsers.set(positionMV, user);
        positionDH = positionMV;
        for(User k: DataHolder.arrayAllUsers){
            if(k.getId() == tmpId){
                positionDH = DataHolder.arrayAllUsers.indexOf(k);
            }
        }
        DataHolder.arrayAllUsers.set(positionDH, user);
    }


    //Updates a specific item to every data structure (ViewModel, DataHolder, SQLite, Mysql etc)
    //"usethat" methods are the final interface.
    //The important thing about the data manupulation is the timing, as you don't want
    //to manipulate the data while the data is loading.
    //ALSO IMPORTANT in order to update correctly, the new object that updates the old
    // should have the same id with the old one.
    void useThatUpdateItem(Item item){
        int tmpId = item.getId();
        int positionMV = 0;
        int positionDH = 0;
        updateItem(item);
        for(Item x: arrayAllItems){
            if(x.getId() == tmpId){
                positionMV = arrayAllItems.indexOf(x);
            }
        }
        arrayAllItems.set(positionMV, item);
        positionDH = positionMV;
        for(Item k: DataHolder.arrayAllItems){
            if(k.getId() == tmpId){
                positionDH = DataHolder.arrayAllItems.indexOf(k);
            }
        }
        DataHolder.arrayAllItems.set(positionDH, item);
    }

    //Updates a specific Note to every data structure (ViewModel, DataHolder, SQLite, Mysql etc)
    //"usethat" methods are the final interface.
    //The important thing about the data manupulation is the timing, as you don't want
    //to manipulate the data while the data is loading.
    //ALSO IMPORTANT in order to update correctly, the new object that updates the old
    // should have the same id with the old one.
    void useThatUpdateNote(Note note){
        int tmpId = note.getId();
        int positionMV = 0;
        int positionDH = 0;
        update(note);
        for(Note x: arrayAllNotes){
            if(x.getId() == tmpId){
                positionMV = arrayAllNotes.indexOf(x);
            }
        }
        arrayAllNotes.set(positionMV, note);
        positionDH = positionMV;
        for(Note k: DataHolder.arrayAllNotes){
            if(k.getId() == tmpId){
                positionDH = DataHolder.arrayAllNotes.indexOf(k);
            }
        }
        DataHolder.arrayAllNotes.set(positionDH, note);
    }

    //Adds a user to every data structure (ViewModel, DataHolder, SQLite, Mysql etc)
    //"usethat" methods are the final interface.
    //The important thing about the data manupulation is the timing, as you don't want
    //to manipulate the data while the data is loading.
    void useThatCreateUser(User user){
        insertUser(user);
        arrayAllUsers.add(user);
        DataHolder.arrayAllUsers.add(user);
    }

    //Adds an item to every data structure (ViewModel, DataHolder, SQLite, Mysql etc)
    //"usethat" methods are the final interface.
    //The important thing about the data manupulation is the timing, as you don't want
    //to manipulate the data while the data is loading.
    void useThatCreateItem(Item item){
        insertItem(item);
        arrayAllItems.add(item);
        DataHolder.arrayAllItems.add(item);
    }

    //Adds a note to every data structure (ViewModel, DataHolder, SQLite, Mysql etc)
    //"usethat" methods are the final interface.
    //The important thing about the data manupulation is the timing, as you don't want
    //to manipulate the data while the data is loading.
    void useThatCreateNote(Note note){
        insert(note);
        arrayAllNotes.add(note);
        DataHolder.arrayAllNotes.add(note);
    }

    //Deletes Everything from Everywhere. Testing and debugging purposes.
    //"usethat" methods are the final interface.
    void useThatDeleteEverythingFromEverywhere(){
        useThatDeleteAllUsers(); //it works!
        useThatDeleteAllItems(); //it works!
        useThatDeleteAllNotes(); //it works!
    }

    //Deletes all the notes from Everywhere. Testing and debugging purposes.
    //"usethat" methods are the final interface.
    void useThatDeleteAllNotes(){
        deleteAllNotes();
        arrayAllNotes.clear();
        DataHolder.arrayAllNotes.clear();
    }

    //Deletes all the items from Everywhere. Testing and debugging purposes.
    //"usethat" methods are the final interface.
    void useThatDeleteAllItems(){
        deleteAllItems();
        arrayAllItems.clear();
        DataHolder.arrayAllItems.clear();
    }

    //Deletes all the Users from Everywhere. Testing and debugging purposes.
    //"usethat" methods are the final interface.
    void useThatDeleteAllUsers(){
        deleteAllUsers();
        //arrayAllUsers.removeAll(arrayAllUsers);
        arrayAllUsers.clear();
        //DataHolder.arrayAllUsers.removeAll(DataHolder.arrayAllUsers);
        DataHolder.arrayAllUsers.clear();
    }

    //pulls the data from the DataHolder to the arrayLists of NoteViewModel.
    //This method can't be called from the DataHolder because it needs to be called from an activity
    //with a viewmodel.
    //This method is called for Sync reasons. it works!
    void pullFromDataHolderToTheArrayLists(){
        arrayAllNotes = DataHolder.notePopulate();
        arrayAllUsers = DataHolder.userPopulate();
        arrayAllItems = DataHolder.itemPopulate();
    }

    //Transfer all the data from the ArrayLists of ViewModel, (those here), to the SQLite and its LiveData Lists
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