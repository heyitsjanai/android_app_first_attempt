package com.cano.cs360.inventorymanager;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/** @noinspection resource*/
public class UpdateItemActivity extends AppCompatActivity {

    EditText name_input, quantity_input;
    Button update;
    String id, name, quantity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_item);

        name_input = findViewById(R.id.item_name2);
        quantity_input = findViewById(R.id.item_quantity2);
        update = findViewById(R.id.updateItemButton);

        //getting old item name & quantity from intent
        getIntentData();

        //setting an actionbar title
        ActionBar ab = getSupportActionBar();
        if (ab == null) {
            throw new AssertionError();
        }
        ab.setTitle(name);

        //setting on click listener for the update button
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ItemDatabaseHelper inventoryDatabase = new ItemDatabaseHelper(UpdateItemActivity.this);
                //Then we can call the update method using the old item data
                inventoryDatabase.updateItem(id, name, quantity);
            }
        });
    }

    //filling edit text boxes with old data before the user updates it
    void getIntentData() {
        if(getIntent().hasExtra("id") && getIntent().hasExtra("name") && getIntent().hasExtra("quantity")) {
            //getting data
            id = getIntent().getStringExtra("id");
            name = getIntent().getStringExtra("name");
            quantity = getIntent().getStringExtra("quantity");

            //setting data
            name_input.setText(name);
            quantity_input.setText(quantity);
        }
        else {
            Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show();
        }
    }
}