package com.example.shoppingapplication;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "note_table")

//Robert: This is clearly for testing. Testing and the actual code should not be together.
public class Note {

    //Robert: NEVER use the primary key in the constructor.
    @PrimaryKey
    private int id;

    private String title;

    private String description;

    private int priority = 0;
    private int userId;
    private String dateString = "DD/MM/YYYY";

    @Ignore
    private Date dateDate;

    public Note(int id, String title, String description, int userId) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.userId = userId;
    }

    //Thise Constructor will be used when MySQL will be introduced to the system.
    @Ignore
    public Note(int id, String title, String description, int priority, int userId, String dateString) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.userId = userId;
        this.dateString = dateString;
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

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getDateString() {
        return dateString;
    }

    public void setDateString(String dateString) {
        this.dateString = dateString;
    }

    public Date getDateDate() {
        return dateDate;
    }

    public void setDateDate(Date dateDate) {
        this.dateDate = dateDate;
    }
}
