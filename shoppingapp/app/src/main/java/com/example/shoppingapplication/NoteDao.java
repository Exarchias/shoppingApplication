package com.example.shoppingapplication;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

//Robert: This is a DAO, (Data Access Object), interface.
// I usually don't like interfaces but I decided to follow the design.
//The big idea is that method that calls the Data Base should have and abstraction here.
//again: I don't like interfaces either.

@Dao
public interface NoteDao {

    //====== Insert ===============
    @Insert
    void insert(Note note);

    @Insert
    void insert(User user);

    @Insert
    void insert(Item item);

    //====== Update ===============
    @Update
    void update(Note note);

    @Update
    void update(User user);

    @Update
    void update(Item item);

    //====== Delete ===============
    @Delete
    void delete(Note note);

    @Delete
    void delete(User user);

    @Delete
    void delete(Item item);

    //====== Delete All ===============
    @Query("DELETE FROM note_table")
    void deleteAllNotes();

    @Query("DELETE FROM user_table")
    void deleteAllUsers();

    @Query("DELETE FROM item_table")
    void deleteAllItems();

    //====== Select All ===============
    @Query("SELECT * FROM note_table ORDER BY priority DESC")
    LiveData<List<Note>> getAllNotes();

    @Query("SELECT * FROM user_table ORDER BY id DESC")
    LiveData<List<Note>> getAllusers();

    @Query("SELECT * FROM note_table ORDER BY id DESC")
    LiveData<List<Note>> getAllItems();
}
