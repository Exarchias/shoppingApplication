package com.example.shoppingapplication;

import java.util.ArrayList;

public class DataHolder {
    static ArrayList<Note> arrayAllNotes = notePopulate(); //testing
    static ArrayList<User> arrayAllUsers= userPopulate(); //users as a normal Arraylist
    static ArrayList<Item> arrayAllItems = itemPopulate(); //items as a normal ArrayList


    static void populate(){
        //======== Loading the data to the arraylists of  DataHolder
        arrayAllNotes.add(new Note(1, "Title 1", "Description 1", 1));
        arrayAllNotes.add(new Note(2,"Title 2", "Description 2", 2));
        arrayAllNotes.add(new Note(3, "Title 3", "Description 3", 3));
        arrayAllUsers.add(new User(1, "Admin", false, "12345"));
        arrayAllUsers.add(new User(2, "Alice", false, "12345"));
        arrayAllUsers.add(new User(3, "Bob", false, "12345"));
        arrayAllItems.add(new Item(1, "Item 1", "Description 1"));
        arrayAllItems.add(new Item(2, "item 2", "Description 2"));
        arrayAllItems.add(new Item(3, "item 3", "Description 3"));
        //==========================================================
    }

    static ArrayList<Item> itemPopulate(){
        ArrayList<Item> tempAr = new ArrayList<>();
        tempAr.add(new Item(1, "Item 1", "Description 1"));
        tempAr.add(new Item(2, "item 2", "Description 2"));
        tempAr.add(new Item(3, "item 3", "Description 3"));
        return tempAr;
    }

    static ArrayList<User> userPopulate(){
        ArrayList<User> tempAr = new ArrayList<>();
        tempAr.add(new User(1, "Admin", false, "12345"));
        tempAr.add(new User(2, "Alice", false, "12345"));
        tempAr.add(new User(3, "Bob", false, "12345"));
        return tempAr;
    }

    static ArrayList<Note> notePopulate(){
        ArrayList<Note> tempAr = new ArrayList<>();
        tempAr.add(new Note(1, "Title 1", "Description 1", 1));
        tempAr.add(new Note(2,"Title 2", "Description 2", 2));
        tempAr.add(new Note(3, "Title 3", "Description 3", 3));
        return tempAr;
    }
}
