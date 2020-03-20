package com.example.shoppingapplication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;


public class MainLoginActivity extends AppCompatActivity {
    private static String  TAG ="MainLoginActivity";

    private Button joinNowButton, loginButton;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);

        joinNowButton = findViewById(R.id.signUp_btn);
        loginButton = findViewById(R.id.signIn_btn);
        progressDialog = new ProgressDialog(this);


        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.show();
                Intent intent = new Intent(MainLoginActivity.this, LoginActivity.class);
                startActivity(intent);

            }
        });

        joinNowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.show();
                Log.d(TAG,"join btn clicked");
                Intent intent = new Intent(MainLoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

    }
}
