package com.example.shoppingapplication;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.shoppingapplication.gmailSender.GmailSender;
import com.example.shoppingapplication.gmailSender.PDFSaver;

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

    boolean storageWrite = false;
    boolean storageRead = false;

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
                    String msgtmp = "ORDER" + "\n" +
                            DataHolder.noteInFocus.getTitle() + "\n" +
                            DataHolder.noteInFocus.getDescription() + "\n" +
                            DataHolder.noteInFocus.getownersInfo() + "\n" + "Total: " +
                            DataHolder.noteInFocus.getTotalSTR();
//                    gmailSender.sendMail("test", msgtmp,
//                            "noreply.activityfinder@gmail.com", DataHolder.activeUser.getEmail());
                    Toast.makeText(InvoiceActivity.this, "Email is sent", Toast.LENGTH_SHORT).show();


                } catch (Exception e) {
                    e.printStackTrace();
                    String msgtmp = "Email wasn't sent";
                    Toast.makeText(InvoiceActivity.this, msgtmp, Toast.LENGTH_SHORT).show();
                }
            }
        });



        //generate pdf functionality here.
        pdfBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                askPermission();

                    PDFSaver pdf = new PDFSaver();
                    pdf.toExternalStorage(getApplicationContext(), "ORDER" + "\n" + generateUserInfo() +"\n"+
                            DataHolder.noteInFocus.getTitle() + "\n" + DataHolder.noteInFocus.getDescription() + "\n" +
                            DataHolder.noteInFocus.getownersInfo() + "\n" + "Total: " + DataHolder.noteInFocus.getTotalSTR()
                    );

            }
        });




        //legacy code for reconsideration
        //Thread meachanics. probably will not needed except if it contributes to the email or pdf functionality.
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        //Thread mechanics end here.

    }

    private void askPermission(){
        boolean returnVal = false;
//        if (ContextCompat.checkSelfPermission(this,
//                Manifest.permission.READ_EXTERNAL_STORAGE)
//                != PackageManager.PERMISSION_GRANTED) {
//
//                ActivityCompat.requestPermissions(this,
//                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
//                        1);
//        }

        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    2);
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                 storageRead = true;
                } else {

                }
                case 2: {
                    if (grantResults.length > 0
                            && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                        storageWrite = true;
                    } else {

                    }
            }
        }
    }
}