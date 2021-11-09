package com.example.todolist;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class AddActivity extends AppCompatActivity {

    EditText etMessage, etTitle;
    Button btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        etMessage = findViewById(R.id.et_message);
        etTitle = findViewById(R.id.et_title);
        btnAdd = findViewById(R.id.btn_add);

        btnAdd.setOnClickListener(view -> {
             Database db = new Database(AddActivity.this);
             db.addNote(etTitle.getText().toString(), etMessage.getText().toString());
        });
    }
}