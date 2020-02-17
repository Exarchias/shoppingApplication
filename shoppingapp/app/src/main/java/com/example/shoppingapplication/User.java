package com.example.shoppingapplication;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "user_table")
public class User {

    //Robert: NEVER use the primary key in the constructor.
    @PrimaryKey(autoGenerate = true)
    private int id;

    private String name;
    private boolean isAdmin;


    //Robert: This is a Constructor. you can have more constructors. NEVER add the primary key, (id),
    // in a constructor
    public User(String name, boolean isAdmin) {
        this.name = name;
        this.isAdmin = isAdmin;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }
}
