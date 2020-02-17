package com.example.shoppingapplication;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "item_table")
public class Item{

    //Robert: NEVER use the primary key in the constructor.
    @PrimaryKey(autoGenerate = true)
    private int id;

    private String title;

    private String description;


    //Robert: This is a Constructor. you can have more constructors.
    // NEVER add the primary key, (id), in a constructor
    public Item(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}