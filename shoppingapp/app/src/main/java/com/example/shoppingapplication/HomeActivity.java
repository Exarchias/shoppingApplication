package com.example.shoppingapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    User user;
    Item item;
    private ListView listItems, listItemsTwo;
    private DisplayItemAdapter itemAdapter, itemAdapterTwo;
    private ImageView toogleBar, viewCart;
    ScrollView scrollView;
    String ownerName;
    private DrawerLayout mDrawerLayout;
    public int numberofItems;
    public int itemFetchNumber,itemsPrinted=0,QueueChecker=0, itemID, orderID;
    public boolean isResultFound;
    ArrayList<Item> ListofAllItems = new ArrayList<>();
    // ==== block of working code. please do not delete ====
    ListView listViewItemsHome;
    Button toTheCart;
    Button toAdminPanel;
    Item selectedItem;
    // ==== Block of code ends here ====

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // if the user doesnt exist then you will be brought back to the mainActivity-abdul
        if (DataHolder.activeUser == null) {
            Intent i = new Intent(this, LoginActivity.class);
            startActivity(i);
        }


        setContentView(R.layout.activity_home);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        // ==== block of working code. please do not delete ====
        listViewItemsHome = (ListView)findViewById(R.id.listViewItemsHome);
        toTheCart = (Button) findViewById(R.id.toTheCart);
        toAdminPanel = (Button) findViewById(R.id.toAdminPanel);

        populateItemListView();
        if(DataHolder.isAdmin){
            toAdminPanel.setVisibility(View.VISIBLE);
        } else {
            toAdminPanel.setVisibility(View.GONE);
        }
        // ==== Block of code ends here ====
        listViewItemsHome.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedItem = DataHolder.arrayAllItems.get(position);
                String msg = "Send " + selectedItem.getTitle() + " to the cart";
                toTheCart.setText(msg);
            }
        });

        toTheCart.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(selectedItem != null){
                    DataHolder.arrayCartItems.add(selectedItem);
                    Toast.makeText(HomeActivity.this, selectedItem.getTitle() +" moved to the shopping cart",
                            Toast.LENGTH_SHORT).show();
                }

            }
        });

        toAdminPanel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(DataHolder.isAdmin){
                    Intent intent = new Intent(HomeActivity.this, AdminPanel.class);
                    HomeActivity.this.startActivity(intent);
                }

            }
        });

        setActivityLisener();
        getAllViews();
        viewOnClickListeners();
        setListViewAdpater();


        numberofItems = getNumberOfItems();



    }

    //This Method is highly functional please do not delete
    void populateItemListView(){
        // Construct the data source
        //ArrayList<Item> arrayOfItems = new ArrayList<Item>();
        ArrayList<Item> arrayOfItems = DataHolder.arrayAllItems;
        //arrayOfItems.add(new Item(1, "Item 1", "Description 1", 2, 1));
        // Create the adapter to convert the array to views
        RItemAdapter itemAdapter = new RItemAdapter(HomeActivity.this, arrayOfItems);
        // Attach the adapter to a ListView
        listViewItemsHome.setAdapter(itemAdapter);
    }

    void sendToTheCart(){
        DataHolder.arrayCartItems.add(selectedItem);
//        Toast.makeText(HomeActivity.this, selectedItem.getTitle() +" moved to the basket",
//                Toast.LENGTH_SHORT).show();
    }


    public void setListViewAdpater() {
        itemAdapter = new DisplayItemAdapter(getApplicationContext(), R.layout.item_display_area);
        setItemListProperty();
        itemAdapterTwo = new DisplayItemAdapter(getApplicationContext(), R.layout.item_display_area);
        setItemListTwoProperty();





        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(MainActivity.MODE_APPEND/ 2, LinearLayout.LayoutParams.FILL_PARENT);

        listItems.setLayoutParams(params);
        listItemsTwo.setLayoutParams(params);

}
    private boolean displayItems(String itemID, String itemTitle, Double itemPrice, String itemIcon) {
        itemAdapter.add(new DisplayItems(itemID, itemTitle, itemPrice, itemIcon));
        return true;
    }
    private boolean displayItemsTwo(String itemID, String itemTitle, Double itemPrice, String itemIcon) {
        itemAdapterTwo.add(new DisplayItems(itemID, itemTitle, itemPrice, itemIcon));
        return true;
    }


    public void setItemListProperty() {
        listItems.setAdapter(itemAdapter);
        listItems.setTranscriptMode(AbsListView.TRANSCRIPT_MODE_ALWAYS_SCROLL);
        listItems.setAdapter(itemAdapter);
        itemAdapter.registerDataSetObserver(new DataSetObserver() {
            public void onChanged() {
                super.onChanged();
                listItems.setSelection(itemAdapter.getCount() - 1);
            }
        });
    }
    public void setItemListTwoProperty() {
        listItemsTwo.setAdapter(itemAdapterTwo);
        listItemsTwo.setTranscriptMode(AbsListView.TRANSCRIPT_MODE_ALWAYS_SCROLL);
        listItemsTwo.setAdapter(itemAdapterTwo);
        itemAdapterTwo.registerDataSetObserver(new DataSetObserver() {
            public void onChanged() {
                super.onChanged();
                listItemsTwo.setSelection(itemAdapterTwo.getCount() - 1);
            }
        });
    }


    // this method is for fetching a list of all items.
    public int getNumberOfItems() {


        int numItems = 0;
        if(DataHolder.userIdExists(numItems)){
            ListofAllItems = RTools.theItemsOfTheUser(user);
            numItems = item.getId();
        }
        return numItems;
    }

    // when you log in you should end up in this activity-abdul


    public void fetchItemsInfo() {
        int curLeftItems;
        try {


            numberofItems = getNumberOfItems();

            curLeftItems = itemFetchNumber - 8;
            // i want a list of all items infromation
            itemID = item.getId();
            ownerName = RTools.findUserNameById(item.getOwnersId());
            int i = 0;
            while ( curLeftItems != itemFetchNumber) {
                isResultFound = true;
                if (i >= itemsPrinted) {
                    itemFetchNumber--;
                    if (QueueChecker == 0) {
                        displayItems("" + item.getId(),item.getTitle(), item.getPrice(),item.getPhoto1());
                        QueueChecker = 1;
                    } else {
                        displayItemsTwo("" + item.getId(), item.getTitle(), item.getPrice(), item.getPhoto1());
                        QueueChecker = 0;
                    }
                }
                i++;
            }
            itemsPrinted += 8;

        } catch (Exception ex) {

            Toast.makeText(this, "" + ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }



    public void viewOnClickListeners() {

        viewCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), OrdersActivity.class);
                startActivity(intent);
            }
        });
        toogleBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDrawerLayout.openDrawer(Gravity.LEFT);
            }
        });

        scrollView.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
            public void onScrollChanged() {
                if (scrollView.getChildAt(0).getBottom() == (scrollView.getHeight() + scrollView.getScrollY())) {
                    if ((listItems.getCount() + listItemsTwo.getCount()) < numberofItems) {
                        fetchItemsInfo();
                    }
                }
            }
        });
    }








    public void getAllViews() {
        scrollView = (ScrollView) findViewById(R.id.scrollView);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        listItems = (ListView) findViewById(R.id.listview);
        listItemsTwo = (ListView) findViewById(R.id.listview_two);
        toogleBar = (ImageView) findViewById(R.id.toogleBar);
        viewCart = (ImageView) findViewById(R.id.viewCart);
    }




  //this starts the nav view -abdul
    public void setActivityLisener() {
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }


// this method handles all the activities in the navigation.
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {


        int id = item.getItemId();

        if (id == R.id.navOrders) {
            startActivity(new Intent(this, OrdersActivity.class));
        } else if (id == R.id.navProfile) {
            startActivity(new Intent(this, ProfileActivity.class));
        } else if (id == R.id.navNew) {
            // startActivity(new Intent(this, AddItem.class));
        } else if (id == R.id.navConfirm) {
            //startActivity(new Intent(this, CheckoutActivity.class));

        } else if (id == R.id.navSignOut) {
            RTools.logout();
            Intent i = new Intent(this, LoginActivity.class);
            startActivity(i);

        }
        return true;

    }


        @Override
        public void onPointerCaptureChanged ( boolean hasCapture){

        }
    }

