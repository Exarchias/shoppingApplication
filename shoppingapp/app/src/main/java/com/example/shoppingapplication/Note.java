package com.example.shoppingapplication;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "note_table")

//Robert: This is clearly for testing. Testing and the actual code should not be together.
public class Note {

    //Robert: NEVER use the primary key in the constructor.
    @PrimaryKey(autoGenerate = true)
    private int id;

    private String title;

    private String description;

    private int priority;

    public Note(String title, String description, int priority) {
        this.title = title;
        this.description = description;
        this.priority = priority;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getPriority() {
        return priority;
    }
}
