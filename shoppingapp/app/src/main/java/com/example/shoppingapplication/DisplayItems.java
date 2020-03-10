package com.example.shoppingapplication;

import android.app.Application;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

public class DisplayItems {
    public String itemTitle, itemID;
    Double itemPrice;
    public Drawable itemIcon;
    public String ItemIconSTR;
    public DisplayItems(String itemID, String itemTitle, Double itemPrice, String itemIcon) {
        super();
        this.itemID       = itemID;
        this.itemTitle    = itemTitle;
        this.itemPrice    = itemPrice;

        this.ItemIconSTR = itemIcon;


        this.itemIcon = getDrawable(ItemIconSTR);
    }


    public static Drawable getDrawable(String name) {
        Context context = new Application(); //if error occurs it will be probably here.
        Resources resources = context.getResources();
        final int resourceId = resources.getIdentifier(name, "drawable",
                context.getPackageName());
        return resources.getDrawable(resourceId);
    }


}

