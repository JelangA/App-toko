package com.example.app_toko.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.app_toko.BackgroundTasks.BackgroundTaskAuth;
import com.example.app_toko.R;

public class LoginActivity extends AppCompatActivity {

    Button btnLogin, btnRegister;
    EditText txtUsername, txtPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnLogin = (Button) findViewById(R.id.btnLogin1);
        btnRegister = (Button) findViewById(R.id.btnRegister1);

        txtUsername = (EditText) findViewById(R.id.txtEmail1);
        txtPassword = (EditText) findViewById(R.id.txtPassword1);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new BackgroundTaskAuth(getApplicationContext()).execute("login", txtUsername.getText().toString(), txtPassword.getText().toString());
            }
        });
    }
}