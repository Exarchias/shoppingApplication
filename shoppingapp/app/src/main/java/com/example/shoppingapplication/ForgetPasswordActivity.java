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
    private Button sendToEmail;

    private int confirmCode = 0;
    private User userTemporary;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);

        emailEditText = findViewById(R.id.email_edit_text);
        phoneEditText = findViewById(R.id.phone_edit_text);
        sendToEmail = findViewById(R.id.sendEmail_editText);



        sendToEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 1. check the phone number exists in database
                // 2. option one send him pass,
                Log.d("sendButton:", "on click");
                //generate random code.
                String phoneNumber = phoneEditText.getText().toString();
                if (DataHolder.userTelephoneExists(phoneNumber)) {
                    userTemporary = RTools.findUserByTelephone(phoneNumber);
                    //String msgtmp = "Telephone:" + phonenumber + ", name:" + usertmp.getName();
                    //Toast.makeText(ForgetPasswordActivity.this, msgtmp, Toast.LENGTH_SHORT).show();
                    Log.d("sendButton:", userTemporary.getEmail());
                    //String msgtmp2 = msgtmp + ", email:" + emailEditText.getText().toString();
                    //Toast.makeText(ForgetPasswordActivity.this, msgtmp2, Toast.LENGTH_SHORT).show();
                    if (userTemporary.getEmail().equalsIgnoreCase(emailEditText.getText().toString())) {
                        confirmCode = generateRandomCode();
                        //sendverification works.
                        sendVerificationCode("Your Password is: " + confirmCode, phoneNumber);
                        Log.d("sendButton:", String.valueOf(confirmCode));
                        //Send him to login page.

                        Toast.makeText(ForgetPasswordActivity.this, "verification code sent successfully", Toast.LENGTH_SHORT).show();
                        // 1. confirm code send him to confirmation page()
                        Intent intent = new Intent(v.getContext(), ChangePassActivity.class);
                        intent.putExtra("phone", userTemporary.getTelephone());
                        intent.putExtra("code", String.valueOf(confirmCode));
                        startActivity(intent);
                    }else {
                        Toast.makeText(ForgetPasswordActivity.this, "Invalid Email", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(ForgetPasswordActivity.this, "Invalid phone number", Toast.LENGTH_SHORT).show();

                }

            }

        });


    }


    /**
     * This method is to send verification code
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
     * This method generates random number
     * @return
     */

    public int generateRandomCode() {
        Random random = new Random();
        return random.nextInt(10000) + 1;
    }

}
