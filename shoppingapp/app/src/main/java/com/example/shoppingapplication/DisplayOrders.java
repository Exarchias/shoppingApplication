package com.example.shoppingapplication;


import android.app.Application;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;



// this class is to display the order
public class DisplayOrders {
    public String orderTitle;
    int itemID, orderID, orderNumberSelected;
    double orderPrice;
    public Drawable orderIcon;
    public String orderIconSTR;

    public DisplayOrders(int itemID, String orderTitle, int orderNumberSelected, double orderPrice, int orderID, String itemIcon) {
        super();
        this.itemID = itemID;
        this.orderID = orderID;
        this.orderTitle = orderTitle;
        this.orderPrice = orderPrice;
        this.orderNumberSelected = orderNumberSelected;
        //Robert: I see the problem. You need it Drawable. I will try to do a conversion.
        //this does a "seek" to raw data. too dangerous to have it that way.
        //this.orderIcon = new BitmapDrawable(BitmapFactory.decodeByteArray(itemIcon, 0, itemIcon.length));

        //Robert: theoretically that works. Of course we will have to see it in action.
        this.orderIconSTR = itemIcon;
        //this.orderIcon = Drawable.createFromPath("R.drawable." + orderIconSTR);
        //Robert my problem was that giving a static path was an awful idea.
        //theoretically this gives a concrete solution if it works properly.
        this.orderIcon = getDrawable(orderIconSTR);
    }

    public static Drawable getDrawable(String name) {
        Context context = new Application(); //if error occurs it will be probably here.
        Resources resources = context.getResources();
        final int resourceId = resources.getIdentifier(name, "drawable",
                context.getPackageName());
        return resources.getDrawable(resourceId);
    }


}

