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
        Item tmpItem = new Item(1, "Packet of Matches", "Always useful", 2, 1);
        tmpItem.setPrice(0.50);
        tempAr.add(tmpItem);

        tmpItem = new Item(2, "An old battery", "It has still some juice in", 2,2);
        tmpItem.setPrice(1.0);
        tempAr.add(tmpItem);

        tmpItem = new Item(3, "Lego parts", "They are collectibles", 2, 3);
        tmpItem.setPrice(0.50);
        tempAr.add(tmpItem);

        tmpItem = new Item(4, "Rusty Coins", "Some of them are really old", 3, 4);
        tmpItem.setPrice(1.50);
        tempAr.add(tmpItem);

        tmpItem = new Item(5, "A hat", "Not a fedora hat", 3, 5);
        tmpItem.setPrice(8.20);
        tempAr.add(tmpItem);

        tmpItem = new Item(6, "Pizza Margarita", "fresh pizza", 3, 6);
        tmpItem.setPrice(5.50);
        tempAr.add(tmpItem);

        return tempAr;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    static ArrayList<User> userPopulate() throws NoSuchAlgorithmException {
        ArrayList<User> tempAr = new ArrayList<>();
        User tmpUser = new User(1, "Admin", true, RTools.encrypted("12345"),
                "admin@test.clom", "1234567890");
        tmpUser.setFullName("Bill Clinton");
        tmpUser.setAddress("The White House");
        tempAr.add(tmpUser);
        tmpUser = new User(2, "Alice", false, RTools.encrypted("12345"),
                "alice@test.clom", "2345678901");
        tmpUser.setFullName("Alice von Wonderland");
        tmpUser.setAddress("Don't ask Avenue 54");
        tempAr.add(tmpUser);
        tmpUser = new User(3, "Bob", false, RTools.encrypted("12345"),
                "Bob@test.clom", "3456789012");
        tmpUser.setFullName("Bob Smith");
        tmpUser.setAddress("The middle of nowhere 62");
        tempAr.add(tmpUser);
        tmpUser = new User(4, "Aki", false, RTools.encrypted("1000"),
                "aki@test.clom", "0764129365");
        tmpUser.setFullName("John Doe");
        tmpUser.setAddress("A random Street 123");
        tempAr.add(tmpUser);
        tmpUser = new User(5, "Robert", true, RTools.encrypted("1"),
                "RobertKristianAlm@gmail.com", "555");
        tmpUser.setFullName("Robert Alm");
        tmpUser.setAddress("Yolo Street 665");
        tempAr.add(tmpUser);
        return tempAr;
    }

    static ArrayList<Note> notePopulate(){
        ArrayList<Note> tempAr = new ArrayList<>();
        Note tmpOrder = new Note(1, "Order:1, Item:1", "Packet of Matches", 2);
        tmpOrder.setDateString("10:37 am 13 March 2020");
        tmpOrder.setTotalSTR("0.50");
        tempAr.add(tmpOrder);

        tmpOrder = new Note(2,"Order:2, Item:1", "An old battery", 2);
        tmpOrder.setDateString("10:42 am 15 March 2020");
        tmpOrder.setTotalSTR("1.0");
        tempAr.add(tmpOrder);

        tmpOrder = new Note(3, "Order:3, Item:1", "Lego parts", 2);
        tmpOrder.setDateString("10:56 am 20 March 2020");
        tmpOrder.setTotalSTR("0.50");
        tempAr.add(tmpOrder);

        tmpOrder = new Note(4, "Order:4, Item:1", "Rusty Coins", 3);
        tmpOrder.setDateString("10:27 am 14 March 2020");
        tmpOrder.setTotalSTR("1.50");
        tempAr.add(tmpOrder);

        tmpOrder = new Note(5,"Order:5, Item:1", "A hat", 3);
        tmpOrder.setDateString("11:52 am 15 March 2020");
        tmpOrder.setTotalSTR("8.20");
        tempAr.add(tmpOrder);

        tmpOrder = new Note(6, "Order:6, Item:1", "Pizza Margarita", 3);
        tmpOrder.setDateString("12:28 am 16 March 2020");
        tmpOrder.setTotalSTR("5.50");
        tempAr.add(tmpOrder);
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
