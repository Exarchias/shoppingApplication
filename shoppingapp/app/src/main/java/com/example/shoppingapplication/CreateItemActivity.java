package com.example.shoppingapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class CreateItemActivity extends AppCompatActivity {
    private NoteViewModel noteViewModel;
    EditText title_editText_CreateItem;
    EditText description_editText_CreateItem;
    EditText price_editText_CreateItem;
    Button btn_createItem_CreateItemActivity;
    TextView textViewDescription;
    TextView textViewTitle;
    TextView textViewPrice;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_item);
        noteViewModel = ViewModelProviders.of(this).get(NoteViewModel.class);
        title_editText_CreateItem=(EditText)findViewById(R.id.title_editText_CreateItem);
        description_editText_CreateItem=(EditText)findViewById(R.id.description_editText_CreateItem);
        price_editText_CreateItem=(EditText)findViewById(R.id.price_editText_CreateItem);
        btn_createItem_CreateItemActivity=(Button)findViewById(R.id.btn_createItem_CreateItemActivity);
        textViewDescription=(TextView)findViewById(R.id.textViewDescription);
        textViewPrice=(TextView)findViewById(R.id.textViewPrice);
        textViewTitle=(TextView)findViewById(R.id.textViewTitle);



    }



    public void onClickUpdate(View view){
      Item tmpItem = DataHolder.arrayAllItems.get(2);
      String title,description,price;

      title=title_editText_CreateItem.getText().toString();
      price=price_editText_CreateItem.getText().toString();
      description=description_editText_CreateItem.getText().toString();
      tmpItem.setTitle(title);
     // tmpItem.setPrice(price);
      tmpItem.setDescription(description);

      noteViewModel.useThatCreateItem(tmpItem);
      textViewTitle.setText(DataHolder.arrayAllItems.get(2).getTitle());
     // textViewPrice.setText(DataHolder.arrayAllItems.get(2).getPrice());
      textViewDescription.setText(DataHolder.arrayAllItems.get(2).getDescription());



    }
}
