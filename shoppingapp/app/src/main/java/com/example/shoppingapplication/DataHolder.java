package com.example.shoppingapplication;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class DataHolder {
    static int nextUserId = 6; //it is used for autoincrement of the id.
    static int nextItemId = 7; //it is used for autoincrement of the id.
    static int nextNoteId = 7; //it is used for autoincrement of the id.

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
    static ArrayList<Item> arrayCartItems = new ArrayList<>();
    static User activeUser; //The user who got logged in the system.
    static boolean isAdmin;
    static User userInFocus; //When the system needs to focus to a specific user
    static Note noteInFocus; //when the system needs to focus in a specific Note, (notes are used for orders)
    static Item itemInFocus; //when the system needs to focus to a specific item.



    static ArrayList<Item> itemPopulate(){
        ArrayList<Item> tempAr = new ArrayList<>();
        tempAr.add(new Item(1, "Item 1", "Description 1", 2, 1));
        tempAr.add(new Item(2, "item 2", "Description 2", 2,2));
        tempAr.add(new Item(3, "item 3", "Description 3", 2, 3));
        tempAr.add(new Item(4, "Item 4", "Description 4", 3, 4));
        tempAr.add(new Item(5, "item 5", "Description 5", 3, 5));
        tempAr.add(new Item(6, "item 6", "Description 6", 3, 6));

        return tempAr;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    static ArrayList<User> userPopulate() throws NoSuchAlgorithmException {
        ArrayList<User> tempAr = new ArrayList<>();
        tempAr.add(new User(1, "Admin", true, RTools.encrypted("12345"), "admin@test.clom", "1234567890"));
        tempAr.add(new User(2, "Alice", false, RTools.encrypted("12345"), "alice@test.clom", "2345678901"));
        tempAr.add(new User(3, "Bob", false, RTools.encrypted("12345"), "Bob@test.clom", "3456789012"));
        tempAr.add(new User(4, "Aki", false, RTools.encrypted("1000"), "aki@test.clom", "0764129365"));
        tempAr.add(new User(5, "Robert", true, RTools.encrypted("1"), "RobertKristianAlm@gmail.com", "0760882382"));
        return tempAr;
    }

    static ArrayList<Note> notePopulate(){
        ArrayList<Note> tempAr = new ArrayList<>();
        tempAr.add(new Note(1, "Al Title 1", "Description 1", 2));
        tempAr.add(new Note(2,"Al Title 2", "Description 2", 2));
        tempAr.add(new Note(3, "Al Title 3", "Description 3", 2));
        tempAr.add(new Note(4, "Bob Title 1", "Description 1", 3));
        tempAr.add(new Note(5,"Bob Title 2", "Description 2", 3));
        tempAr.add(new Note(6, "Bob Title 3", "Description 3", 3));
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

    //This will be used when the user forget its password.
    //name is the username not the full name.
    static boolean userEmailExists(String emailtobefound){
        for(User user : arrayAllUsers){
            if(user.getEmail().equalsIgnoreCase(emailtobefound)){
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
