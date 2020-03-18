package com.example.shoppingapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

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
    EditText fullname;
    EditText address;
    EditText passwordEditText;
    EditText userIdEditText;
    TextView testing1;
    TextView testing2;
    TextView testing3;
    TextView testing4;
    Button createUser;
    CheckBox checkBox;


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
        checkBox=(CheckBox)findViewById(R.id.checkBox);
        userIdEditText =(EditText)findViewById(R.id.UserID_editText_CreateUser);
    }

    public void onClick(View view){

        final User tmp = new User(5000,"",false,"12345");
        String name,password,userId;
        Boolean istheUserAdmin;
      //  userId=userIdEditText.getText().toString();
        password = passwordEditText.getText().toString();
      //  tmp.setId(Integer.parseInt(userId));
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

      // final  int last = DataHolder.arrayAllUsers.size()-1;
      //  testing2.setText(DataHolder.arrayAllUsers.get(last).getName());
        Toast.makeText(CreateUserActivity.this,"User is admin: "+tmp.isAdmin(),Toast.LENGTH_SHORT).show();
      //  testing3.setText(DataHolder.arrayAllUsers.get(last).getPassword());



    }
}
