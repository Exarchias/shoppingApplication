package com.example.shoppingapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class CreateUserActivity extends AppCompatActivity {
    private NoteViewModel noteViewModel;
    EditText editName;
    EditText phoneNumberEditText;
    EditText address;
    EditText passwordEditText;
    EditText emailAddressEditText;

    Button createUser;
    CheckBox checkBox;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_user);
        noteViewModel = ViewModelProviders.of(this).get(NoteViewModel.class);
        editName=(EditText)findViewById(R.id.name_editText_CreateUser);
        phoneNumberEditText=(EditText)findViewById(R.id.phoneNumber_editText_CreateUser);
        emailAddressEditText=(EditText)findViewById(R.id.emailAddress_editText_CreateUser);
        passwordEditText =(EditText)findViewById(R.id.password_editText_CreateUser);
        address=(EditText)findViewById(R.id.address_editText_CreateUser);
        createUser=(Button)findViewById(R.id.btn_createUser_CreateUserActivity);
        checkBox=(CheckBox)findViewById(R.id.checkBox);

    }

    public void onClick(View view){

        final User tmp = new User(5000,"",false,"12345");
        String name,password,phoneNumber,emailAddress;
        Boolean istheUserAdmin;
        phoneNumber=phoneNumberEditText.getText().toString();
        tmp.setMobilePhone(phoneNumber);

        emailAddress=emailAddressEditText.getText().toString();
        tmp.setEmail(emailAddress);
        password = passwordEditText.getText().toString();

        tmp.setPassword(password);
        name = editName.getText().toString();
        tmp.setName(name);
        checkBox.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                if (checkBox.isChecked()){
                    tmp.setAdmin(true);
                }else {
                    tmp.setAdmin(false);

                }
            }
        });
        istheUserAdmin=checkBox.isChecked();
        tmp.setAdmin(istheUserAdmin);

        noteViewModel.useThatCreateUser(tmp);

         //STUFF USED FOR TESTING
        Toast.makeText(CreateUserActivity.this,"User is admin: "+tmp.isAdmin(),Toast.LENGTH_SHORT).show();


        Intent intent = new Intent(CreateUserActivity.this, AdminPanel.class);

        CreateUserActivity.this.startActivity(intent);

    }

    public void closeBtn(View view){
        Intent intent = new Intent(CreateUserActivity.this, HomeActivity.class);
        CreateUserActivity.this.startActivity(intent);
    }
}
