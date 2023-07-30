package com.cano.cs360.inventoryapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView textGreeting;
    EditText nameText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //finding views by ID
        textGreeting = findViewById(R.id.textGreeting);
        nameText = findViewById(R.id.nameText);

        //finding button by ID
        Button button = findViewById(R.id.buttonSayHello);

        //dynamic enable/disable button
        button.setOnClickListener(this::SayHello);
         nameText.addTextChangedListener(new TextWatcher() {
             @Override
             public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

             }
             @Override
             public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                 button.setEnabled(!charSequence.toString().trim().isEmpty());
             }

             @Override
             public void afterTextChanged(Editable editable) {

             }
         });

    }

    //SayHello function
    public void SayHello(@NonNull View view) {
        //make sure nameText is not null
        String name = nameText.getText().toString().trim();
        if(!name.isEmpty()) {
            //if not null, display "Hello"
            String greetingMessage = "Hello " + name;
            textGreeting.setText(greetingMessage);
        }
        else {
            //if null
            textGreeting.setText("You must enter a name");
        }
    }
}