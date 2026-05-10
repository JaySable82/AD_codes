package com.example.exp_4;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText name;
    Spinner spinner;
    RadioGroup radioGroup;
    CheckBox graduate, postgraduate, phd;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = findViewById(R.id.name);
        spinner = findViewById(R.id.spinner);
        radioGroup = findViewById(R.id.radioGroup);
        graduate = findViewById(R.id.graduate);
        postgraduate = findViewById(R.id.postgraduate);
        phd = findViewById(R.id.phd);
        button = findViewById(R.id.button);

        String subjects[] = {"Computer", "Maths", "Physics"};

        ArrayAdapter adapter = new ArrayAdapter(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                subjects
        );

        spinner.setAdapter(adapter);

        button.setOnClickListener(v -> {

            String n = name.getText().toString();
            String subject = spinner.getSelectedItem().toString();

            int id = radioGroup.getCheckedRadioButtonId();
            RadioButton rb = findViewById(id);

            String gender = rb.getText().toString();

            String q = "";

            if (graduate.isChecked())
                q += "Graduate ";

            if (postgraduate.isChecked())
                q += "Post Graduate ";

            if (phd.isChecked())
                q += "PhD";

            Intent i = new Intent(MainActivity.this, ShowActivity.class);

            i.putExtra("data",
                    "Name: " + n +
                            "\nSubject: " + subject +
                            "\nGender: " + gender +
                            "\nQualification: " + q);

            startActivity(i);

        });
    }
}