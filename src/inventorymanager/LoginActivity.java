package com.cano.cs360.inventorymanager;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.cano.cs360.inventorymanager.databinding.ActivityLoginBinding;

/*
This is the first screen shown when the app launches.
Allows users to login, or create a new login.
Links to the user database to check credentials.
@author Janai Cano
@course SNHU CS 360
@date 8/8/23
 */
public class LoginActivity extends AppCompatActivity {
    ActivityLoginBinding binding;
    UserDatabaseHelper userDatabaseHelper;

    /*
    on create method that creates an on click listener for the login button.
    This gets email and password input from user, makes sure the inputs are not null.
    If they are empty, Toast messages are used to prompt user to try again.
    if not empty, checks the email & password against the user database.
    If user info is NOT correct, prompts user to retry login
    If user info is correct, allows login, takes user to the Welcome Screen
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        userDatabaseHelper = new UserDatabaseHelper(this);

        binding.loginButton.setOnClickListener(view -> {
            String email = binding.emailInput.getText().toString();
            String password = binding.passwordInput.getText().toString();

            if (email.equals("") || password.equals("")) {
                Toast.makeText(LoginActivity.this, "Must enter email and password", Toast.LENGTH_LONG).show();
            }
            else {
                Boolean checkLogin = userDatabaseHelper.checkEmailPassword(email, password);

                if (checkLogin) {
                    Intent intent = new Intent(LoginActivity.this, WelcomeActivity.class);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(LoginActivity.this, "Invalid login. Try again", Toast.LENGTH_LONG).show();
                }
            }
        });

        /*
        This sets the on click listener for new user button.
        Takes the user to the New User Activity to register.
         */
        binding.newUserButton.setOnClickListener(view -> {
            Intent intent = new Intent(LoginActivity.this, NewUserActivity.class);
            startActivity(intent);
        });
    }
}