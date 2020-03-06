package com.example.shoppingapplication;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

@Entity(tableName = "user_table")
public class User {

    @PrimaryKey
    private int id;
    private String name; //the username of the user
    private boolean isAdmin;
    private String password;
    private String email = "example@example.com";
    private String fullName = "John Doe"; //the full name of the user
    private String address = "Example Street 21";
    private String postCode = "12345";
    private String city = "Kristianstad";
    private String Country = "Sweden";
    private String telephone = "+461234567890";
    private String mobilePhone = "+461234567890";
    private int userNumber; //not the same as id. this can be used in case that an id needs to be publix.
    // Better AVOID to use it if you are not having a specific plan about it.
    private int age = 30; //it might be generated automatically, in that case the setter will be removed.
    @Ignore
    private Date loginDate;
    private String loginDateSTR = "DD/MM/YYYY";

    @Ignore
    private Date dayOfBirth;
    private String dayOfBirthSTR = "DD/MM/YYYY";

    private int wallet = 100; //In case that a wallet feature is implemented.

    @Ignore
    public ArrayList<Item> purchasedItems; //This is for the case we need to implement history of the user.
    //this is NOT for the shopping basket.
    //Try to AVOID it to use such feature. It will add complexity to the project for everyone.



    //Robert: This is a Constructor. you can have more constructors.
    //this is one is a very basic one. It takes the basics for any User
    @Ignore
    public User(int id, String name, boolean isAdmin, String password) {
        this.id = id;
        this.name = name;
        this.isAdmin = isAdmin;
        this.password = password;
    }



    //Robert: This is a Constructor. you can have more constructors. NEVER add the primary key, (id),
    // in a constructor
    //this is one is a very basic one. It takes the basics for any User
    //But it INCLUSES the email.
//    @Ignore
//    public User(int id, String name, boolean isAdmin, String encrypted, String password, String email){
//        this.id = id;
//        this.name = name;
//        this.isAdmin = isAdmin;
//        this.password = password;
//        this.email = email;
//    }


    //Robert: Since the Login System will use the telephone number. This constructor is the one that
    //needs to be used for the register.
    public User(int id, String name, boolean isAdmin, String password, String email, String telephone){
        this.id = id;
        this.name = name;
        this.isAdmin = isAdmin;
        this.password = password;
        this.email = email;
        this.telephone = telephone;
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

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
