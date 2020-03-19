package com.example.shoppingapplication;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.util.Random;

public class ForgetPasswordActivity extends AppCompatActivity {

    private EditText emailEditText;
    private EditText phoneEditText;
    private Button sendToPhone;
    private int confirmCode = 0;
    private User userTemporary;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);

        emailEditText = findViewById(R.id.email_edit_text);
        phoneEditText = findViewById(R.id.phone_edit_text);
        sendToPhone = findViewById(R.id.send_to_phone);


        sendToPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 1. check the phone number exists in database
                // 2. option one send him password,
                Log.d("sendButton:", "on click");
                //generate random code.
<<<<<<< HEAD
                String phoneNumber = phoneEditText.getText().toString();
                if (DataHolder.userTelephoneExists(phoneNumber)) {
                    userTemporary = RTools.findUserByTelephone(phoneNumber);
                    Log.d("sendButton:", userTemporary.getEmail());
                    if (userTemporary.getEmail().equalsIgnoreCase(emailEditText.getText().toString())) {
                        confirmCode = generateRandomCode();
                        sendVerificationCode("Your Password is: " + confirmCode, phoneNumber);
=======
                String phonenumber = phoneEditText.getText().toString();
                if (DataHolder.userTelephoneExists(phonenumber)) {
                    usertmp = RTools.findUserByTelephone(phonenumber);
                    //String msgtmp = "Telephone:" + phonenumber + ", name:" + usertmp.getName();
                    //Toast.makeText(ForgetPasswordActivity.this, msgtmp, Toast.LENGTH_SHORT).show();
                    Log.d("sendButton:", usertmp.getEmail());
                    //String msgtmp2 = msgtmp + ", email:" + emailEditText.getText().toString();
                    //Toast.makeText(ForgetPasswordActivity.this, msgtmp2, Toast.LENGTH_SHORT).show();
                    if (usertmp.getEmail().equalsIgnoreCase(emailEditText.getText().toString())) {
                        confirmCode = generateRandomCode();
                        //sendverification works.
                        sendVerificationCode("Your Password is: " + confirmCode, phonenumber);
>>>>>>> 90405cbf70c9b2c1bbfcc54b3954898f031ed0d8
                        Log.d("sendButton:", String.valueOf(confirmCode));
                        //Send him to login page.
                        Toast.makeText(ForgetPasswordActivity.this, "your verification code sent successfully to your phone number", Toast.LENGTH_SHORT).show();
                        // 1. confirm code send him to confirmation page()
                        Intent intent = new Intent(v.getContext(), ChangePassActivity.class);
                        intent.putExtra("phone", userTemporary.getTelephone());
                        intent.putExtra("code", String.valueOf(confirmCode));
                        startActivity(intent);
                    }
                }else {
                    Toast.makeText(ForgetPasswordActivity.this, "Invalid email or phone number", Toast.LENGTH_SHORT).show();
                }
            }

        });
    }

    /**
     * This method is to send verification code to its phone number
     *
     * @param code
     * @param phone
     */
    public void sendVerificationCode(String code, String phone) {
        if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS}, 10);
        } else {
            SmsManager sms = SmsManager.getDefault();
            sms.sendTextMessage(phone, null, code, null, null);
        }
    }

    /**
     * generate random number to be sent to its phone as a code
     *
     * @return
     */
    public int generateRandomCode() {
        Random random = new Random();
        return random.nextInt(10000) + 1;
    }

}
