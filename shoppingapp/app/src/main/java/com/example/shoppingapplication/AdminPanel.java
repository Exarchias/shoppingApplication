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
    Button switchTo;
    //Robert: This code is here to stay ==============
    ListView itemsListView;
    ListView usersListView;
    RItemAdapter adapter;
    //=== Code that is here to stay ends here =============
    int value=2;
    boolean itemsActive = false;
    boolean usersActive = true;
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_panel);
        noteViewModel = ViewModelProviders.of(this).get(NoteViewModel.class);
        // if the user doesnt exist then you will be brought back to the login page
        if (DataHolder.activeUser == null) {
            Intent i = new Intent(this, LoginActivity.class);
            startActivity(i);
        }
        // if the user is not an admin the user will be brought back to the home page
        if (!DataHolder.isAdmin) {
            Intent i = new Intent(this, HomeActivity.class);
            startActivity(i);
        }
        createItem=(Button)findViewById(R.id.btn_createItem);
        editItem=(Button)findViewById(R.id.btn_editItem);
        createUser=(Button)findViewById(R.id.btn_CreateUser);
        editUser=(Button)findViewById(R.id.btn_editUser);
        deleteItem=(Button)findViewById(R.id.btn_deleteItem);
        deleteUser=(Button)findViewById(R.id.btn_deleteUser);
        switchTo=(Button)findViewById(R.id.btn_switch);
        usersListView = (ListView)findViewById(R.id.first_listView_AdminPanel);
        itemsListView = (ListView)findViewById(R.id.second_listView_AdminPanel);
        populateItemListView();
        populateUserListView();

        //Making sure that the items will be invisible at the beginning.
        createItem.setVisibility(View.GONE);
        editItem.setVisibility(View.GONE);
        deleteItem.setVisibility(View.GONE);
        itemsListView.setVisibility(View.GONE);
        //Setting the switch button to "Switch to Items"
        switchTo.setText("Switch to Items");


        switchTo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(itemsActive){
                    //Hiding the items
                    createItem.setVisibility(View.GONE);
                    editItem.setVisibility(View.GONE);
                    deleteItem.setVisibility(View.GONE);
                    itemsListView.setVisibility(View.GONE);

                    //Revealing the users
                    createUser.setVisibility(View.VISIBLE);
                    editUser.setVisibility(View.VISIBLE);
                    deleteUser.setVisibility(View.VISIBLE);
                    usersListView.setVisibility(View.VISIBLE);

                    //Adjusting the booleans
                    itemsActive = false;
                    usersActive = true;

                    //changing the text on the button
                    switchTo.setText("Switch to Items");

                } else {
                    //Hiding the users
                    createUser.setVisibility(View.GONE);
                    editUser.setVisibility(View.GONE);
                    deleteUser.setVisibility(View.GONE);
                    usersListView.setVisibility(View.GONE);

                    //Revealing the items
                    createItem.setVisibility(View.VISIBLE);
                    editItem.setVisibility(View.VISIBLE);
                    deleteItem.setVisibility(View.VISIBLE);
                    itemsListView.setVisibility(View.VISIBLE);

                    //Adjusting the booleans
                    itemsActive = true;
                    usersActive = false;

                    //changing the text on the button
                    switchTo.setText("Switch to Users");
                }

            }
        });


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
        //ArrayList<Item> arrayOfItems = new ArrayList<Item>();
        ArrayList<Item> arrayOfItems = DataHolder.arrayAllItems;
        //arrayOfItems.add(new Item(1, "Item 1", "Description 1", 2, 1));
        // Create the adapter to convert the array to views
        RItemAdapter adapter = new RItemAdapter(AdminPanel.this, arrayOfItems);
        // Attach the adapter to a ListView
        itemsListView.setAdapter(adapter);
    }

    void populateUserListView(){
        // Construct the data source
        //ArrayList<Item> arrayOfItems = new ArrayList<Item>();
        ArrayList<User> arrayOfUsers = DataHolder.arrayAllUsers;
        //arrayOfItems.add(new Item(1, "Item 1", "Description 1", 2, 1));
        // Create the adapter to convert the array to views
        RUserAdapter userAdapter = new RUserAdapter(AdminPanel.this, arrayOfUsers);
        // Attach the adapter to a ListView
        usersListView.setAdapter(userAdapter);
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
