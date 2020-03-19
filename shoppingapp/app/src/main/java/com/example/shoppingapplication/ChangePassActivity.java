package com.example.shoppingapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.security.NoSuchAlgorithmException;

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
        String msgtmp = "Genrated Code:" + code + ", telephone:" + phone;
        //Toast.makeText(ChangePassActivity.this, msgtmp, Toast.LENGTH_SHORT).show();
        noteViewModel = ViewModelProviders.of(this).get(NoteViewModel.class);
        //user = RTools.findUserByTelephone(phone);
        changePass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String codeed = codeEditText.getText().toString();
                String pass = newPassEditText.getText().toString();
                Log.d("pass: ",pass);
                user = RTools.findUserByTelephone(phone);
//                String msgtmp2 = "Genrated Code:" + code + ", telephone:" + phone + ", User:" + user.getName();
//                Toast.makeText(ChangePassActivity.this, msgtmp2, Toast.LENGTH_SHORT).show();
                Log.d("user: ",user.getEmail());
                //it seems to work until this point.
                if (code.equalsIgnoreCase(codeed)){
//                    String msgtmp2 = "the generated code matches the given code";
//                    Toast.makeText(ChangePassActivity.this, msgtmp2, Toast.LENGTH_SHORT).show();
                    //pass is never null so we check if it is "", (AKA empty)
                    if (!pass.equalsIgnoreCase("")){
                        //String msgtmp3 = "the given password is " + pass;
                        //Toast.makeText(ChangePassActivity.this, msgtmp3, Toast.LENGTH_SHORT).show();
                        //The problem is here. it has to be encrypted
                        try {
                            user.setPassword(RTools.encrypted(pass));
                        } catch (NoSuchAlgorithmException e) {
                            e.printStackTrace();
                        }
                        noteViewModel.useThatUpdateUser(user);

                        Intent intent = new Intent(ChangePassActivity.this, LoginActivity.class);
                        startActivity(intent);
                    }
                }

            }
        });


    }
}
