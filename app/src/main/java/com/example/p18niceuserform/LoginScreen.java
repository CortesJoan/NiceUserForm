package com.example.p18niceuserform;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;

public class LoginScreen extends AppCompatActivity {
    Button loginButton;
    Button registerButton;
    TextInputLayout layoutUsername;
    TextInputLayout layoutPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);
        layoutUsername = findViewById(R.id.usernameLayout);
        layoutPassword = findViewById(R.id.passwordLayout);
        loginButton = findViewById(R.id.loginButton);
        registerButton = findViewById(R.id.registerButton);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RegisterScreen.checkIfError(layoutUsername, false, "");
                RegisterScreen.checkIfError(layoutPassword, false, "");
                if (!RegisterScreen.hasError) {
                    Intent intent = new Intent(LoginScreen.this, WelcomeScreen.class);
                    startActivity(intent);
                }
            }
        });
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(LoginScreen.this, RegisterScreen.class);
                startActivity(intent);
            }
        });
    }


}