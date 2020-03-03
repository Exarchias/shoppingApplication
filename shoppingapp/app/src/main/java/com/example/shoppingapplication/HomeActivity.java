package com.example.shoppingapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    User user;
    private ListView listItems, listItemsTwo;
    private ImageView toogleBar, viewCart;
    ScrollView scrollView;
    private DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        if (user.isAdmin()) {
            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
        } else {
            setContentView(R.layout.activity_home);



            setActivityLisener();
            getAllViews();
            viewOnClickListeners();
        }
        // when you log in you should end up in this activity-abdul
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
            //startActivity(new Intent(this, OrdersActivity.class));
        } else if (id == R.id.navProfile) {
            startActivity(new Intent(this, ProfileActivity.class));
        } else if (id == R.id.navNew) {
            // startActivity(new Intent(this, AddItem.class));
        } else if (id == R.id.navConfirm) {
            //startActivity(new Intent(this, CheckoutActivity.class));

        } else if (id == R.id.navSignOut) {

        }
        return true;

    }


        @Override
        public void onPointerCaptureChanged ( boolean hasCapture){

        }
    }

