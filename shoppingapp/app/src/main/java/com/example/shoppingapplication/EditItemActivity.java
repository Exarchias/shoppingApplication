package com.example.shoppingapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class EditItemActivity extends AppCompatActivity {
    private NoteViewModel noteViewModel;
    EditText editTitleOfTheItem;
    EditText editDescriptionOfTheItem;
    Button btnEditItem;
    TextView testingTxtView;
    EditText priceOfTheItem;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item);
        noteViewModel = ViewModelProviders.of(this).get(NoteViewModel.class);
        editTitleOfTheItem=(EditText)findViewById(R.id.titleOfTheItem_editText_editItemActivity);
        editDescriptionOfTheItem=(EditText)findViewById(R.id.descriptionOfTheItem_editText_editItemActivity);
        btnEditItem=(Button)findViewById(R.id.btnEditItem_editItemActivity);
        testingTxtView =(TextView)findViewById(R.id.testingTextViewInEditItemActivity);
        priceOfTheItem =(EditText)findViewById(R.id.priceOfTheItem_editText_editItemActivity);



        String price = String.valueOf(DataHolder.itemInFocus.getPrice());
        editTitleOfTheItem.setText(DataHolder.itemInFocus.getTitle());
        editDescriptionOfTheItem.setText(DataHolder.itemInFocus.getDescription());
        priceOfTheItem.setText(price);

    }
    public void editItemBtn(View view){
      Item tmpItem = DataHolder.itemInFocus;
      String titleOfItem,descriptionOfItem,finalPrice;
      finalPrice = priceOfTheItem.getText().toString();
      titleOfItem = editTitleOfTheItem.getText().toString();
      descriptionOfItem = editDescriptionOfTheItem.getText().toString();
      tmpItem.setPrice(Integer.parseInt(finalPrice));
      tmpItem.setTitle(titleOfItem);
      tmpItem.setDescription(descriptionOfItem);
      noteViewModel.useThatUpdateItem(tmpItem);


        //A TEXTFIELD ADDED FOR TESTING IF U WISH
     // String testing12 = String.valueOf(DataHolder.itemInFocus.getPrice());
    //  testingTxtView.setText(testing12);

    }


}
