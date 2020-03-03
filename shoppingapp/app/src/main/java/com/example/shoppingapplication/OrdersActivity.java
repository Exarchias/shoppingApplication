package com.example.shoppingapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.sql.ResultSet;
import java.sql.Statement;

public class OrdersActivity extends AppCompatActivity {
    private ListView listOrders;

    public TextView orderTotalPrice;
    public ImageView removeButton;
    public ConstraintLayout ordersActivity;
    public int itemID[], itemNumPics[], orderID[];
    public double orderTotal = 0.0;
    public String userPaid[];
    public ImageView closeButton;


    User user;
    DataHolder holder = new DataHolder();
    Item item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);


        listOrders = (ListView) findViewById(R.id.listview);
        ordersActivity = (ConstraintLayout) findViewById(R.id.OrdersActivity);
        closeButton = (ImageView) findViewById(R.id.closeButton);
        orderTotalPrice = (TextView) findViewById(R.id.orderTotalPrice);

        int a = countOrderDatabase();
        itemID = new int[a];
        itemNumPics = new int[a];
        orderID = new int[a];
        userPaid = new String[a];


        fetchOrders();
        for(int i=0; i<itemID.length; i++) {
            fetchItemsInfo(itemID[i],itemNumPics[i],orderID[i]);
        }
        orderTotalPrice.setText(""+orderTotal);
    }
// this method should fetch all itemsinformation
    public void fetchItemsInfo(int itemID, int itemNumPics, int orderID)  {

            item.setId(itemID);
            item.setSizeInt(itemNumPics);
            // need orderid

            // i need to select from itemlist where itemid == itemID

            // there is no itemprice
           // orderTotal = orderTotal + (itemNumPics * item.Price);

            DataHolder.itemPopulate().get(itemID);



    }


    public int countOrderDatabase() {
        int a = 0;


        DataHolder.itemIdExists(user.getId());

        // if Item populate gets all the orders in a list then this should work.
        DataHolder.itemPopulate();
        a++;


        return a;

    }

    // this method fetches ordertetials usch as username and orderid. from database.

    public void fetchOrdersDetail(String userName, int orderID, int index) {
        int i = 0;

        // i want to select from a username which matches an order id.
        DataHolder.itemPopulate();


        itemID[i] = item.getId();

        // i dont know whether getownerrid is about orders or not.
        itemNumPics[i] = item.getOwnersId();

    }


/// this one fetches all orders.
    public void fetchOrders() {
        int i = 0;

        // i want to select from the shooping table by userid.
        DataHolder.noteIdExists(user.getId());
        DataHolder.notePopulate();
                     // there should be an orderid
        orderID[i] = item.getId();

        userPaid[i] = user.getName();

        i++;
        for (int j = 0; j < i; j++) {
            fetchOrdersDetail(userPaid[j], orderID[j], j);
        }
    }
}
