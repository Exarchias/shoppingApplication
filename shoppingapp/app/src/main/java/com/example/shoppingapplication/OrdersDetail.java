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


public class OrdersDetail extends AppCompatActivity {

    private ImageView closeButton, minuButton, plusButton, itemIcon;
    private Button updateItemInfo;
    public TextView itemTitle, itemLeft, itemCategory;
    public EditText itemNumPics, itemPrice;
    ArrayList<Note> theOrdersOftheActiveUser = new ArrayList<>();
    private NoteViewModel noteViewModel; //Kind of necessary in every activity
    String itemID;
    int numPicsLeft = 0;
    String title;
    String description;
    int ownnerId;
    String ownerName;;
    int orderID;
    double price = 0.0;
Item item;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders_detail);
        //The following 1 line is kind of necessary in every activity.
        noteViewModel = ViewModelProviders.of(this).get(NoteViewModel.class);
        System.out.println("add ");
      // if no items exist then you go back
        itemID = getIntent().getStringExtra("itemID");
        if(itemID == null) {
            onBackPressed();
        }
        itemTitle    = (TextView) findViewById(R.id.itemTitle);
        minuButton   = (ImageView) findViewById(R.id.minusButton);
        plusButton   = (ImageView) findViewById(R.id.plusButton);
        itemNumPics  = (EditText) findViewById(R.id.itemNumPics);
        closeButton  = (ImageView) findViewById(R.id.closeButton);
        updateItemInfo    = (Button) findViewById(R.id.updateItemInfo);
        itemLeft     = (TextView) findViewById(R.id.itemLeft);
        itemCategory = (TextView) findViewById(R.id.itemCategory);
        itemPrice    = (EditText) findViewById(R.id.itemPrice);
        itemIcon     = (ImageView) findViewById(R.id.itemIcon);


        fetchItem();


viewOnClickListeners();
    }

    public void viewOnClickListeners() {
        updateItemInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateItem();
                Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                startActivity(intent);
            }
        });

        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        // this one controls the number of items you want in a specific product
        minuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int num = Integer.parseInt(itemNumPics.getText().toString());
                if(num > 0) {
                    itemNumPics.setText("" + (num - 1));
                }
            }
        });

        // this one controls the number of items you want in a specific product
        plusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int num = Integer.parseInt(itemNumPics.getText().toString());
                itemNumPics.setText("" + (num + 1));
            }
        });
    }
// this method fetches the item
    private void fetchItem() {
        itemID = String.valueOf(item.getId());
        ownerName = RTools.findUserNameById(item.getOwnersId());
        // need orderid
        orderID = item.getNoteId(); //note is the order
        price = item.getPrice();
        title = item.getTitle();
        description = item.getDescription();
        price = item.getPrice();
        try {
            // this one numbers how many items are on the product
            numPicsLeft = item.getSizeInt();

            itemTitle.setText(""+item.getTitle());
            // type should be the category
            itemCategory.setText(""+item.getType());

            // how many items are left to ship for this product
            itemLeft.setText(item.getSizeSTR()+ " Item(s) Left");
            // number of items for this product
            itemNumPics.setText(item.getSizeSTR()+ "");
            // the price of the product
            itemPrice.setText(item.getPrice() + " ");



        } catch (Exception ex) {
        Toast.makeText(this,""+ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    public void updateItem() {
        try {


            itemID = String.valueOf(item.getId());
            ownerName = RTools.findUserNameById(item.getOwnersId());
            // need orderid
            orderID = item.getNoteId(); //note is the order
            price = item.getPrice();
            title = item.getTitle();
            description = item.getDescription();
            price = item.getPrice();

            String numPics = itemNumPics.getText().toString(), price = itemPrice.getText().toString();
            if(numPics.equals("")) {
                numPics = "0";
            }
            if(price.equals("")) {
                price = "0";
            }

            // we add the new values to the item
            item.setSizeSTR(numPics);
            item.setPrice(Double.parseDouble(price));
            noteViewModel.useThatUpdateItem(item);

            // we need to update the items in the database to fit the new changes.
            // a query here would be useful




            // we need a to update the shopping list on the item we selected
            Toast.makeText(this, " Item Information Sucessfully Updated ", Toast.LENGTH_SHORT).show();

        } catch (Exception ex) {
        }
    }

    void createitemsXtimes(Item item, int times){
        for(int x=0; x<times; x++){
            item.setId(5000);
            noteViewModel.useThatCreateItem(item);
        }
    }
}