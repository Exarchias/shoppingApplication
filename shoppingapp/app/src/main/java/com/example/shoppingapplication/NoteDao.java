package com.example.shoppingapplication;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

//Robert: This is a DAO, (Data Access Object), interface.
//The big idea is that the data base handles the queries through this intefaces.
//I don't like interfaces either.

//Robert: PLEASE avoid to do any changes in this class.
@Dao
public interface NoteDao {

    //====== Insert ===============
    @Insert
    void insert(Note note);

    //====== Update ===============
    @Update
    void update(Note note);

    //====== Delete ===============
    @Delete
    void delete(Note note);

    //====== Delete All ===============
    @Query("DELETE FROM note_table")
    void deleteAllNotes();

    //====== Select All ===============
    @Query("SELECT * FROM note_table ORDER BY priority DESC")
    LiveData<List<Note>> getAllNotes();

}
