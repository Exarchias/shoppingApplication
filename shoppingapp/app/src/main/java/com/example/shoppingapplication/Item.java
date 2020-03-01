package com.example.shoppingapplication;


import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "item_table")
public class Item{

    //Robert: NEVER use the primary key in the constructor.
    @PrimaryKey
    private int id;
    private int ownersId = 10000;
    private boolean sold = false;
    private int noteId = 0;

    private String title = "Item";
    private String type = "generic";
    private String sizeSTR = "basic";
    private int sizeInt = 0;
    private String material = "unknown";
    private String colour = "random";

    private String description = "A very useful item";
    private String photo1 = "photoname";
    private String photo2 = "photoname";
    private String photo3 = "photoname";
    private String photo4 = "photoname";
    private String photo5 = "photoname";
    private String photo6 = "photoname";



    //Robert: This is a Constructor. you can have more constructors.
    // NEVER add the primary key, (id), in a constructor
    public Item(int id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
    }


    //Robert: This is a Constructor. you can have more constructors.
    // NEVER add the primary key, (id), in a constructor
    //This here is a basic constructor that includes the owner's Id
    @Ignore
    public Item(int id, String title, String description, int ownersId) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.ownersId = ownersId;
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

    public int getOwnersId() {
        return ownersId;
    }

    public void setOwnersId(int ownersId) {
        this.ownersId = ownersId;
    }

    public boolean isSold() {
        return sold;
    }

    public void setSold(boolean sold) {
        this.sold = sold;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSizeSTR() {
        return sizeSTR;
    }

    public void setSizeSTR(String sizeSTR) {
        this.sizeSTR = sizeSTR;
    }

    public int getSizeInt() {
        return sizeInt;
    }

    public void setSizeInt(int sizeInt) {
        this.sizeInt = sizeInt;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public String getPhoto1() {
        return photo1;
    }

    public void setPhoto1(String photo1) {
        this.photo1 = photo1;
    }

    public String getPhoto2() {
        return photo2;
    }

    public void setPhoto2(String photo2) {
        this.photo2 = photo2;
    }

    public String getPhoto3() {
        return photo3;
    }

    public void setPhoto3(String photo3) {
        this.photo3 = photo3;
    }

    public String getPhoto4() {
        return photo4;
    }

    public void setPhoto4(String photo4) {
        this.photo4 = photo4;
    }

    public String getPhoto5() {
        return photo5;
    }

    public void setPhoto5(String photo5) {
        this.photo5 = photo5;
    }

    public String getPhoto6() {
        return photo6;
    }

    public void setPhoto6(String photo6) {
        this.photo6 = photo6;
    }

    public int getNoteId() {
        return noteId;
    }

    public void setNoteId(int noteId) {
        this.noteId = noteId;
    }
}