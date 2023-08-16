package com.cano.cs360.inventorymanager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

/*
This is the user database. Creates a user table with email
& password. Implements functions to authenticate user credentials
@author Janai Cano
@course SNHU CS 360
@date 8/12/23
 */

public class UserDatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Users.db";
    public static final int VERSION = 1;

    public UserDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase userDatabase) {
        userDatabase.execSQL("create Table users(email TEXT primary key, password TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase userDatabase, int i, int i1) {
        userDatabase.execSQL("drop Table if exists users");

    }

    /*
    This function adds user data into user table
    as long as it is new data. Makes sure the operation
    was successful by returning a boolean.
     */
    public Boolean insertData(String email, String password) {
        SQLiteDatabase userDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("email", email);
        contentValues.put("password", password);
        long result = userDatabase.insert("users", null, contentValues);

        return result != -1;
    }

    /*
    This function checks to make sure the new user email is not
    already in the user database. If it is, the user is prompted
    in the New User Activity that the email already exists, can't be added.
     */
    public Boolean checkEmail(String email) {
        SQLiteDatabase userDatabase = this.getWritableDatabase();
        Cursor cursor = userDatabase.rawQuery("Select * from users where email = ?", new String[]{email});

        return cursor.getCount() > 0;
    }

    /*
    This verifies that the email & password match what is in the database.
    In other words, verifies that the password is correct. If not, user is
    prompted in the login screen that it is incorrect, try again.
     */
    public Boolean checkEmailPassword(String email, String password) {
        SQLiteDatabase userDatabase = this.getWritableDatabase();
        Cursor cursor = userDatabase.rawQuery("Select * from users where email = ? and password = ?", new String[]{email, password});

        return cursor.getCount() > 0;
    }
}