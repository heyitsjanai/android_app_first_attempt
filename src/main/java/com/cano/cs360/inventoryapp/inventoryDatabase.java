package com.cano.cs360.inventoryapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;

public class inventoryDatabase extends SQLiteOpenHelper {

    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "inventory.db";
    private static inventoryDatabase instance;
    private inventoryDatabase(Context context) { super(context, DATABASE_NAME, null, VERSION); }

    public static inventoryDatabase getInstance(Context context) {
        if (instance == null) {
            instance = new inventoryDatabase(context);
        }

        return instance;
    }

    private static final class inventoryTable {
        private static final String TABLE = "inventory";

        private static final String COL_ID = "_id";

        private static final String COL_NAME = "name";

        private static final String COL_TOTAL = "amount";
    }


    private static final class loginTable {
        private static final String TABLE = "users";

        private static final String COL_USER = "username";

        private static final String COL_PASSWORD = "password";
    }

    @Override
    public void onCreate(@NonNull SQLiteDatabase db) {
        db.execSQL("create table " + inventoryTable.TABLE + "  (" +
                inventoryTable.COL_ID + " integer primary key autoincrement, " +
                inventoryTable.COL_NAME + " text, " +
                inventoryTable.COL_TOTAL + " int)");

        db.execSQL("create table " + loginTable.TABLE + " (" +
                loginTable.COL_USER + " text, " +
                loginTable.COL_PASSWORD + " text)");
    }

}
