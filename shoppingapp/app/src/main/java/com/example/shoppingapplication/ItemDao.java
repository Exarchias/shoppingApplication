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
public interface ItemDao {

    //====== Insert ===============
    @Insert
    void insert(Item item);

    //====== Update ===============
    @Update
    void update(Item item);

    //====== Delete ===============
    @Delete
    void delete(Item item);

    //====== Delete All ===============
    @Query("DELETE FROM item_table")
    void deleteAllItems();

    //====== Select All ===============
    @Query("SELECT * FROM item_table ORDER BY id ASC")
    LiveData<List<Item>> getAllItems();

}
