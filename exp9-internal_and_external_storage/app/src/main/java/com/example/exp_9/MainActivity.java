package com.example.exp_9;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.*;

import java.io.*;

public class MainActivity extends AppCompatActivity {

    EditText fileName, dataText;
    RadioButton internalRadio, externalRadio;
    Button saveBtn, loadBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fileName = findViewById(R.id.fileName);
        dataText = findViewById(R.id.dataText);

        internalRadio = findViewById(R.id.internalRadio);
        externalRadio = findViewById(R.id.externalRadio);

        saveBtn = findViewById(R.id.saveBtn);
        loadBtn = findViewById(R.id.loadBtn);

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData();
            }
        });

        loadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadData();
            }
        });
    }

    void saveData() {

        String fname = fileName.getText().toString();
        String data = dataText.getText().toString();

        try {

            if (internalRadio.isChecked()) {

                FileOutputStream fos = openFileOutput(fname, MODE_PRIVATE);
                fos.write(data.getBytes());
                fos.close();

                Toast.makeText(this, "Saved Internal", Toast.LENGTH_SHORT).show();
            }

            else if (externalRadio.isChecked()) {

                File file = new File(getExternalFilesDir(null), fname);

                FileOutputStream fos = new FileOutputStream(file);
                fos.write(data.getBytes());
                fos.close();

                Toast.makeText(this, "Saved External", Toast.LENGTH_SHORT).show();
            }

        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    void loadData() {

        String fname = fileName.getText().toString();

        try {

            BufferedReader br;

            if (internalRadio.isChecked()) {

                FileInputStream fis = openFileInput(fname);
                br = new BufferedReader(new InputStreamReader(fis));
            }

            else {

                File file = new File(getExternalFilesDir(null), fname);
                br = new BufferedReader(new FileReader(file));
            }

            String line;
            String data = "";

            while ((line = br.readLine()) != null) {
                data += line;
            }

            dataText.setText(data);

            Toast.makeText(this, "Loaded", Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}