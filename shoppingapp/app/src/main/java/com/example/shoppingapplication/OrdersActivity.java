package com.example.shoppingapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;


//REMEMBER: This activity does not manipulate data. it displays data.
//it is not wise to add or even worse to remove orders from the place we display them.
//We add an order when we purchase something, and we never delete them.
//It makes sense, right? :)
public class OrdersActivity extends AppCompatActivity {
    private ListView listOrders;

    public TextView orderTotalPrice;
    public ImageView removeButton;
    public ConstraintLayout ordersActivity;
    //Robert: I use other data structure. ArrayList. Morespecifically the DataHolder.arrayAllItems
    //public int itemID[], itemNumPics[], orderID[];
    //You can access DataHolder.arrayAllItems directly because it is static but I will
    //make you here a local one to fill more comforable with it :)
    ArrayList<Item> arrayAllItems = DataHolder.arrayAllItems;

    //Robert: This is the local ArrayList that will collect all the orders of the user.
    // When the method fetchOrders() is called.
    ArrayList<Note> theOrdersOftheActiveUser = new ArrayList<>();

    //Variables for the item in focus
    String title;
    String description;
    int ownnerId;
    String ownerName;
    int itemID;
    int orderID;
    double price = 0.0;

    //variables for the order in focus
    int orderOwnerID; //The owner of the order. The one who made the purchese
    String orderOwnerName = "John Doe";
    String orderTitle = "a random order";
    String orderDescriptionl = "This is the description of an order";
    String orderDateAsString = "DD/MM/YYYY";
    int numberOfItemsInTheOrder = 0;
    //An arrayList with all the items of the order in focus
    ArrayList<Item>theItemsofTheActiveOrder = new ArrayList<>();
    double orderTotal = 0.0;
    //public String userPaid[];

    //other variables and declarations
    public ImageView closeButton;


    //User user;
    //DataHolder is a static class. No need to instantiate it :)
    //DataHolder holder = new DataHolder();
    //Item item;

    //We probably mean "inFocus"
    User userInFocus; //The selected user. Probably the DataHolder.activeUser
    Item itemInfccus; //the selected item
    Note orderInFocus; //the selected order


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);


        listOrders = (ListView) findViewById(R.id.listview);
        ordersActivity = (ConstraintLayout) findViewById(R.id.OrdersActivity);
        closeButton = (ImageView) findViewById(R.id.closeButton);
        orderTotalPrice = (TextView) findViewById(R.id.orderTotalPrice);

        //Robert:lets do things differently :)
//        int a = countOrderDatabase();
//        itemID = new int[a];
//        itemNumPics = new int[a];
//        orderID = new int[a];
//        userPaid = new String[a];






//        fetchOrders();
//        for(int i=0; i<itemID.length; i++) {
//            fetchItemsInfo(itemID[i],itemNumPics[i],orderID[i]);
//        }
//        orderTotalPrice.setText(""+orderTotal);


    }
//// this method should fetch all itemsinformation
////    public void fetchItemsInfo(int itemID, int itemNumPics, int orderID)  {
////
////            item.setId(itemID);
////            item.setSizeInt(itemNumPics);
////            // need orderid
////
////            // i need to select from itemlist where itemid == itemID
////
////            // there is no itemprice
////           // orderTotal = orderTotal + (itemNumPics * item.Price);
////
////
////            //DataHolder.itemPopulate().get(itemID); //please don't use that. it will not help.
////        //it is a loader for the data. not the ArrayList
////    }

    // this method fetches all the details of the item in focus.
    public void fetchItemsInfo(Item item)  {

        itemID = item.getId();
        ownerName = RTools.findUserNameById(item.getOwnersId());
        // need orderid
        orderID = item.getNoteId(); //note is the order
        price = item.getPrice();
        title = item.getTitle();
        description = item.getDescription();
        price = item.getPrice();
    }

//Robert: it doesn't work, and it is redundant. I understand what you are trying to do, so we will
    //do something different.
//    public int countOrderDatabase() {
//        int a = 0;
//
//
//        //Robert: please don't use that. it is a check. it returns value that needs to be assigned somewhere
//        //DataHolder.itemIdExists(user.getId());
//
//        // if Item populate gets all the orders in a list then this should work.
//        //DataHolder.itemPopulate();
//        //a++;
//
//
//        //return a;
//    }

    // this method fetches ordertetials usch as username and orderid. from database.
//    public void fetchOrdersDetail(String userName, int orderID, int index) {
//        int i = 0;
//        // i want to select from a username which matches an order id.
//        DataHolder.itemPopulate();
//        itemID[i] = item.getId();
//        // i dont know whether getownerrid is about orders or not.
//        itemNumPics[i] = item.getOwnersId();
//    }

    //Fetches the deatails from the order in focus.
    public void fetchOrdersDetail(Note order){
        ArrayList<Item>tmpItemArr = fetchTheItemsOfTheOrder(order);
        orderID = order.getId();
        orderTitle = order.getDescription();
        orderDateAsString = order.getDateString();
        orderTotal = 0.0;
        orderOwnerID = order.getUserId();
        orderOwnerName = RTools.findUserNameById(orderOwnerID);
        numberOfItemsInTheOrder = tmpItemArr.size();
        for(Item item: tmpItemArr){
            orderTotal = orderTotal + item.getPrice();
        }
    }

    //it fetches all the items that belong to the Order and returns them as an arraylist
    public ArrayList<Item> fetchTheItemsOfTheOrder(Note order){
        ArrayList<Item>theItemsofTheOrder = new ArrayList<>();
        for(Item item : DataHolder.arrayAllItems){
            theItemsofTheOrder.add(item);
        }
        return theItemsofTheOrder;
    }

//I made a new versio of this method. see below.
///// this one fetches all orders.
//    public void fetchOrders() {
//        int i = 0;
//
//        // i want to select from the shooping table by userid.
//        DataHolder.noteIdExists(user.getId());
//        DataHolder.notePopulate();
//                     // there should be an orderid
//        orderID[i] = item.getId();
//
//        userPaid[i] = user.getName();
//
//        i++;
//        for (int j = 0; j < i; j++) {
//            fetchOrdersDetail(userPaid[j], orderID[j], j);
//        }
//    }


    //fetches all the orders to the local ArrayList "theOrdersOftheActiveUser"
    public void fetchOrders(int userID){
        if(DataHolder.userIdExists(userID)){
            theOrdersOftheActiveUser = RTools.theOrdersOfTheUser(RTools.findUserById(userID));
        }
    }

}