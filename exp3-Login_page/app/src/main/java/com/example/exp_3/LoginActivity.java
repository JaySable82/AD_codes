package com.example.exp_3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText username, password;
    Button login;

    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = findViewById(R.id.username1);
        password = findViewById(R.id.password1);

        login = findViewById(R.id.btnsignin1);

        DB = new DBHelper(this);

        login.setOnClickListener(view -> {

            String user = username.getText().toString();
            String pass = password.getText().toString();

            Boolean check = DB.checkUsernamePassword(user, pass);

            if(check) {

                Toast.makeText(LoginActivity.this,
                        "Login Successful",
                        Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(
                        LoginActivity.this,
                        HomeActivity.class);

                startActivity(intent);

            } else {

                Toast.makeText(LoginActivity.this,
                        "Invalid Credentials",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
}