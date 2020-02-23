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
public interface UserDao {

    //====== Insert ===============
    @Insert
    void insert(User user);

    //====== Update ===============
    @Update
    void update(User user);

    //====== Delete ===============
    @Delete
    void delete(User user);

    //====== Delete All ===============
    @Query("DELETE FROM user_table")
    void deleteAllUsers();

    //====== Select All ===============
    @Query("SELECT * FROM user_table ORDER BY id ASC")
    LiveData<List<User>> getAllusers();

}
