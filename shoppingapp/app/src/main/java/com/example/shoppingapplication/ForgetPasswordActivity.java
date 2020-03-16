package com.example.shoppingapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.shoppingapplication.gmailSender.GmailSender;

import java.security.SecureRandom;

public class ForgetPasswordActivity extends AppCompatActivity {

    private MailSender mailSender;

    private EditText emailEditText;
    private Button sendToEmail;
    private NoteViewModel noteViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
        noteViewModel = ViewModelProviders.of(this).get(NoteViewModel.class);

        emailEditText = findViewById(R.id.email_edit_text);
        sendToEmail = findViewById(R.id.send_to_email);


        sendToEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // change an activity when admin logged in/ send to Admin page
                //Send mail.

                mailSender = new MailSender();  // make sure the user with phon existes.
                try {
                    //boolean check = mailSender.sendEmail();
                    //if (check){
                        Toast.makeText(ForgetPasswordActivity.this, "Succssess", Toast.LENGTH_SHORT).show();
                    //}
                } catch (Exception e) {
                    Toast.makeText(ForgetPasswordActivity.this, "Fsil", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }

                Intent intent = new Intent(ForgetPasswordActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });


    }


    public void verifyEmail(){

        if (emailEditText.getText().toString().isEmpty()){
            Toast.makeText(this, "please fill you valid email", Toast.LENGTH_SHORT).show();
        }
    }
}
