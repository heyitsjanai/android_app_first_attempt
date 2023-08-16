package com.cano.cs360.inventorymanager;

import androidx.appcompat.app.AppCompatActivity;
import android.widget.Toast;
import android.os.Bundle;
import android.content.Intent;

import com.cano.cs360.inventorymanager.databinding.ActivityNewItemBinding;

/*
New Item Screen. Prompts the user to enter item name & quantity.
Adds the item to the inventory database.
Upon successful addition, user is taken back to the inventory list view.
@author Janai Cano
@course SNHU CS 360
@date 8/12/23
 */

public class NewItemActivity extends AppCompatActivity {

    ActivityNewItemBinding binding;
    ItemDatabaseHelper inventoryDatabase;

    //implements binding to get layout
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityNewItemBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        inventoryDatabase = new ItemDatabaseHelper(this);

        //on click listener for add item button, gets user inputs
        binding.addItemButton.setOnClickListener(view -> {
            String name = binding.itemName.getText().toString();
            int quantity = Integer.parseInt(binding.itemQuantity.getText().toString());

            //makes sure inputs are not null, prompts user to fill out all fields
            if (name.equals("") || quantity < 0) {
                Toast.makeText(this, "Must fill out all fields", Toast.LENGTH_SHORT).show();
            }
            //if inputs are not null, continues to add item to inventory
            else {
                //makes sure item name is not already in inventory
                Boolean checkName = inventoryDatabase.checkItemName(name);
                //if it is not, adds item to inventory database
                if (!checkName) {
                    Boolean insert = inventoryDatabase.addItem(name, quantity);
                    // if item is successfully added, takes the user back to inventory screen
                    if (insert) {
                        Toast.makeText(this, "Item added to inventory", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(getApplicationContext(), ViewInventoryActivity.class);
                        startActivity(intent);
                    }
                    // if item not added, prompts user to try again
                    else {
                        Toast.makeText(this, "Item not added. Try again", Toast.LENGTH_LONG).show();
                    }
                }
                // if the item already exists in inventory, does not add to database
                else {
                    Toast.makeText(this, "Item already exists!", Toast.LENGTH_LONG).show();
                }
            }

        });
    }


}