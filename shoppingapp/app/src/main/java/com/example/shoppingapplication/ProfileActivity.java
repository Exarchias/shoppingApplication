package com.example.shoppingapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ProfileActivity extends AppCompatActivity {  //Or USER SETTINGS ACTIVITY
    private NoteViewModel noteViewModel;
    Button adminPanelTest;
    EditText editText;
    EditText editText1;
    EditText editText3;
    Button btnUpdate;
    TextView txt;
    TextView txt1;
    TextView txt2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        btnUpdate=(Button)findViewById(R.id.btnupdate_profileA);
        editText=(EditText)findViewById(R.id.fullname_editText_ProfileA);
        editText1=(EditText)findViewById(R.id.phonenumber_editText_ProfileA);
        editText3=(EditText)findViewById(R.id.address_editText_ProfileA);
        noteViewModel = ViewModelProviders.of(this).get(NoteViewModel.class);
        txt=(TextView)findViewById(R.id.textView4);
        txt1=(TextView)findViewById(R.id.textView5);
        txt2=(TextView)findViewById(R.id.textView6);
        adminPanelTest=(Button)findViewById(R.id.btn_AdminPanel);

        adminPanelTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this, AdminPanel.class);
                ProfileActivity.this.startActivity(intent);
            }
        });




    }

    public void onClick(View view){

        User tmpUser = DataHolder.userInFocus;

        String fullname,phonenumber,address;
        fullname=editText.getText().toString();
        phonenumber=editText1.getText().toString();
        address = editText3.getText().toString();
        tmpUser.setFullName(fullname);
        tmpUser.setMobilePhone(phonenumber);
        tmpUser.setAddress(address);

        noteViewModel.useThatUpdateUser(tmpUser);
       // txt.setText(DataHolder.arrayAllUsers.get(2).getFullName());
        txt.setText(DataHolder.userInFocus.getFullName());
        txt1.setText(DataHolder.arrayAllUsers.get(2).getMobilePhone());
        txt2.setText(DataHolder.arrayAllUsers.get(2).getAddress());





    }


}