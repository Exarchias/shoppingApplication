package com.example.shoppingapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class CreateItemActivity extends AppCompatActivity {
    EditText title_editText_CreateItem;
    EditText description_editText_CreateItem;
    EditText price_editText_CreateItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_item);
    }



    public void onClickUpdate(View view){

    }
}
