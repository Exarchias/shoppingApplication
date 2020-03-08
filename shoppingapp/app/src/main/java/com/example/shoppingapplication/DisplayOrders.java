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
    public String orderIcon2;

    public DisplayOrders(int itemID, String orderTitle, int orderNumberSelected, double orderPrice, int orderID, String itemIcon) {
        super();
        this.itemID = itemID;
        this.orderID = orderID;
        this.orderTitle = orderTitle;
        this.orderPrice = orderPrice;
        this.orderNumberSelected = orderNumberSelected;
        //Robert: I see the problem. You need it Drawable. I will try to do a conversion.
        //this.orderIcon = new BitmapDrawable(BitmapFactory.decodeByteArray(itemIcon, 0, itemIcon.length));
        this.orderIcon2 = itemIcon;
    }
}

