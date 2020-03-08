package com.example.shoppingapplication;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.List;

// this class we create an array of orders
public class DisplayOrderAdapter extends ArrayAdapter<DisplayOrders>{
    private ImageView orderIcon;
    private TextView orderTitle,OrderNumberSelected, itemID, orderID;
    private List<DisplayOrders> ordersList = new ArrayList<>();
    LinearLayout ordersDetail;

    public DisplayOrderAdapter(Context context, int textViewResourceId) {
        super(context,textViewResourceId);
    }
    public void add(DisplayOrders obj) {
        ordersList.add(obj);
        super.add(obj);
    }
    public int getCount() {
        return this.ordersList.size();
    }
    public DisplayOrders getItem(int index) {
        return this.ordersList.get(index);
    }
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public View getView(final int position, View ConvertView, final ViewGroup parent) {
        View v = ConvertView;
        if(v == null) {
            LayoutInflater inflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.orders_display_area, parent, false);
        }
        orderTitle   = (TextView) v.findViewById(R.id.orderTitle);
        orderIcon    = (ImageView) v.findViewById(R.id.orderIcon);
       OrderNumberSelected  = (TextView) v.findViewById(R.id.orderNumPics);
        ordersDetail = (LinearLayout) v.findViewById(R.id.ordersDetail);
        itemID       = (TextView) v.findViewById(R.id.itemID);
        orderID      = (TextView) v.findViewById(R.id.orderID);

        final DisplayOrders disObj = getItem(position);
        orderTitle.setText(disObj.orderTitle);
        orderIcon.setBackground(disObj.orderIcon);
        OrderNumberSelected.setText(disObj.orderNumberSelected+"pcs");
        itemID.setText(""+disObj.itemID);
        orderID.setText(""+disObj.orderID);

        return v;
    }
}

