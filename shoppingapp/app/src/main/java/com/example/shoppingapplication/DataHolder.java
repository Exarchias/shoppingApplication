package com.example.shoppingapplication;

import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class DataHolder {
    static ArrayList<Note> arrayAllNotes = notePopulate(); //testing
    static ArrayList<User> arrayAllUsers; //users as a normal Arraylist

    static {
        try {
            arrayAllUsers = userPopulate();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    static ArrayList<Item> arrayAllItems = itemPopulate(); //items as a normal ArrayList
    static User activeUser; //The user who got logged in the system.



    static ArrayList<Item> itemPopulate(){
        ArrayList<Item> tempAr = new ArrayList<>();
        tempAr.add(new Item(1, "Item 1", "Description 1"));
        tempAr.add(new Item(2, "item 2", "Description 2"));
        tempAr.add(new Item(3, "item 3", "Description 3"));
        return tempAr;
    }

    static ArrayList<User> userPopulate() throws NoSuchAlgorithmException {
        ArrayList<User> tempAr = new ArrayList<>();
        tempAr.add(new User(1, "Admin", false, RTools.encrypted("12345")));
        tempAr.add(new User(2, "Alice", false, RTools.encrypted("12345")));
        tempAr.add(new User(3, "Bob", false, RTools.encrypted("12345")));
        return tempAr;
    }

    static ArrayList<Note> notePopulate(){
        ArrayList<Note> tempAr = new ArrayList<>();
        tempAr.add(new Note(1, "Title 1", "Description 1", 1));
        tempAr.add(new Note(2,"Title 2", "Description 2", 2));
        tempAr.add(new Note(3, "Title 3", "Description 3", 3));
        return tempAr;
    }

    //checks if the given User Id exists in the arrayAllUsers, and if the id exists returns true.
    static boolean userIdExists(int id){

        for(User user : arrayAllUsers){
            if(user.getId()==id){
                return true;
            }
        }
        return false;
    }

    //This will be used for the login. It checks if a User with the given name exists
    //name is the username not the full name.
    static boolean userNameExists(String name){

        for(User user : arrayAllUsers){
            if(user.getName().equalsIgnoreCase(name)){
                return true;
            }
        }
        return false;
    }

    //This may be used for the login if the one who works on the login decide that.
    // It checks if a User with the given telephone number exists
    //The telephone number is given in String type.
    static boolean userTelephoneExists(String phone){

        for(User user : arrayAllUsers){
            if(user.getTelephone().equalsIgnoreCase(phone)){
                return true;
            }
        }
        return false;
    }

    //checks if the given Item Id exists in the arrayAllItems, and if the id exists returns true.
    static boolean itemIdExists(int id){

        for(Item item : arrayAllItems){
            if(item.getId()==id){
                return true;
            }
        }
        return false;
    }


    public static boolean checkUserExist(int id, String userPass) {
        boolean isUserFound = false;
        for(User user : arrayAllUsers){
            if(user.getId()== id){
                isUserFound = true;
            }else if (user.getPassword() == userPass) {
                isUserFound = true;
            }

            isUserFound = false;

            }

        return isUserFound;
        }




        //checks if the given Note Id exists in the arrayAllnotes, and if the id exists returns true.
    static boolean noteIdExists(int id){

        for(Note note : arrayAllNotes){
            if(note.getId()==id){
                return true;
            }
        }
        return false;
    }


}
