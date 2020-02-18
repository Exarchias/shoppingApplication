package com.example.shoppingapplication;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import android.app.Application;
import java.util.List;

//Robert: The main reason why ViewModel exists. As a placeholder for the repository.
//Feel free to use ModelView as your main dataholder. I may do the same.
public class NoteViewModel extends AndroidViewModel {
    private NoteRepository repository;
    private LiveData<List<Note>> allNotes; //for testing

    //Feel free to GET your Data from here. Don't add things to the lists manually. use the methods.
    private LiveData<List<User>> allUsers;
    private LiveData<List<Item>> allItems;

    public NoteViewModel(@NonNull Application application) {
        super(application);
        repository = new NoteRepository(application);
        allNotes = repository.getAllNotes(); //testing
        allUsers = repository.getAllUsers(); //users
        allItems = repository.getAllItems(); //items
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
}