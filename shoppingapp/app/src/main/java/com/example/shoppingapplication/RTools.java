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


    //It delvers an ArrayList wil all the Orders (Note objects) of a user
    static ArrayList<Note> theOrdersOfTheUser(User user){
        ArrayList<Note> tempArr = new ArrayList<>();
        for(Note order: DataHolder.arrayAllNotes){
            if(order.getUserId() == user.getId()){
                tempArr.add(order);
            }
        }
        return tempArr;
    }

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
}
