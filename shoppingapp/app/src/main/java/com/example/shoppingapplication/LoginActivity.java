package com.example.shoppingapplication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.security.NoSuchAlgorithmException;


public class LoginActivity extends AppCompatActivity {

    private EditText inputPhonenumber, inputPassword;
    private Button loginButton;

    private CheckBox chkBoxRememberMe;
    private TextView forgetPass;

    private NoteViewModel noteViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginButton = findViewById(R.id.login_btn);
        inputPassword = findViewById(R.id.login_password_editText);
        inputPhonenumber = findViewById(R.id.login_phone_editText);

        forgetPass = findViewById(R.id.forgetPassword);

        chkBoxRememberMe = findViewById(R.id.remember_me_chkb);

        //noteViewModel = ViewModelProviders.of(this).get(NoteViewModel.class);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {

                try {
                    login(inputPhonenumber.getText().toString(), inputPassword.getText().toString());

                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                }

            }
        });

        forgetPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, ForgetPasswordActivity.class);
                startActivity(intent);
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void login(String phone, String password) throws NoSuchAlgorithmException {
        User user;
        if (DataHolder.userTelephoneExists(phone)) {
            user = RTools.findUserByTelephone(phone);

            if (RTools.checkLoginWithHash(user, password)) {
                DataHolder.activeUser = user;
                DataHolder.userInFocus = user;
                DataHolder.isAdmin = user.isAdmin();
                //move to new activity. This little part maybe in need of small edit,
                // (depends on the Activity which it is in)
                if (user.isAdmin()) {

                    // change an activity when admin logged in/ send to Admin page
                    Toast.makeText(this, "Welcome Admin you logged in successfully", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                    startActivity(intent);


                } else {

                    // change an activity when user logged in send to User page // tell to @Abdi
                    Toast.makeText(this, "Welcome User you logged in successfully", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                    startActivity(intent);

                }
            }else {
                Toast.makeText(this, "Invalid password", Toast.LENGTH_SHORT).show();
            }

        }else {
            Toast.makeText(this, "Invalid Phone", Toast.LENGTH_SHORT).show();
        }
    }

}
