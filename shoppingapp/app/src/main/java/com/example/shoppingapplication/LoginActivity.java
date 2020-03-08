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
import androidx.lifecycle.ViewModelProviders;

import java.security.NoSuchAlgorithmException;


public class LoginActivity extends AppCompatActivity {

    private EditText inputPhonenumber, inputPassword;

    private Button loginButton;
    private ProgressDialog progressDialog;

    private String parentDBName = "Users";
    private CheckBox chkBoxRememberMe;
    private TextView adminLink, notAdminLink;

    private NoteViewModel noteViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginButton = findViewById(R.id.login_btn);
        inputPassword = findViewById(R.id.login_password_input);
        inputPhonenumber = findViewById(R.id.login_phone_number_input);

        adminLink = findViewById(R.id.admin_panel_link);
        notAdminLink = findViewById(R.id.not_admin_panel_link);

        progressDialog = new ProgressDialog(this);
        chkBoxRememberMe = findViewById(R.id.remember_me_chkb);

        noteViewModel = ViewModelProviders.of(this).get(NoteViewModel.class);


        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                //startActivity(intent);
                try {
                    login(inputPhonenumber.getText().toString(), inputPassword.getText().toString());
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                }

            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void login(String phone, String password) throws NoSuchAlgorithmException {
        User user;
        if(DataHolder.userTelephoneExists(phone)){
            user = RTools.findUserByTelephone(phone);
            if (RTools.checkLoginWithHash(user, password)){
                DataHolder.activeUser = user;
                DataHolder.userInFocus = user;
                DataHolder.isAdmin = user.isAdmin();
                //move to new activity. This little part maybe in need of small edit,
                // (depends on the Activity which it is in)
                Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                startActivity(intent);
            }

        }
    }

}
