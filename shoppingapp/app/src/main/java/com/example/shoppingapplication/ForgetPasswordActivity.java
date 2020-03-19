package com.example.shoppingapplication;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProviders;

import java.util.Random;

public class ForgetPasswordActivity extends AppCompatActivity {

    private EditText emailEditText;
    private EditText phoneEditText;
    private Button sendToEmail;

    private int confirmCode = 0;
    private User usertmp;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);

        emailEditText = findViewById(R.id.email_edit_text);
        phoneEditText = findViewById(R.id.phone_edit_text);
        sendToEmail = findViewById(R.id.send_to_email);



        sendToEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 1. check the phone number exists in database
                // 2. option one send him pass,
                Log.d("sendButton:", "on click");
                //generate random code.
                String phonenumber = phoneEditText.getText().toString();
                if (DataHolder.userTelephoneExists(phonenumber)) {
                    usertmp = RTools.findUserByTelephone(phonenumber);
                    String msgtmp = "Telephone:" + phonenumber + ", name:" + usertmp.getName();
                    //This method works nice until that point.
                    //Toast.makeText(ForgetPasswordActivity.this, msgtmp, Toast.LENGTH_SHORT).show();
                    Log.d("sendButton:", usertmp.getEmail());
                    //String msgtmp2 = msgtmp + ", email:" + emailEditText.getText().toString();
                    //Toast.makeText(ForgetPasswordActivity.this, msgtmp2, Toast.LENGTH_SHORT).show();
                    //the problem seems to be in the if statement.
                    //The email seems correct.
                    //From the if bellow (usertmp.getEmail().equalsIgnoreCase(emailEditText.getText().toString()))
                    if (true) {
                        confirmCode = generateRandomCode();
                        //sendverification works.
                        sendVerificationCode("Your Password is: " + confirmCode, phonenumber);
                        Log.d("sendButton:", String.valueOf(confirmCode));
                        //Send him to login page.

                        Toast.makeText(ForgetPasswordActivity.this, "Sms sent success", Toast.LENGTH_SHORT).show();
                        // 1. confirm code send him to confirmation page()
                        //this part i working
                        Intent intent = new Intent(v.getContext(), ChangePassActivity.class);
                        intent.putExtra("phone", usertmp.getTelephone());
                        intent.putExtra("code", String.valueOf(confirmCode));
                        startActivity(intent);
                    }
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

    public int generateRandomCode() {
        Random random = new Random();
        return random.nextInt(10000) + 1;
    }

}
