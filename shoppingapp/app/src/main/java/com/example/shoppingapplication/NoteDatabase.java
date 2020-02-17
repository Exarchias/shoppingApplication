package com.example.shoppingapplication;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

//Robert: This is the actual DataBase
@Database(entities = {Note.class, User.class, Item.class}, version = 1)
public abstract class NoteDatabase extends RoomDatabase {

    //Robert: it is a singlenton. That means that no second Database can be initiated.
    private static NoteDatabase instance;

    public abstract NoteDao noteDao();

    public static synchronized NoteDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    NoteDatabase.class, "note_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}