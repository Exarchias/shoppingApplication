package com.example.shoppingapplication;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.ArrayList;
import java.util.Date;

@Entity(tableName = "user_table")
public class User {

    //Robert: NEVER use the primary key in the constructor.
    @PrimaryKey(autoGenerate = true)
    private int id;

    private String name;
    private boolean isAdmin;
    private String password;
    private String email;
    private String address;
    private String postCode;
    private String city;
    private String Country;
    private String telephone;
    private String mobilePhone;
    private int userNumber; //not the same as id. this can be used in case that an id needs to be publix.
    // Better AVOID to use it if you are not having a specific plan about it.
    private int age; //it might be generated automatically, in that case the setter will be removed.
    @Ignore
    private Date loginDate;
    private String loginDateSTR;

    @Ignore
    private Date dayOfBirth;
    private String dayOfBirthSTR;

    private int wallet; //In case that a wallet feature is implemented.

    @Ignore
    public ArrayList<Item> purchasedItems; //This is for the case we need to implement history of the user.
    //this is NOT for the shopping basket.
    //Try to AVOID it to use such feature. It will add complexity to the project for everyone.



    //Robert: This is a Constructor. you can have more constructors. NEVER add the primary key, (id),
    // in a constructor
    //This is the first Constructor ever used. Hardly to have any future use.
    public User(String name, boolean isAdmin) {
        this.name = name;
        this.isAdmin = isAdmin;
    }

    @Ignore
    //Robert: This is a Constructor. you can have more constructors. NEVER add the primary key, (id),
    // in a constructor
    //this is one is a very basic one. It takes the basics for any User
    public User(String name, boolean isAdmin, String password) {
        this.name = name;
        this.isAdmin = isAdmin;
        this.password = password;
    }

    @Ignore
    //Robert: This is a Constructor. you can have more constructors. NEVER add the primary key, (id),
    // in a constructor
    //this is one is a very basic one. It takes the basics for any User
    //But it INCLUSES the email.
    public User(String name, boolean isAdmin, String password, String email) {
        this.name = name;
        this.isAdmin = isAdmin;
        this.password = password;
        this.email = email;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        Country = country;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public int getUserNumber() {
        return userNumber;
    }

    public void setUserNumber(int userNumber) {
        this.userNumber = userNumber;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Date getLoginDate() {
        return loginDate;
    }

    public void setLoginDate(Date loginDate) {
        this.loginDate = loginDate;
    }

    public Date getDayOfBirth() {
        return dayOfBirth;
    }

    public void setDayOfBirth(Date dayOfBirth) {
        this.dayOfBirth = dayOfBirth;
    }

    public int getWallet() {
        return wallet;
    }

    public void setWallet(int wallet) {
        this.wallet = wallet;
    }

    public String getLoginDateSTR() {
        return loginDateSTR;
    }

    public void setLoginDateSTR(String loginDateSTR) {
        this.loginDateSTR = loginDateSTR;
    }

    public String getDayOfBirthSTR() {
        return dayOfBirthSTR;
    }

    public void setDayOfBirthSTR(String dayOfBirthSTR) {
        this.dayOfBirthSTR = dayOfBirthSTR;
    }
}
