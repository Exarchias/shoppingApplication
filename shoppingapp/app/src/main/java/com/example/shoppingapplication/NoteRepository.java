package com.example.shoppingapplication;

import android.app.Application;
import android.os.AsyncTask;
import androidx.lifecycle.LiveData;

import java.util.List;

public class NoteRepository {
    private NoteDao noteDao;
    private LiveData<List<Note>> allNotes; //For testing purposes

    //The lists bellow are the ones that you will GET your data. The get updated automatically
    //through LiveData. PLEASE dont add stuff directly to them.
    //For example. Don't do: allUsers.add(someRandomUser), but use the update methods bellow.
    //For example use the: insertUser(someRandomUser).
    //NEVER use or change the ASYNC methods for any reason.
    private LiveData<List<User>> allUsers;
    private LiveData<List<Item>> allItems;

    //Robert: Everything about the database instantiates here.
    public NoteRepository(Application application) {
        NoteDatabase database = NoteDatabase.getInstance(application);
        noteDao = database.noteDao();
        allNotes = noteDao.getAllNotes();
    }

    //Robert: The methods here are the methods that we actually use, in order to allow
    // our tasks to run in the background

    public void insert(Note note) {
        new InsertNoteAsyncTask(noteDao).execute(note);
    }

    //Insert User. Use it!
    public void insertUser(User user) {
        new InsertUserAsyncTask(noteDao).execute(user);
    }

    //Insert User. Use it!
    public void insertItem(Item item) {
        new InsertItemAsyncTask(noteDao).execute(item);
    }

    //Update Note. Testing purposes
    public void update(Note note) {
        new UpdateNoteAsyncTask(noteDao).execute(note);
    }

    //Update User. Use it!
    public void updateUser(User user) {
        new UpdateUserAsyncTask(noteDao).execute(user);
    }

    //Update Item. Use it!
    public void updateItem(Item item) {
        new UpdateItemAsyncTask(noteDao).execute(item);
    }

    //Delete Note. Testing Purposes
    public void delete(Note note) {
        new DeleteNoteAsyncTask(noteDao).execute(note);
    }

    //Delete User. Use it!
    public void deleteUser(User user) {
        new DeleteUserAsyncTask(noteDao).execute(user);
    }

    //Delete Item. Use it!
    public void deleteItem(Item item) {
        new DeleteItemAsyncTask(noteDao).execute(item);
    }

    //Delete ALL Notes. Testing purposes.
    public void deleteAllNotes() {
        new DeleteAllNotesAsyncTask(noteDao).execute();
    }

    //Delete ALL Users. Use that if you have. Try to AVOID it.
    public void deleteAllUsers() {
        new DeleteAllUsersAsyncTask(noteDao).execute();
    }

    //Delete ALL Items. Use that if you have. Try to AVOID it.
    public void deleteAllItems() {
        new DeleteAllItemsAsyncTask(noteDao).execute();
    }

    //Testing purposes
    public LiveData<List<Note>> getAllNotes() {
        return allNotes;
    }

    //Getter for the allUsers List. It is LiveData and it updates, automatically.
    public LiveData<List<User>> getAllUsers() {
        return allUsers;
    }

    //Getter for the allItems List. It is LiveData and it updates, automatically.
    public LiveData<List<Item>> getAllItems() {
        return allItems;
    }


    //Robert: ASYNC methods. PLEASE don't change anything below this line
    //====================================================================
    //Insert Note, (ASYNC). Testing purposes
    private static class InsertNoteAsyncTask extends AsyncTask<Note, Void, Void> {
        private NoteDao noteDao;

        private InsertNoteAsyncTask(NoteDao noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.insert(notes[0]);
            return null;
        }
    }

    //Insert User (ASYNC)
    private static class InsertUserAsyncTask extends AsyncTask<User, Void, Void> {
        private NoteDao noteDao;

        private InsertUserAsyncTask(NoteDao noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(User... users) {
            noteDao.insert(users[0]);
            return null;
        }
    }

    //Insert Item (ASYNC)
    private static class InsertItemAsyncTask extends AsyncTask<Item, Void, Void> {
        private NoteDao noteDao;

        private InsertItemAsyncTask(NoteDao noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Item... items) {
            noteDao.insert(items[0]);
            return null;
        }
    }

    //Update Note (ASYNC)
    private static class UpdateNoteAsyncTask extends AsyncTask<Note, Void, Void> {
        private NoteDao noteDao;

        private UpdateNoteAsyncTask(NoteDao noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.update(notes[0]);
            return null;
        }
    }

    //Update User (ASYNC)
    private static class UpdateUserAsyncTask extends AsyncTask<User, Void, Void> {
        private NoteDao noteDao;

        private UpdateUserAsyncTask(NoteDao noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(User... users) {
            noteDao.update(users[0]);
            return null;
        }
    }

    //Update Item (ASYNC)
    private static class UpdateItemAsyncTask extends AsyncTask<Item, Void, Void> {
        private NoteDao noteDao;

        private UpdateItemAsyncTask(NoteDao noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Item... items) {
            noteDao.update(items[0]);
            return null;
        }
    }

    //Delete Note (ASYNC)
    private static class DeleteNoteAsyncTask extends AsyncTask<Note, Void, Void> {
        private NoteDao noteDao;

        private DeleteNoteAsyncTask(NoteDao noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.delete(notes[0]);
            return null;
        }
    }

    //Delete User (ASYNC)
    private static class DeleteUserAsyncTask extends AsyncTask<User, Void, Void> {
        private NoteDao noteDao;

        private DeleteUserAsyncTask(NoteDao noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(User... users) {
            noteDao.delete(users[0]);
            return null;
        }
    }

    //Delete Item (ASYNC)
    private static class DeleteItemAsyncTask extends AsyncTask<Item, Void, Void> {
        private NoteDao noteDao;

        private DeleteItemAsyncTask(NoteDao noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Item... items) {
            noteDao.delete(items[0]);
            return null;
        }
    }

    //Delete ALL notes (ASYNC)
    private static class DeleteAllNotesAsyncTask extends AsyncTask<Void, Void, Void> {
        private NoteDao noteDao;

        private DeleteAllNotesAsyncTask(NoteDao noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            noteDao.deleteAllNotes();
            return null;
        }
    }

    //Delete ALL users (ASYNC)
    private static class DeleteAllUsersAsyncTask extends AsyncTask<Void, Void, Void> {
        private NoteDao noteDao;

        private DeleteAllUsersAsyncTask(NoteDao noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            noteDao.deleteAllUsers();
            return null;
        }
    }

    //Delete ALL items (ASYNC)
    private static class DeleteAllItemsAsyncTask extends AsyncTask<Void, Void, Void> {
        private NoteDao noteDao;

        private DeleteAllItemsAsyncTask(NoteDao noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            noteDao.deleteAllItems();
            return null;
        }
    }
}
