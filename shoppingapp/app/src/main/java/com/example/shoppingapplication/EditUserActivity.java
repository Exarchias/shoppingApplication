package com.example.shoppingapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class EditUserActivity extends AppCompatActivity {
    private NoteViewModel noteViewModel;

    EditText editTextNameEditUser;
    EditText editTextPhoneNumberEditUser;
    EditText editTextEmailAddressEditUser;
    Button btnUpdateThisUser;
    Button closeBtn_EditUserActivity;
    CheckBox checkBoxEditUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user);
        noteViewModel = ViewModelProviders.of(this).get(NoteViewModel.class);
        editTextPhoneNumberEditUser=(EditText)findViewById(R.id.phonenumber_editText_editUserActivity);
        editTextEmailAddressEditUser =(EditText)findViewById(R.id.emailAddress_editText_editUserActivity);
        editTextNameEditUser =(EditText)findViewById(R.id.name_editText_editUserActivity);
        btnUpdateThisUser=(Button)findViewById(R.id.btnupdate_editUserActivity);
        checkBoxEditUser=(CheckBox)findViewById(R.id.checkBoxEditUser);
        closeBtn_EditUserActivity=(Button)findViewById(R.id.closeBtn_EditUserActivity);


        editTextNameEditUser.setText(DataHolder.userInFocus.getName());
        editTextPhoneNumberEditUser.setText(DataHolder.userInFocus.getMobilePhone());
        editTextEmailAddressEditUser.setText(DataHolder.userInFocus.getEmail());
    }


    public void onClick(View view){
        final User tmpUser=DataHolder.userInFocus;
        String name,emailAddress,phonenumber;
        Boolean makeTheUserAnAdmin;
        name= editTextNameEditUser.getText().toString();
        emailAddress = editTextEmailAddressEditUser.getText().toString();
        phonenumber=editTextPhoneNumberEditUser.getText().toString();
        tmpUser.setEmail(emailAddress);
        tmpUser.setName(name);
        tmpUser.setMobilePhone(phonenumber);

        checkBoxEditUser.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                if (checkBoxEditUser.isChecked()){
                    tmpUser.setAdmin(true);
                }else {
                    tmpUser.setAdmin(false);
                }
            }
        });
        makeTheUserAnAdmin=checkBoxEditUser.isChecked();
        tmpUser.setAdmin(makeTheUserAnAdmin);


        noteViewModel.useThatUpdateUser(tmpUser);






        //TESTING AREA
       Toast.makeText(EditUserActivity.this,"User is admin: "+tmpUser.isAdmin(),Toast.LENGTH_SHORT).show();


        Intent intent = new Intent(EditUserActivity.this, AdminPanel.class);

        EditUserActivity.this.startActivity(intent);




    }

    public void closeBtn(View view){
        Intent intent = new Intent(EditUserActivity.this, HomeActivity.class);
        EditUserActivity.this.startActivity(intent);
    }
}
