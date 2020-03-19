package com.example.shoppingapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ChangePassActivity extends AppCompatActivity {

    private EditText newPassEditText;
    private EditText codeEditText;
    private Button changePass;

    //temp var
    private String phone ;
    private String code;
    private NoteViewModel noteViewModel;

    private  User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_pass);

        newPassEditText = findViewById(R.id.newpass_edit_text);
        codeEditText = findViewById(R.id.confirmcode_edit_text);
        changePass = findViewById(R.id.changepassButton);
        Intent intent = getIntent();
        code = intent.getStringExtra("code");
        phone= intent.getStringExtra("phone");
        noteViewModel = ViewModelProviders.of(this).get(NoteViewModel.class);
        //user = RTools.findUserByTelephone(phone);
        changePass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String codeed = codeEditText.getText().toString();
                String pass = newPassEditText.getText().toString();
                Log.d("pass: ",pass);
                user = RTools.findUserByTelephone(phone);
                Log.d("user: ",user.getEmail());
                if (code.equalsIgnoreCase(codeed)){
                    if (pass!=null){
                        user.setPassword(pass);
                        noteViewModel.useThatUpdateUser(user);

                        Intent intent = new Intent(ChangePassActivity.this, LoginActivity.class);
                        startActivity(intent);
                    }
                }

            }
        });


    }
}
