package com.example.shoppingapplication;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

//Helpful methods that doen't belong to a specific class.
public class RTools {

    //Robert: this method transforms  a byte stream to a Hex format. We use this method for our hash encryption.
    private static String bytesToHex(byte[] hash) {
        StringBuffer hexString = new StringBuffer();
        for (int i = 0; i < hash.length; i++) {
            String hex = Integer.toHexString(0xff & hash[i]);
            if (hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }
        return hexString.toString();
    }

    //Robert: transforms a string to a hash encryption. This method uses the help of bytesToHex method.
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public static String encrypted(String digested) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] encodedhash = digest.digest(
                digested.getBytes(StandardCharsets.UTF_8));
        return bytesToHex(encodedhash);
    }



    // ================= ITEMS ========================================

    //it finds a user by their id
    static User findUserById(int id){
        for (User user : DataHolder.arrayAllUsers){
            if(id == user.getId()){
                return user;
            }
        }
        return null;
    }

    //it finds the name of a user by their id
    static String findUserNameById(int id){
        if(DataHolder.userIdExists(id)){
            for (User user : DataHolder.arrayAllUsers){
                if(id == user.getId()){
                    return user.getName();
                }
            }
        }
        return null;
    }

    //Returns a String text with the details of the user
    static String detailsOgTheUserAsString(User user){
        String msg = user.getName() + ": \n";
        msg = msg + "============\n";
        msg = msg + "Name: " + user.getFullName() + "\n";
        msg = msg + "Email: " + user.getEmail() + "\n";
        msg = msg + "Telephone: " + user.getTelephone() + "\n";
        msg = msg + "Address: " + user.getAddress() + " PC" + user.getPostCode() + "\n";
        msg = msg + "City: " + user.getCity() + ", " + user.getCountry();
        return  msg;
    }

    //It delivers an ArrayList with all the Orders (Note objects) of a user
    static ArrayList<Note> theOrdersOfTheUser(User user){
        ArrayList<Note> tempArr = new ArrayList<>();
        for(Note order: DataHolder.arrayAllNotes){
            if(order.getUserId() == user.getId()){
                tempArr.add(order);
            }
        }
        return tempArr;
    }

    //It delivers an ArrayList with all the purchased Items of a user
    static ArrayList<Item> theItemsOfTheUser(User user){
        ArrayList<Item> tempArr = new ArrayList<>();
        for(Item item: DataHolder.arrayAllItems){
            if(item.getOwnersId() == user.getId()){
                tempArr.add(item);
            }
        }
        return tempArr;
    }


    // ================= NOTES ========================================

    //it finds a Note by its id
    static Note findNoteById(int id){
        for (Note note : DataHolder.arrayAllNotes){
            if(id == note.getId()){
                return note;
            }
        }
        return null;
    }

    //it finds the title of a Note by its id
    static String findNotesTitleById(int id){
        if(DataHolder.noteIdExists(id)){
            for (Note note : DataHolder.arrayAllNotes){
                if(id == note.getId()){
                    return note.getTitle();
                }
            }
        }
        return null;
    }


    // ================= ITEMS ========================================

    //it finds an Item by its id
    static Item findItemById(int id){
        for (Item item : DataHolder.arrayAllItems){
            if(id == item.getId()){
                return item;
            }
        }
        return null;
    }

    //it finds the title of a Note by its id
    static String findItemsTitleById(int id){
        if(DataHolder.itemIdExists(id)){
            for (Item item : DataHolder.arrayAllItems){
                if(id == item.getId()){
                    return item.getTitle();
                }
            }
        }
        return null;
    }


}
