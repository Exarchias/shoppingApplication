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
    Button button2;
    TextView txt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        button2=(Button)findViewById(R.id.button2);
        editText=(EditText)findViewById(R.id.fullname_editText_ProfileA);
        noteViewModel = ViewModelProviders.of(this).get(NoteViewModel.class);
        txt=(TextView)findViewById(R.id.textView4);





    }

    public void onClick(View view){
        User tmpUser = DataHolder.arrayAllUsers.get(2);
        String fullname;
        fullname=editText.getText().toString();
        tmpUser.setFullName(fullname);
        noteViewModel.useThatUpdateUser(tmpUser);
        txt.setText(DataHolder.arrayAllUsers.get(2).getFullName());





    }


}