package com.example.shoppingapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class AdminPanel extends AppCompatActivity {
    private NoteViewModel noteViewModel;
    Button createItem;
    Button editItem;
    Button createUser;
    Button editUser;
    Button deleteItem;
    Button deleteUser;
    //Robert: This code is here to stay ==============
    ListView itemsListView;
    ListView usersListView;
    RItemAdapter adapter;
    //=== Code that is here to stay ends here =============
    int value=2;
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_panel);
        noteViewModel = ViewModelProviders.of(this).get(NoteViewModel.class);
        createItem=(Button)findViewById(R.id.btn_createItem);
        editItem=(Button)findViewById(R.id.btn_editItem);
        createUser=(Button)findViewById(R.id.btn_CreateUser);
        editUser=(Button)findViewById(R.id.btn_editUser);
        deleteItem=(Button)findViewById(R.id.btn_deleteItem);
        deleteUser=(Button)findViewById(R.id.btn_deleteUser);
        //Robert: This code is here to stay ==============
        //usersListView = (ListView)findViewById(R.id.first_listView_AdminPanel);
        itemsListView = (ListView)findViewById(R.id.second_listView_AdminPanel);
        //=== Code that is here to stay ends here =============
        populateItemListView();







        createItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adminPanelCreateAnItem(value);
                Intent intent = new Intent(AdminPanel.this, CreateItemActivity.class);
                startActivity(intent);

            }
        });



        editItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adminPanelEditItem(value);
                Intent intent = new Intent(AdminPanel.this, EditItemActivity.class);
                startActivity(intent);
            }
        });


        createUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adminPanelCreateAUser(value);
                Intent intent = new Intent(AdminPanel.this, CreateUserActivity.class);
                startActivity(intent);
            }
        });




        editUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adminPanelEditUser(value);
                Intent intent = new Intent(AdminPanel.this, EditUserActivity.class);
                startActivity(intent);

            }
        });



        deleteUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                adminPanelDeleteUser(value);


            }
        });



        deleteItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adminPanelDeleteUser(value);
            }
        });

    }

    void populateItemListView(){
        // Construct the data source
        ArrayList<Item> arrayOfItems = new ArrayList<Item>();
        arrayOfItems.add(new Item(1, "Item 1", "Description 1", 2, 1));
        // Create the adapter to convert the array to views
        RItemAdapter adapter = new RItemAdapter(AdminPanel.this, arrayOfItems);
        // Attach the adapter to a ListView
        itemsListView.setAdapter(adapter);
    }

    //for the button "Create a new Item" on admin Panel
     void adminPanelCreateAnItem(int id){
        if(DataHolder.isAdmin){
            if(DataHolder.itemIdExists(id)){
                Item item = RTools.findItemById(id);
                DataHolder.itemInFocus = item;
                // The activities need to correspond real activities. (depends on the Activity which it is in)
               // Intent intent = new Intent(AdminPanel.this, CreateItemActivity.class);
              //  startActivity(intent);
            }
        }
    }

    //for the button "Edit Itemr" on admin Panel
    void adminPanelEditItem(int id){
        if(DataHolder.isAdmin){
            if(DataHolder.itemIdExists(id)){
                Item item = RTools.findItemById(id);
                DataHolder.itemInFocus = item;
                // The activities need to correspond real activities. (depends on the Activity which it is in)
             //   Intent intent = new Intent(AdminPanel.this, EditItemActivity.class);
             //   startActivity(intent);
            }
        }
    }
    //for the button "Create a new User" on admin Panel
    void adminPanelCreateAUser(int id){
        if(DataHolder.isAdmin){
            if(DataHolder.userIdExists(id)){
                User user = RTools.findUserById(id);
                DataHolder.userInFocus = user;
                // The activities need to correspond real activities. (depends on the Activity which it is in)
              //  Intent intent = new Intent(AdminPanel.this, CreateUserActivity.class);
              //  startActivity(intent);
            }
        }
    }

    //for the button "Edit User" on admin Panel
    void adminPanelEditUser(int id){
        if(DataHolder.isAdmin){
            if(DataHolder.userIdExists(id)){
                User user = RTools.findUserById(id);
                DataHolder.userInFocus = user;
                // The activities need to correspond real activities. (depends on the Activity which it is in)
              //  Intent intent = new Intent(AdminPanel.this, EditUserActivity.class);
              //  startActivity(intent);
            }
        }
    }
    //for the button "Delete Item" on admin Panel
    void adminPanelDeleteUser(int id){
        if(DataHolder.isAdmin){
            if(DataHolder.itemIdExists(id)){
                User user = RTools.findUserById(id);
                noteViewModel.useThatDeleteUser(user);
            }
        }
    }

    //for the button "Create a new User" on admin Panel
    void adminPanelDeleteItem(int id){
        if(DataHolder.isAdmin){
            if(DataHolder.userIdExists(id)){
                Item item = RTools.findItemById(id);
                noteViewModel.useThatDeleteItem(item);
            }
        }
    }
}
