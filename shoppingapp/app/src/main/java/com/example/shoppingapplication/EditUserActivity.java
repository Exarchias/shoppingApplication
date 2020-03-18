package com.example.shoppingapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class EditUserActivity extends AppCompatActivity {
    private NoteViewModel noteViewModel;

    EditText editTextFullnameEditUser;
    EditText editTextPhoneNumberEditUser;
    EditText editTextAddressEditUser;
    Button btnUpdateThisUser;
    CheckBox checkBoxEditUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user);
        noteViewModel = ViewModelProviders.of(this).get(NoteViewModel.class);
        editTextPhoneNumberEditUser=(EditText)findViewById(R.id.phonenumber_editText_editUserActivity);
        editTextAddressEditUser=(EditText)findViewById(R.id.address_editText_editUserActivity);
        editTextFullnameEditUser=(EditText)findViewById(R.id.fullname_editText_editUserActivity);
        btnUpdateThisUser=(Button)findViewById(R.id.btnupdate_editUserActivity);
        checkBoxEditUser=(CheckBox)findViewById(R.id.checkBoxEditUser);

        editTextFullnameEditUser.setText(DataHolder.userInFocus.getFullName());
        editTextPhoneNumberEditUser.setText(DataHolder.userInFocus.getMobilePhone());
        editTextAddressEditUser.setText(DataHolder.userInFocus.getAddress());
    }


    public void onClick(View view){
        final User tmpUser=DataHolder.userInFocus;
        String fullname,address,phonenumber;
        Boolean makeTheUserAnAdmin;
        fullname=editTextFullnameEditUser.getText().toString();
        address = editTextAddressEditUser.getText().toString();
        phonenumber=editTextPhoneNumberEditUser.getText().toString();
        tmpUser.setAddress(address);
        tmpUser.setFullName(fullname);
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
      //  Toast.makeText(EditUserActivity.this,"User is admin: "+tmpUser.isAdmin(),Toast.LENGTH_SHORT).show();





    }
}
