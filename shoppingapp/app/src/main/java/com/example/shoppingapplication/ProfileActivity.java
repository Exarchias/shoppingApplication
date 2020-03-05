package com.example.shoppingapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ProfileActivity extends AppCompatActivity {  //Or USER SETTINGS ACTIVITY
    private NoteViewModel noteViewModel;

    EditText editText;
    EditText editText1;
    Button button2;
    TextView txt;
    TextView txt1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        button2=(Button)findViewById(R.id.button2);
        editText=(EditText)findViewById(R.id.fullname_editText_ProfileA);
        editText1=(EditText)findViewById(R.id.phonenumber_editText_ProfileA);
        noteViewModel = ViewModelProviders.of(this).get(NoteViewModel.class);
        txt=(TextView)findViewById(R.id.textView4);
        txt1=(TextView)findViewById(R.id.textView5);






    }

    public void onClick(View view){
        User tmpUser = DataHolder.arrayAllUsers.get(2);
        String fullname,phonenumber;
        fullname=editText.getText().toString();
        phonenumber=editText1.getText().toString();
        tmpUser.setFullName(fullname);
        tmpUser.setMobilePhone(phonenumber);

        noteViewModel.useThatUpdateUser(tmpUser);
        txt.setText(DataHolder.arrayAllUsers.get(2).getFullName());
        txt1.setText(DataHolder.arrayAllUsers.get(2).getMobilePhone());





    }


}