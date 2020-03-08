package com.example.shoppingapplication;

import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
// this class is to display the order
public class DisplayOrders {
    public String orderTitle;
    int itemID, orderID, orderNumberSelected;
    double orderPrice;
    public Drawable orderIcon;

    public DisplayOrders(int itemID, String orderTitle, int orderNumberSelected, double orderPrice, int orderID, byte[] itemIcon) {
        super();
        this.itemID = itemID;
        this.orderID = orderID;
        this.orderTitle = orderTitle;
        this.orderPrice = orderPrice;
        this.orderNumberSelected = orderNumberSelected;
        this.orderIcon = new BitmapDrawable(BitmapFactory.decodeByteArray(itemIcon, 0, itemIcon.length));
    }
}

