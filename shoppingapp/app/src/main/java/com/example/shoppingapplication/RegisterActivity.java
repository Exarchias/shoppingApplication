package com.example.shoppingapplication;

import android.app.ProgressDialog;
import android.content.ContentValues;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import java.security.NoSuchAlgorithmException;

public class RegisterActivity extends AppCompatActivity {

    private Button createAccountButton;
    private EditText inputName, inputPhoneNumber, inputPassword;
    private ProgressDialog progressDialog;
    private NoteViewModel noteViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        createAccountButton = findViewById(R.id.register_btn);
        inputName = findViewById(R.id.register_username_input);
        inputPhoneNumber = findViewById(R.id.register_phone_number_input);
        inputPassword = findViewById(R.id.register_password_input);
        progressDialog = new ProgressDialog(this);
        noteViewModel = ViewModelProviders.of(this).get(NoteViewModel.class);


        createAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        //create account here
        //check if edit text is empty
        //
    }

    public boolean register(String username, String email, String password,
                            String phone, boolean admin) throws NoSuchAlgorithmException {
        if(!DataHolder.userNameExists(username)){
            if(!DataHolder.userTelephoneExists(phone)){
                try{
                    User tmpUser = new User(5000, username, admin, RTools.encrypted(password),
                            email, phone);
                    return true;
                } catch (Exception e){
                    return false;
                }

            }
        }
        return false;
    }



}
