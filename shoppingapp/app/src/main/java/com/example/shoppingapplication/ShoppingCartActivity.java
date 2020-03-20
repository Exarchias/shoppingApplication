package com.example.shoppingapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.room.FtsOptions;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class ShoppingCartActivity extends AppCompatActivity {
    ListView shoppingCartListView;
    Button toCheckOutBtn;
    double total;
    private NoteViewModel noteViewModel;

    //This variable controls if the payment will be done here or in the nect activity.
    //if true, the purchase will be done in this activity and the next will be the invoice.
    //if false the purchase will be in the next activity.
    boolean payment = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart);
        noteViewModel = ViewModelProviders.of(this).get(NoteViewModel.class);
        shoppingCartListView = (ListView)findViewById(R.id.shoppingCartListView);
        toCheckOutBtn = (Button)findViewById(R.id.toCheckOutBtn);
        total = caclulateTotal();
        String msg = "Checkout: " + total;
        toCheckOutBtn.setText(msg);
        populateCartListView();

        toCheckOutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = "Order:" + DataHolder.nextNoteId +  ", Items:" + DataHolder.arrayCartItems.size();
                String description = "a description of the order";
                description = produceADescription();
                Note ordertmp = new Note(DataHolder.nextNoteId, title, description, DataHolder.userInFocus.getId());
                ordertmp.setTotal(total);
                ordertmp.setTotalSTR(String.valueOf(total));
                ordertmp.setownersInfo(generateUserInfo());
                ordertmp.setDateString(RTools.getCurrentDateSTR());
                ordertmp.setDateDate(RTools.getCurrentDate());
                ordertmp.setTheItems(produceAListOfItems());
                ordertmp.setUser(DataHolder.userInFocus);
                ordertmp.theActualItems = DataHolder.arrayCartItems;
                DataHolder.noteInFocus = ordertmp;
                testing();
                doThePayment();
                //HomeActivity here needs to be replaced with the activity where the checkout will take place.
                Intent intent = new Intent(ShoppingCartActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });
    }

    //Script for testing the String values
    void testing(){
        Note ordertmp2 = DataHolder.noteInFocus;
        String msgtmp2 = ordertmp2.getTitle() + "\n ";
        msgtmp2 = msgtmp2 + ordertmp2.getDescription() + "\n ";
        msgtmp2 = msgtmp2 + ordertmp2.getownersInfo() + "\n ";
        msgtmp2 = msgtmp2 + ordertmp2.getDateString() + "\n ";
        Toast.makeText(ShoppingCartActivity.this, msgtmp2, Toast.LENGTH_SHORT).show();
    }

    void doThePayment(){
        if(payment){
            Note ordertmp2 = DataHolder.noteInFocus;
            noteViewModel.useThatCreateNote(ordertmp2);
            DataHolder.arrayCartItems.clear();
        }
    }

    void populateCartListView(){
        //the items that are collected to the shopping cart
        ArrayList<Item> arrayOfItems = DataHolder.arrayCartItems;
        // Create the adapter to convert the array to views
        RItemAdapter adapter = new RItemAdapter(ShoppingCartActivity.this, arrayOfItems);
        // Attach the adapter to a ListView
        shoppingCartListView.setAdapter(adapter);
    }

    double caclulateTotal(){
        double total2 = 0.0;
        for(Item item: DataHolder.arrayCartItems){
            total2 = total2 + item.getPrice();
        }
        return total2;
    }

    String produceADescription(){
        String msgtmp = DataHolder.arrayCartItems.size() + " items: \n";
        for(Item item: DataHolder.arrayCartItems){
            msgtmp = msgtmp + item.getTitle() + "\n";
        }
        return msgtmp;
    }

    String produceAListOfItems(){
        String msgtmp = " items: \n";
        for(Item item: DataHolder.arrayCartItems){
            msgtmp = msgtmp + item.getTitle() + "\n";
        }
        return msgtmp;
    }

    String generateUserInfo(){
        User usertmp = DataHolder.userInFocus;
        String msgtmp = "";
        msgtmp = msgtmp + "Username:" + usertmp.getName() + "\n";
        msgtmp = msgtmp + "Full name:" + usertmp.getFullName() + "\n";
        msgtmp = msgtmp + "Telephone:" + usertmp.getTelephone() + "\n";
        msgtmp = msgtmp + "Email:" + usertmp.getEmail() + "\n";
        msgtmp = msgtmp + "Address:" + usertmp.getAddress() + "\n";
        msgtmp = msgtmp + "PostCode:" + usertmp.getPostCode() + "\n";
        return msgtmp;
    }


    }
