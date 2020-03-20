package com.example.shoppingapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class ShoppingCartActivity extends AppCompatActivity {
    ListView shoppingCartListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart);
        shoppingCartListView = (ListView)findViewById(R.id.shoppingCartListView);
        populateCartListView();
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
