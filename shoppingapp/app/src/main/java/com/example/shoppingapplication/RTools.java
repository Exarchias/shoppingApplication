package com.example.shoppingapplication;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

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
}
