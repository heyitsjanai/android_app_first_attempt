package com.cano.cs360.inventorymanager;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.ArrayList;

/*
This is the view inventory activity. It makes use of a scrollable
recycler view with each individual item showing up in a row.
There is a floating action button that also allows users to add new items
from this screen. It will take the user to the NewItemActivity.
It also has a delete item button on each row for each item, as well as
a delete all button items button that allows user to delete entire inventory.
@author Janai Cano
@course SNHU CS 360
@date 8/13/23
 */
public class ViewInventoryActivity extends AppCompatActivity {

    Activity activity;
    RecyclerView recycler;
    FloatingActionButton add_button;
    ImageView empty_imageview;
    TextView empty_inventory;
    ImageButton delete_all;
    CustomAdapter customAdapter;
    ArrayList<String> id, name, quantity;
    ItemDatabaseHelper inventoryDatabase;

    /*
    On create method initializes the layout and buttons. On add button click,
    user is taken to NewItemActivity screen. If delete all button is clicked,
    an alert dialog box will pop up, asking the user to confirm that they want to
    delete entire inventory. If inventory is empty, the empty image & empty text
    will show. Otherwise, the inventory database will be loaded into each row and
    be viewable/scrollable.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_inventory);
        activity = this;

        recycler = findViewById(R.id.recyclerView);
        add_button = findViewById(R.id.add_button);
        delete_all = findViewById(R.id.delete_all);
        empty_imageview = findViewById(R.id.empty_imageview);
        empty_inventory = findViewById(R.id.empty_inventory);

        add_button.setOnClickListener(view -> {
            Intent intent = new Intent(ViewInventoryActivity.this, NewItemActivity.class);
            startActivity(intent);
        });

        delete_all.setOnClickListener(view -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(ViewInventoryActivity.this);
            builder.setTitle("Delete all items?");
            builder.setMessage("Are you sure you want to delete all items from inventory?");

            builder.setPositiveButton("Yes",(dialogInterface,i)-> {
                ItemDatabaseHelper inventoryDatabase = new ItemDatabaseHelper(ViewInventoryActivity.this);
                inventoryDatabase.deleteAllItems();
                Intent intent = new Intent(ViewInventoryActivity.this, ViewInventoryActivity.class);
                startActivity(intent);
                finish();
            });

            builder.setNegativeButton("No",(dialogInterface,i)-> {
                //nothing happens if they click no
            });

            builder.create().show();
        });

        inventoryDatabase = new ItemDatabaseHelper(ViewInventoryActivity.this);
        id = new ArrayList<>();
        name = new ArrayList<>();
        quantity = new ArrayList<>();

        storeInventoryData();

        customAdapter = new CustomAdapter(ViewInventoryActivity.this, this, id, name, quantity);
        recycler.setAdapter(customAdapter);
        recycler.setLayoutManager(new LinearLayoutManager(ViewInventoryActivity.this));

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0) {
            recreate();
        }
    }

    void storeInventoryData() {
        Cursor cursor = inventoryDatabase.getInventory();
        if (cursor.getCount() == 0) {
            empty_imageview.setVisibility(View.VISIBLE);
            empty_inventory.setVisibility(View.VISIBLE);
        }
        else {
            while (cursor.moveToNext()) {
                id.add(cursor.getString(0));
                name.add(cursor.getString(1));
                quantity.add(cursor.getString(2));
            }
            empty_imageview.setVisibility(View.GONE);
            empty_inventory.setVisibility(View.GONE);
        }
        cursor.close();
    }
}
