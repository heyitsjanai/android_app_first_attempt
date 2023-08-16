package com.cano.cs360.inventorymanager;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;

/*
This is the inventory database. Creates an inventory table that is unique
to each user based on email. Allows CRUD operations within this inventory
database.
@author Janai Cano
@course SNHU CS 360
@date 8/12/23
 */

public class ItemDatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Items.db";
    public static final int VERSION = 1;
    private static final String TABLE_NAME = "Items";
    Context context;

    static ArrayList<String> id;
    static ArrayList<String> name;
    static ArrayList<String> quantity;




    public ItemDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase inventoryDatabase) {
        inventoryDatabase.execSQL("create Table " + TABLE_NAME + "(id int primary key autoincrement, name TEXT, quantity int)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase inventoryDatabase, int i, int i1) {
        inventoryDatabase.execSQL("drop Table if exists " + TABLE_NAME);
    }

    //function to add an item to inventory database
    public Boolean addItem(String name, int quantity) {
        SQLiteDatabase inventoryDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("name", name);
        contentValues.put("quantity", quantity);
        long result = inventoryDatabase.insert(TABLE_NAME, null, contentValues);

        return result != -1;
    }

    //function that checks inventory for a specific item name - no duplicates allowed
    public Boolean checkItemName(String name) {
        SQLiteDatabase inventoryDatabase = this.getWritableDatabase();
        Cursor cursor = inventoryDatabase.rawQuery("Select * from " + TABLE_NAME + " where name = ?", new String[]{name});

        cursor.close();
        return cursor.getCount() > 0;
    }

    //displays all inventory in the database
    Cursor getInventory() {
        SQLiteDatabase inventoryDatabase = this.getReadableDatabase();
        Cursor cursor = null;

        if (inventoryDatabase != null) {
            cursor = inventoryDatabase.rawQuery("Select * from " + TABLE_NAME, null);
        }
        return cursor;
    }

    //function to update item information
    void updateItem(String row_id, String name, String quantity) {
        SQLiteDatabase inventoryDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("name", name);
        contentValues.put("quantity", quantity);

        long result = inventoryDatabase.update(TABLE_NAME, contentValues, "id=?", new String[]{row_id});
        if (result == -1) {
            Toast.makeText(context, "Item not updated", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Successfully updated item", Toast.LENGTH_SHORT).show();
        }
    }

    //function to delete item
    void deleteItem(String row_id) {
        SQLiteDatabase inventoryDatabase = this.getWritableDatabase();
        long result = inventoryDatabase.delete(TABLE_NAME, " id=?", new String[]{row_id});
        if (result == -1) {
            Toast.makeText(context, "Did not delete item", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Deleted item", Toast.LENGTH_SHORT).show();
        }
    }


    //function to delete all inventory items
    void deleteAllItems() {
        SQLiteDatabase inventoryDatabase = this.getWritableDatabase();
        inventoryDatabase.execSQL("delete from " + TABLE_NAME);
    }
}
