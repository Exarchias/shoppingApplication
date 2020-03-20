package com.example.shoppingapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class ShoppingCartActivity extends AppCompatActivity {
    ListView shoppingCartListView;
    Button toCheckOutBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart);
        shoppingCartListView = (ListView)findViewById(R.id.shoppingCartListView);
        toCheckOutBtn = (Button)findViewById(R.id.toCheckOutBtn);
        populateCartListView();

        toCheckOutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //HomeActivity here needs to be replaced with the activity where the checkout will take place.
                Intent intent = new Intent(ShoppingCartActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });
    }

    void populateCartListView(){
        //the items that are collected to the shopping cart
        ArrayList<Item> arrayOfItems = DataHolder.arrayCartItems;
        // Create the adapter to convert the array to views
        RItemAdapter adapter = new RItemAdapter(ShoppingCartActivity.this, arrayOfItems);
        // Attach the adapter to a ListView
        shoppingCartListView.setAdapter(adapter);
    }
}
