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
    private NoteViewModel noteViewModel;

    private Button loginButton;
    private ProgressDialog progressDialog;
    private String parentDBName = "Users";
    private CheckBox chkBoxRememberMe;
    private TextView adminLink, notAdminLink;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        noteViewModel = ViewModelProviders.of(this).get(NoteViewModel.class);

        loginButton = findViewById(R.id.login_btn);
        inputPassword = findViewById(R.id.login_password_input);
        inputPhonenumber = findViewById(R.id.login_phone_number_input);

        adminLink = findViewById(R.id.admin_panel_link);
        notAdminLink = findViewById(R.id.not_admin_panel_link);

        progressDialog = new ProgressDialog(this);
        chkBoxRememberMe = findViewById(R.id.remember_me_chkb);
        //Paper.init(this);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.login_btn:

                }
                //new once

                String userPhone = inputPhonenumber.getText().toString();
                String userPassword = inputPassword.getText().toString();

                /*if (databaseDB.isLoginValid(userPhone, userPassword)) {
                    Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                    startActivity(intent);
                    Toast.makeText(LoginActivity.this, "Login Successful!", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(LoginActivity.this, "Invalid!", Toast.LENGTH_SHORT).show();
                }
*/

                //loginUser();
            }
        });
    }
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void loggedin(String phone, String password) throws NoSuchAlgorithmException {
        String passEncrypt = RTools.encrypted(password);
        boolean telexists = false;
        for(User user: DataHolder.arrayAllUsers){
            if(user.getTelephone().equalsIgnoreCase(phone)){
                telexists=true;
            }
            if(telexists){

            }
        }

    }


    /**
     * This method is to validate the input text fields and verify login credentials from SQLite
     */
    /*private void verifyFromSQLite() {

        if (!validation.isInputEditTextFilled(inputPhonenumber)) {
            return;
        }
        if (!inputValidation.isInputEditTextEmail(textInputEditTextEmail, textInputLayoutEmail, getString(R.string.error_message_email))) {
            return;
        }
        if (!inputValidation.isInputEditTextFilled(textInputEditTextPassword, textInputLayoutPassword, getString(R.string.error_message_email))) {
            return;
        }

        if (databaseHelper.checkUser(textInputEditTextEmail.getText().toString().trim()
                , textInputEditTextPassword.getText().toString().trim())) {


            Intent accountsIntent = new Intent(activity, UsersListActivity.class);
            accountsIntent.putExtra("EMAIL", textInputEditTextEmail.getText().toString().trim());
            emptyInputEditText();
            startActivity(accountsIntent);


        } else {
            // Snack Bar to show success message that record is wrong
            Snackbar.make(nestedScrollView, getString(R.string.error_valid_email_password), Snackbar.LENGTH_LONG).show();
        }
    }*/




        /*adminLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginButton.setText("Login Admin");
                adminLink.setVisibility(View.INVISIBLE);
                notAdminLink.setVisibility(View.VISIBLE);
                // when logged in as admin, DB changes its name from Users to Admins
                parentDBName = "Admins";
            }
        });
        notAdminLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginButton.setText("Login");
                adminLink.setVisibility(View.VISIBLE);
                notAdminLink.setVisibility(View.INVISIBLE);
                // when logged in as users, DB changes its name from Admins to Users
                parentDBName = "Users";
            }
        });*/


    /*private void loginUser() {
        String phone = inputPhonenumber.getText().toString();
        String password = inputPassword.getText().toString();
// if the edit text is empty you have to write either phone or password
        if (TextUtils.isEmpty(phone)) {
            Toast.makeText(this, "Please write your phone number", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Please write your password", Toast.LENGTH_SHORT).show();
        } else {

            progressDialog.setTitle("Login Account");
            progressDialog.setMessage("Please wait, while we are checking for credentials");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();

           // AllowAccessToAccount(phone, password);
        }
    }*/

    /*private void AllowAccessToAccount(final String phone, final String password) {

        *//*if the box ticked,returns true and it will write the phone number
         into user phone(prevalent class),store it into phone memory*//*

        if (chkBoxRememberMe.isChecked()) {
            Paper.book().write(Prevalent.userPhoneKey, phone);
            Paper.book().write(Prevalent.userPasswordKey, password);
        }
        final DatabaseReference rootRef;
        rootRef = FirebaseDatabase.getInstance().getReference();
        rootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                //checks if the user is already has an account, if its just get the phone and let them login
                if (dataSnapshot.child(parentDBName).child(phone).exists()) {
                    // the phone that we retrieve from database
                    Users usersData = dataSnapshot.child(parentDBName).child(phone).getValue(Users.class);
                    // the phone that we retrieve from database
                    if (usersData.getPhone().equals(phone)) {

                        if (usersData.getPassword().equals(password)) {

                            if (parentDBName.equals("Admins")) {
                                Toast.makeText(LoginActivity.this, "Welcome Admin, you are logged in successfully", Toast.LENGTH_SHORT).show();
                                progressDialog.dismiss();

                                Intent intent = new Intent(LoginActivity.this, AdminCategoryActivity.class);
                                startActivity(intent);

                            } else if (parentDBName.equals("Users")) {
                                Toast.makeText(LoginActivity.this, "Logged in successfully", Toast.LENGTH_SHORT).show();
                                progressDialog.dismiss();

                                Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                                Prevalent.currentOnlineUser = usersData;
                                startActivity(intent);
                            }

                        } else {
                            progressDialog.dismiss();
                            Toast.makeText(LoginActivity.this, "Password is incorrect", Toast.LENGTH_SHORT).show();

                        }
                    }

                } else {
                    Toast.makeText(LoginActivity.this, "Account with this" + phone + "number do not exists", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }*/

}
