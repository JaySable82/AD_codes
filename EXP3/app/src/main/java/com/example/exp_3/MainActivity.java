package com.example.exp_3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText username, password, repassword;
    Button signup, signin;

    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        repassword = findViewById(R.id.repassword);

        signup = findViewById(R.id.btnsignup);
        signin = findViewById(R.id.btnsignin);

        DB = new DBHelper(this);

        signup.setOnClickListener(view -> {

            String user = username.getText().toString();
            String pass = password.getText().toString();
            String repass = repassword.getText().toString();

            if(user.equals("") || pass.equals("") || repass.equals("")) {

                Toast.makeText(MainActivity.this,
                        "Enter all fields",
                        Toast.LENGTH_SHORT).show();

            }
            else if(!pass.equals(repass)) {

                Toast.makeText(MainActivity.this,
                        "Passwords not matching",
                        Toast.LENGTH_SHORT).show();

            }
            else {

                Boolean checkUser = DB.checkUsername(user);

                if(!checkUser) {

                    Boolean insert = DB.insertData(user, pass);

                    if(insert) {

                        Toast.makeText(MainActivity.this,
                                "Registered Successfully",
                                Toast.LENGTH_SHORT).show();

                    }

                } else {

                    Toast.makeText(MainActivity.this,
                            "User already exists",
                            Toast.LENGTH_SHORT).show();

                }
            }
        });

        signin.setOnClickListener(view -> {

            Intent intent = new Intent(
                    MainActivity.this,
                    LoginActivity.class);

            startActivity(intent);
        });
    }
}