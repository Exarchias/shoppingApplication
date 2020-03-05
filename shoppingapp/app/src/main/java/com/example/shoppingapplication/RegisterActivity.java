package com.example.shoppingapplication;

import android.app.ProgressDialog;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {

    SQLiteOpenHelper openHelper;
    SQLiteDatabase database;
    private Button createAccountButton;
    private EditText inputName, inputPhoneNumber, inputPassword;
    private ProgressDialog progressDialog;
    //DatabaseDB databaseDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        createAccountButton = findViewById(R.id.register_btn);
        inputName = findViewById(R.id.register_username_input);
        inputPhoneNumber = findViewById(R.id.register_phone_number_input);
        inputPassword = findViewById(R.id.register_password_input);
        progressDialog = new ProgressDialog(this);

        //databaseDB = new DatabaseDB(this);


        createAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //new once
                String userName = inputName.getText().toString();
                String userPhone = inputPhoneNumber.getText().toString();
                String userPassword = inputPassword.getText().toString();

                if (userName.length() > 1) {
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("name", userName);
                    contentValues.put("phone", userPhone);
                    contentValues.put("password", userPassword);

                    //databaseDB.insertUser(contentValues);
                    Toast.makeText(RegisterActivity.this, "User registered!", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(RegisterActivity.this, "Enter the values!", Toast.LENGTH_SHORT).show();
                }



                createAccount();
            }
        });
    }

    private void createAccount() {

        String name = inputName.getText().toString();
        String phone = inputPhoneNumber.getText().toString();
        String password = inputPassword.getText().toString();

        if (TextUtils.isEmpty(name)) {
            Toast.makeText(this, "Please write your name...", Toast.LENGTH_SHORT).show();
        }
        if (TextUtils.isEmpty(phone)) {
            Toast.makeText(this, "Please write your phone...", Toast.LENGTH_SHORT).show();
        }
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Please write your password...", Toast.LENGTH_SHORT).show();
        } else {
            progressDialog.setTitle("Create Account");
            progressDialog.setMessage("Please we are checking your credentials");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();

            //validatePhonenumber(name, phone, password);

        }
    }

   /* private void validatePhonenumber(final String name, final String phone, final String password) {

        final DatabaseReference rootRef;
        rootRef = FirebaseDatabase.getInstance().getReference();
        rootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                // if the phone not exist in our database, so let them create account
                if (!(dataSnapshot.child("Users").child(phone).exists())) {

                    HashMap<String, Object> userData = new HashMap<>();
                    userData.put("phone", phone);
                    userData.put("password", password);
                    userData.put("name", name);

                    rootRef.child("Users").child(phone).updateChildren(userData)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(RegisterActivity.this, "Congratulations for your new Account", Toast.LENGTH_SHORT).show();
                                        progressDialog.dismiss();

                                        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                                        startActivity(intent);
                                    } else {
                                        progressDialog.dismiss();
                                        Toast.makeText(RegisterActivity.this, "Network Error, please try again", Toast.LENGTH_SHORT).show();
                                    }

                                }
                            });


                } else {
                    Toast.makeText(RegisterActivity.this, "This" + phone + "is already exists", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();

                    Toast.makeText(RegisterActivity.this, "please use another phone number",
                            Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                    startActivity(intent);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }*/
}
