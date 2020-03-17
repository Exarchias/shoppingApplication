package com.example.shoppingapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class CreateUserActivity extends AppCompatActivity {
    private NoteViewModel noteViewModel;
    EditText editName;
    EditText fullname;
    EditText address;
    EditText passwordEditText;
    TextView testing1;
    TextView testing2;
    TextView testing3;
    TextView testing4;
    Button createUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_user);
        noteViewModel = ViewModelProviders.of(this).get(NoteViewModel.class);
        editName=(EditText)findViewById(R.id.name_editText_CreateUser);
        fullname=(EditText)findViewById(R.id.fullname_editText_CreateUser);
        passwordEditText =(EditText)findViewById(R.id.password_editText_CreateUser);
        address=(EditText)findViewById(R.id.address_editText_CreateUser);
        testing1=(TextView)findViewById(R.id.textViewFullNameCreateUserActivity);
        testing2=(TextView)findViewById(R.id.textViewNameCreateUserActivity);
        testing3=(TextView)findViewById(R.id.textViewPhoneCreateUserActivity);
        testing4=(TextView)findViewById(R.id.textViewAddressCreateUserActivity);
        createUser=(Button)findViewById(R.id.btn_createUser_CreateUserActivity);
    }

    public void onClick(View view){

        User tmp = new User(20,"",false,"12345");
        String name,password,id;
        Boolean isAdmin;
        
        password = passwordEditText.getText().toString();
        tmp.setPassword(password);
        name = editName.getText().toString();
        tmp.setName(name);
        noteViewModel.useThatCreateUser(tmp);

        int last = DataHolder.arrayAllUsers.size()-1;
        testing2.setText(DataHolder.arrayAllUsers.get(last).getName());

        testing3.setText(DataHolder.arrayAllUsers.get(last).getPassword());



    }
}
