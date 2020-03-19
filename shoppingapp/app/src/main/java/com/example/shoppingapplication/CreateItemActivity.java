package com.example.shoppingapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class CreateItemActivity extends AppCompatActivity {
    private NoteViewModel noteViewModel;
    EditText title_editText_CreateItem;
    EditText description_editText_CreateItem;
    EditText price_editText_CreateItem;
    Button btn_createItem_CreateItemActivity;
    Button closeBtn_CreateItemActivity;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_item);
        noteViewModel = ViewModelProviders.of(this).get(NoteViewModel.class);
        title_editText_CreateItem=(EditText)findViewById(R.id.title_editText_CreateItem);
        description_editText_CreateItem=(EditText)findViewById(R.id.description_editText_CreateItem);
        price_editText_CreateItem=(EditText)findViewById(R.id.price_editText_CreateItem);
        btn_createItem_CreateItemActivity=(Button)findViewById(R.id.btn_createItem_CreateItemActivity);
        closeBtn_CreateItemActivity=(Button)findViewById(R.id.closeBtn_CreateItemActivity);



    }



    public void onClickUpdate(View view){
      //int i= Integer.parseInt(title_editText_CreateItem.getText().toString());
      Item tmpItem = new Item(5000,"","");
      String title,description,price;


      title=title_editText_CreateItem.getText().toString();
      price= price_editText_CreateItem.getText().toString();
      description=description_editText_CreateItem.getText().toString();
      tmpItem.setTitle(title);
      tmpItem.setPrice(Integer.parseInt(price));
      tmpItem.setDescription(description);


      noteViewModel.useThatCreateItem(tmpItem);


          //TESTING
        Toast.makeText(CreateItemActivity.this,"The Title of the Item is "+tmpItem.getTitle(),Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(CreateItemActivity.this, HomeActivity.class);
        CreateItemActivity.this.startActivity(intent);


    }

    public void closeBtn(View view){
        Intent intent = new Intent(CreateItemActivity.this, HomeActivity.class);
        CreateItemActivity.this.startActivity(intent);
    }
}
