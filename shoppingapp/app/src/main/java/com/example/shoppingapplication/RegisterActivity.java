package com.example.shoppingapplication;

import android.app.ProgressDialog;
import android.content.ContentValues;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import java.security.NoSuchAlgorithmException;

public class RegisterActivity extends AppCompatActivity {

    private Button createAccountButton;
    private EditText inputName, inputPhoneNumber, inputPassword, inputEmail;
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
        inputEmail = findViewById(R.id.register_email_input);

        progressDialog = new ProgressDialog(this);
        noteViewModel = ViewModelProviders.of(this).get(NoteViewModel.class);


        createAccountButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {
                //validate before you
                String name = inputName.getText().toString();
                String phone = inputPhoneNumber.getText().toString().trim();
                String pass = inputPassword.getText().toString();
                String email = inputEmail.getText().toString();

// if password is less than 4 character
                if (pass.length() < 4) {
                    inputPassword.setError("Password must be >4");
                    inputPassword.setFocusable(true);
                } else {

                    try {
                        boolean check = register(name, email, pass, phone, false);
                        if (check) {
                            Toast.makeText(RegisterActivity.this, "Account created successfully ", Toast.LENGTH_SHORT).show();
                        }

                    } catch (NoSuchAlgorithmException e) {
                        e.printStackTrace();
                    }
                }


            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public boolean register(String username, String email, String password,
                            String phone, boolean admin) throws NoSuchAlgorithmException {
        if (!DataHolder.userNameExists(username)) {
            if (!DataHolder.userTelephoneExists(phone)) {
                try {
                    User tmpUser = new User(5000, username, admin, RTools.encrypted(password),
                            email, phone);
                    // creating new user through useThat method
                    noteViewModel.useThatCreateUser(tmpUser);
                    return true;
                } catch (Exception e) {
                    return false;
                }

            }
        }
        return false;
    }


}
