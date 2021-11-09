package com.example.todolist;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Currency;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    FloatingActionButton addButton;

    Database db = new Database(MainActivity.this);
    ArrayList<String> noteId;

    {
        noteId = new ArrayList<>();
    }

    ArrayList<String> noteTitle;

    {
        noteTitle = new ArrayList<>();
    }

    ArrayList<String> noteMessage;

    {
        noteMessage = new ArrayList<>();
    }

    CustomAdapter customAdapter;

    {
        customAdapter = new CustomAdapter(MainActivity.this, MainActivity.this, noteId, noteTitle, noteMessage);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycle_view);
        addButton = findViewById(R.id.btn_add);

        addButton.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, AddActivity.class);
            startActivity(intent);
        });

        saveData();

        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1){
            recreate();
        }
    }

    void saveData(){
        Cursor cursor = db.getAllNotes();
        if(cursor.getCount() == 0){
            Toast.makeText(this, "Data not found", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()){
                noteId.add(cursor.getString(0));
                noteTitle.add(cursor.getString(1));
                noteMessage.add(cursor.getString(2));
            }
        }
    }
}