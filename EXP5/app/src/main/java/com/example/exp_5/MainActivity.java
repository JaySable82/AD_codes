package com.example.exp_5;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btnDialog, btnCustom;
    ListView listView;

    String[] items = {"Item 1", "Item 2", "Item 3"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnDialog = findViewById(R.id.btnDialog);
        btnCustom = findViewById(R.id.btnCustom);
        listView = findViewById(R.id.listView);

        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(this,
                        android.R.layout.simple_list_item_1,
                        items);

        listView.setAdapter(adapter);

        registerForContextMenu(listView);

        // Simple Dialog
        btnDialog.setOnClickListener(v -> {

            AlertDialog.Builder builder =
                    new AlertDialog.Builder(MainActivity.this);

            builder.setTitle("Simple Dialog");
            builder.setMessage("Hello User");

            builder.setPositiveButton("OK", (dialog, which) ->
                    Toast.makeText(this, "OK Clicked",
                            Toast.LENGTH_SHORT).show());

            builder.show();
        });

        // Custom Dialog
        btnCustom.setOnClickListener(v -> {

            View view =
                    getLayoutInflater().inflate(
                            R.layout.dialog_custom,
                            null);

            AlertDialog.Builder builder =
                    new AlertDialog.Builder(MainActivity.this);

            builder.setTitle("Custom Dialog");
            builder.setView(view);

            builder.setPositiveButton("Submit",
                    (dialog, which) ->
                            Toast.makeText(this,
                                    "Submitted",
                                    Toast.LENGTH_SHORT).show());

            builder.show();
        });
    }

    // Option Menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId()==R.id.settings){
            Toast.makeText(this,
                    "Settings Clicked",
                    Toast.LENGTH_SHORT).show();
        }

        if(item.getItemId()==R.id.about){
            Toast.makeText(this,
                    "About Clicked",
                    Toast.LENGTH_SHORT).show();
        }

        return true;
    }

    // Context Menu
    @Override
    public void onCreateContextMenu(ContextMenu menu,
                                    View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {

        super.onCreateContextMenu(menu, v, menuInfo);

        getMenuInflater().inflate(
                R.menu.context_menu,
                menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        if(item.getItemId()==R.id.edit){
            Toast.makeText(this,
                    "Edit Clicked",
                    Toast.LENGTH_SHORT).show();
        }

        if(item.getItemId()==R.id.delete){
            Toast.makeText(this,
                    "Delete Clicked",
                    Toast.LENGTH_SHORT).show();
        }

        return true;
    }
}