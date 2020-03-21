package com.example.shoppingapplication;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.shoppingapplication.gmailSender.GmailSender;

public class InvoiceActivity extends ShoppingCartActivity {

    //Initiations of the TextViews
    TextView totalView;
    TextView ownerInfoView;
    TextView descriptionView;
    TextView titleView;

    //initiations of the buttons
    Button homeBtn;
    Button emailBtn;
    Button pdfBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invoice);

        //assignment of the textviews
        totalView = (TextView)findViewById(R.id.totalView);
        ownerInfoView = (TextView)findViewById(R.id.ownerInfoView);
        descriptionView = (TextView)findViewById(R.id.descriptionView);
        titleView = (TextView)findViewById(R.id.titleView);

        //assignment of the buttons
        homeBtn = (Button)findViewById(R.id.homeBtn);
        emailBtn = (Button)findViewById(R.id.emailBtn);
        pdfBtn = (Button)findViewById(R.id.pdfBtn);

        //adding content to the textviews
        totalView.setText("Total: " + DataHolder.noteInFocus.getTotalSTR());
        ownerInfoView.setText(DataHolder.noteInFocus.getownersInfo());
        descriptionView.setText(DataHolder.noteInFocus.getDescription());
        titleView.setText(DataHolder.noteInFocus.getTitle());

        //assigning onclick methods for the buttons

        //Back to home. Best place to wrap everything up
        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //HomeActivity here needs to be replaced with the activity where the checkout will take place.
                Intent intent = new Intent(InvoiceActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });

        //send email functionality here
        emailBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                try {

                    String email = "noreply.activityfinder@gmail.com";  // temp gmail account to send mails from
                    String pass = "something713";
                    GmailSender gmailSender = new GmailSender(email,pass);
                    //gmailSender.sendMail("test", "Hello gmail","noreply.activityfinder@gmail.com", "karl.i.lundh@gmail.com");
                    gmailSender.sendMailWithPdfAttachment("INVOICE", "ORDER" + "\n" + generateUserInfo() +"\n"+
                             DataHolder.noteInFocus.getTitle() + "\n" + DataHolder.noteInFocus.getDescription() + "\n" +
                                    DataHolder.noteInFocus.getownersInfo() + "\n" + "Total: " + DataHolder.noteInFocus.getTotalSTR(),
                            "PDF TEXT","noreply.activityfinder@gmail.com", "karl.i.lundh@gmail.com");

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
                //added by Ingemar
                //some awesome code.


        //generate pdf functionality here.
        pdfBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //some awesome code.
            }
        });


        //legacy code for reconsideration
        //Thread meachanics. probably will not needed except if it contributes to the email or pdf functionality.
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        //Thread mechanics end here.

    }
}