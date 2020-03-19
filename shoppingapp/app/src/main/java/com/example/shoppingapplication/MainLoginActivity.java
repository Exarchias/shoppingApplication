package com.example.shoppingapplication;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;


public class MainLoginActivity extends AppCompatActivity {
    private static String  TAG ="MainLoginActivity";

    private Button signUpButton, signInButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);

        signUpButton = findViewById(R.id.signUp_btn);
        signInButton = findViewById(R.id.signIn_btn);


        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainLoginActivity.this, LoginActivity.class);
                startActivity(intent);

            }
        });

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG,"join btn clicked");
                Intent intent = new Intent(MainLoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

    }
}
