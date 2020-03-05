package com.example.shoppingapplication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;


public class MainLoginActivity extends AppCompatActivity {

    private Button joinNowButton, loginButton;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        joinNowButton = findViewById(R.id.main_join_now_btn);
        loginButton = findViewById(R.id.main_login_btn);
        progressDialog = new ProgressDialog(this);

        //Paper.init(this);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainLoginActivity.this, LoginActivity.class);
                startActivity(intent);

            }
        });

        joinNowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainLoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });


/**
 * the app will remember the user phone and password and
 * the user will be sent direct to the home page
 */
/*
        String userPhoneKey = Paper.book().read(Prevalent.userPhoneKey);
        String userPasswordKey = Paper.book().read(Prevalent.userPasswordKey);

        if (userPhoneKey != "" && userPasswordKey != "") {
            if (!TextUtils.isEmpty(userPhoneKey) && !TextUtils.isEmpty(userPasswordKey)) {
               // allowAccess(userPhoneKey, userPasswordKey);

                progressDialog.setTitle("Already logged in");
                progressDialog.setMessage("Please wait.....");
                progressDialog.setCanceledOnTouchOutside(false);
                progressDialog.show();
            }

        }*/

    }

  /*  private void allowAccess(final String phone, final String password) {

        final DatabaseReference rootRef;
        rootRef = FirebaseDatabase.getInstance().getReference();
        rootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                //checks if the user is already has an account, if its just get the phone and let them login
                if (dataSnapshot.child("Users").child(phone).exists()) {

                    Users usersData = dataSnapshot.child("Users").child(phone).getValue(Users.class);

                    if (usersData.getPhone().equals(phone)) {
                        if (usersData.getPassword().equals(password)) {
                            Toast.makeText(MainLoginActivity.this, "you are already logged in....", Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();

                            Intent intent = new Intent(MainLoginActivity.this, HomeActivity.class);
                            startActivity(intent);
                        }
                    }

                } else {
                    Toast.makeText(MainLoginActivity.this, "Account with this" + phone + "number do not exists", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }*/
}
