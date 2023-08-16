package com.cano.cs360.inventorymanager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

/*
This is the first screen a user sees after logging in.
User can view inventory, add a new item to inventory, or
enable SMS notifications from clicking on the three provided buttons.
@author Janai Cano
@course SNHU CS 360
@date 8/8/23
 */
public class WelcomeActivity extends AppCompatActivity {

    Activity activity;
    Button viewInventory;
    Button addItem;
    Button enableNotifications;
    ItemDatabaseHelper inventoryDatabase;
    private static final int USER_PERMISSIONS_REQUEST_SEND_SMS = 0;


    /*
    On create method initiates the inventory database & all buttons & text views.
    Adds on click listeners to view inventory, add item, and enable notifications buttons.
    View Inventory button takes user to ViewInventoryActivity.
    Add Item button takes user to NewItemActivity.
    Enable Notifications button displays a permission request dialog. Once enabled, a toast
    message will pop up telling user they have already enabled permissions.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        activity = this;

        inventoryDatabase = new ItemDatabaseHelper(this);
        viewInventory = findViewById(R.id.viewInventoryButton);
        addItem = findViewById(R.id.addItemButton);
        enableNotifications = findViewById(R.id.enableNotifications);

        viewInventory.setOnClickListener(view -> {
            Intent intent = new Intent(this, ViewInventoryActivity.class);
            startActivity(intent);
        });

        addItem.setOnClickListener(view -> {
            Intent intent = new Intent(this, NewItemActivity.class);
            startActivity(intent);
        });

        enableNotifications.setOnClickListener(view -> checkPermission());
    }

    /*
    This function checks the SMS permissions in the manifest. Default is set to disabled.
    Once user enables notifications, once an item quantity falls to zero, the user will be notified.
    I did not implement this on this version - that is a TODO.
    Once enabled, if a user clicks to enable notifications, a toast message appears telling them
    they already have sms notifications enabled.
     */
    private void checkPermission() {
        if (ContextCompat.checkSelfPermission(WelcomeActivity.this, Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_DENIED) {
            // Requesting the permission
            ActivityCompat.requestPermissions(WelcomeActivity.this, new String[]{Manifest.permission.SEND_SMS}, WelcomeActivity.USER_PERMISSIONS_REQUEST_SEND_SMS);
        } else {
            Toast.makeText(WelcomeActivity.this, "Permission already granted", Toast.LENGTH_SHORT).show();
        }
    }
}

