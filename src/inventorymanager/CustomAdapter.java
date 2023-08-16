package com.cano.cs360.inventorymanager;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

/*
This class gets information from the inventory database &
puts them into the item row view holder. Used for each item
in the inventory database.
@author Janai Cano
@course SNHU CS 360
@date 8/12/23
 */

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {
    private final Context context;
    Activity activity;
    private final ArrayList id;
    private final ArrayList name;
    private final ArrayList quantity;

    CustomAdapter(Activity activity, Context context, ArrayList id, ArrayList name, ArrayList quantity) {
        this.activity = activity;
        this.context = context;
        this.id = id;
        this.name = name;
        this.quantity = quantity;
    }
    //getting the item row layout view
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_row, parent, false);
        return new MyViewHolder(view);
    }

    //gets the data from the arraylists
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.name_txt.setText(String.valueOf(name.get(position)));
        holder.quantity_txt.setText(String.valueOf(quantity.get(position)));

        //setting on click listener for edit item button
        //putting old data into intent, starting update item activity
        holder.edit_button.setOnClickListener(view -> {
            Intent intent = new Intent(context, UpdateItemActivity.class);
            intent.putExtra("name", String.valueOf(name.get(position)));
            intent.putExtra("quantity", String.valueOf(quantity.get(position)));
            activity.startActivityForResult(intent, 1);
        });
        //setting on click listener for delete item button
        //on delete click, dialog box will appear asking if user really wants to delete item
        holder.delete_button.setOnClickListener(view -> {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Delete " + name + " ?");
                builder.setMessage("Are you sure you want to delete " + name + " ?");
            //if user clicks confirm delete, item will be deleted
            builder.setPositiveButton("Yes", (dialogInterface, i) -> {
                ItemDatabaseHelper inventoryDatabase = new ItemDatabaseHelper(context);
                inventoryDatabase.deleteItem(String.valueOf(id.get(position)));
                activity.finish();
            });
                // if user clicks no, nothing happens
                builder.setNegativeButton("No", (dialogInterface, i) -> {
                    //nothing happens if they click no
                });
                builder.create().show();
            });
    }

    //returns the size of the inventory
    @Override
    public int getItemCount() {
        return id.size();
    }

    //initializes the text view items in the item row
    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView name_txt, quantity_txt;
        Button delete_button;
        Button edit_button;
        RelativeLayout itemRow;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name_txt = itemView.findViewById(R.id.item_name);
            quantity_txt = itemView.findViewById(R.id.item_quantity);
            delete_button = itemView.findViewById(R.id.delete_item);
            edit_button = itemView.findViewById(R.id.edit_item);
            itemRow = itemView.findViewById(R.id.itemRow);
        }
    }
}
