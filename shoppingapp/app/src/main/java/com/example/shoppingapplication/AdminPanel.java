package com.example.shoppingapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class AdminPanel extends AppCompatActivity {
    private NoteViewModel noteViewModel;
    Button createItem;
    Button editItem;
    Button creatUser;
    Button editUser;
    Button deleteItem;
    Button deleteUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_panel);
        noteViewModel = ViewModelProviders.of(this).get(NoteViewModel.class);
        createItem=(Button)findViewById(R.id.btn_createItem);
        editItem=(Button)findViewById(R.id.btn_editItem);
        creatUser=(Button)findViewById(R.id.btn_CreateUser);
        editUser=(Button)findViewById(R.id.btn_editUser);
        deleteItem=(Button)findViewById(R.id.btn_deleteItem);
        deleteUser=(Button)findViewById(R.id.btn_deleteUser);

    }

    //for the button "Create a new Item" on admin Panel
     void adminPanelCreateAnItem(int id){
        if(DataHolder.isAdmin){
            if(DataHolder.itemIdExists(id)){
                Item item = RTools.findItemById(id);
                DataHolder.itemInFocus = item;
                // The activities need to correspond real activities. (depends on the Activity which it is in)
                Intent intent = new Intent(AdminPanel.this, CreateItemActivity.class);
                startActivity(intent);
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
                Intent intent = new Intent(AdminPanel.this, EditItemActivity.class);
                startActivity(intent);
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
                Intent intent = new Intent(AdminPanel.this, CreateUserActivity.class);
                startActivity(intent);
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
                Intent intent = new Intent(AdminPanel.this, EditUserActivity.class);
                startActivity(intent);
            }
        }
    }
    //for the button "Delete Item" on admin Panel
    void adminPanelDeleteItem(int id){
        if(DataHolder.isAdmin){
            if(DataHolder.itemIdExists(id)){
                User user = RTools.findUserById(id);
                noteViewModel.useThatDeleteUser(user);
            }
        }
    }

    //for the button "Create a new User" on admin Panel
    void adminPanelDeleteUser(int id){
        if(DataHolder.isAdmin){
            if(DataHolder.userIdExists(id)){
                Item item = RTools.findItemById(id);
                noteViewModel.useThatDeleteItem(item);
            }
        }
    }
}
