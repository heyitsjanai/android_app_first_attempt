package com.cano.cs360.inventorymanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import com.cano.cs360.inventorymanager.databinding.ActivityNewUserBinding;

/*
New User screen. Prompts user to enter email, password, and confirm password.
Adds the credentials to the user database.
Upon successful registration, user is taken back to login screen.
@author Janai Cano
@course SNHU CS 360
@date 8/13/23
 */
public class NewUserActivity extends AppCompatActivity {

    ActivityNewUserBinding binding;
    UserDatabaseHelper userDatabaseHelper;

    /*
    On create method creates a click listener for register button, gets user inputs
    for email & password. If values are null, prompts the user to fill out all fields.
    As long as not null, verifies that the email is not already in use. Saves email &
    password to the user database.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityNewUserBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        userDatabaseHelper = new UserDatabaseHelper(this);

        binding.registerButton.setOnClickListener(view -> {
            String email = binding.emailInput.getText().toString();
            String password = binding.passwordInput.getText().toString();
            String confirmPassword = binding.confirmPassword.getText().toString();

            if (email.equals("") || password.equals("") || confirmPassword.equals("")) {
                Toast.makeText(NewUserActivity.this, "Must enter email and password", Toast.LENGTH_LONG).show();
            }
            else {
                if (password.equals(confirmPassword)) {
                    Boolean checkUserEmail = userDatabaseHelper.checkEmail(email);
                    //makes sure email is not in database, adds user to database if not already there
                    if (!checkUserEmail) {
                        Boolean insert = userDatabaseHelper.insertData(email, password);
                        //if user is successfully added to database, tells the user & takes back to login screen
                        if (insert) {
                            Toast.makeText(NewUserActivity.this, "User Added Successfully", Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                            startActivity(intent);
                        }
                        //if user is not added for whatever reason, prompts the user to try again
                        else {
                            Toast.makeText(NewUserActivity.this, "User Not Added. Try Again", Toast.LENGTH_LONG).show();
                        }
                    }
                    //if email is already in database, prompts the user to login
                    else {
                        Toast.makeText(NewUserActivity.this, "User already exists, please login", Toast.LENGTH_LONG).show();
                    }
                }
                // if the password & confirm password do NOT match, prompts user that it is a mismatch
                else {
                    Toast.makeText(NewUserActivity.this, "Passwords must match", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}