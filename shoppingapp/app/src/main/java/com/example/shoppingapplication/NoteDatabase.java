package com.example.shoppingapplication;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

//Robert: This is the actual DataBase.
//PLEASE avoid to do any changes in this class, (apart of the hardcoded populations of course)
@Database(entities = {Note.class, User.class, Item.class}, version = 8)
public abstract class NoteDatabase extends RoomDatabase {

    //Robert: it is a singleton pattern. That means that no second Database can be initiated.
    private static NoteDatabase instance;

    public abstract NoteDao noteDao();
    public abstract UserDao userDao();
    public abstract ItemDao itemDao();

    public static synchronized NoteDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    NoteDatabase.class, "note_database")
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }

    //Robert: This is used for the hardcoded population of the Data Base.
    //Now it is used for testing purposes, BUT it will be surely be a Superuser,
    //for safety reasons
    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    //ASYNC for popylating the data base on creation. Try to avoid changes here.
    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        private NoteDao noteDao;
        private UserDao userDao;
        private ItemDao itemDao;

        private PopulateDbAsyncTask(NoteDatabase db) {
            noteDao = db.noteDao();
            userDao = db.userDao();
            itemDao = db.itemDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            //====== Populate the Data base here with hardcoded items ======================

            //==============================================================================
            return null;
        }
    }
}