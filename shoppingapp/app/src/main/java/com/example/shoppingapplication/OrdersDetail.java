package com.example.shoppingapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;


// this activity is basically used to update the quantity of a specific item.


public class OrdersDetail extends ShoppingCartActivity {

    private ImageView closeButton, minuButton, plusButton, itemIcon;
    private Button updateItemInfo;
    public TextView itemTitle, itemLeft, itemdescription;
    public EditText itemNumPics, itemPrice;
    ArrayList<Note> theOrdersOftheActiveUser = new ArrayList<>();
    private NoteViewModel noteViewModel; //Kind of necessary in every activity
    String itemID;
    int numPicsLeft = 0;
    String title;
    String description;
    int ownnerId;
    String ownerName;
    ;
    int orderID;
    double price = 0.0;
    TextView itemId;
    Item item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.orders_display_area);
        //The following 1 line is kind of necessary in every activity.
        noteViewModel = ViewModelProviders.of(this).get(NoteViewModel.class);

        itemId = (TextView) findViewById(R.id.ItemID);
        itemTitle = (TextView) findViewById(R.id.orderTitle);
        minuButton = (ImageView) findViewById(R.id.minusButton);
        plusButton = (ImageView) findViewById(R.id.plusButton);
        itemNumPics = (EditText) findViewById(R.id.itemNumPics);
        closeButton = (ImageView) findViewById(R.id.closeButton);
        updateItemInfo = (Button) findViewById(R.id.updateItemInfo);
        itemLeft = (TextView) findViewById(R.id.itemLeft);
        itemdescription = (TextView) findViewById(R.id.descript);
        itemPrice = (EditText) findViewById(R.id.itemPrice);
        itemIcon = (ImageView) findViewById(R.id.itemIcon);


        itemTitle.setText("title" + DataHolder.noteInFocus.getTitle());
        itemId.setText("itemID " + DataHolder.noteInFocus.getId());
        itemdescription.setText("Description " + DataHolder.noteInFocus.getDescription());


    }
}