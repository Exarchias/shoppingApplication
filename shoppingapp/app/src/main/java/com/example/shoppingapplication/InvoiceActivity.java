package com.example.shoppingapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;

import com.example.shoppingapplication.gmailSender.GmailSender;

public class InvoiceActivity extends ShoppingCartActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invoice);

        //added by Ingemar

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);

        toCheckOutBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                try {

                    String email = "noreply.activityfinder@gmail.com";  // temp gmail account to send mails from
                    String pass = "something713";
                    GmailSender gmailSender = new GmailSender(email,pass);
                    //gmailSender.sendMail("test", "Hello gmail","noreply.activityfinder@gmail.com", "karl.i.lundh@gmail.com");
                    gmailSender.sendMailWithPdfAttachment("test", "Hello gmail","PDF TEXT","noreply.activityfinder@gmail.com", "karl.i.lundh@gmail.com");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        //added by Ingemar


    }
}