package com.example.todolist;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {

    EditText titleEditText, messageEditText;
    Button updateButton, deleteButton;
    String id, title, message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        titleEditText = findViewById(R.id.et_Title;
        messageEditText = findViewById(R.id.et_Message);
        updateButton = findViewById(R.id.btn_Update);
        deleteButton = findViewById(R.id.btn_Delete);

        getSelectedData();

        updateButton.setOnClickListener(view -> {
            Database db = new Database(UpdateActivity.this);
            title = titleEditText.getText().toString();
            message = messageEditText.getText().toString();
            db.updateData(id, title, message);
        });

        deleteButton.setOnClickListener(view -> {
            Database db = new Database(UpdateActivity.this);
            db.deleteData(id);
            finish();
        });
    }

    void getSelectedData(){
        if (getIntent().hasExtra("id") && getIntent().hasExtra("title") && getIntent().hasExtra("message")){

            id = getIntent().getStringExtra("id");
            title = getIntent().getStringExtra("title");
            message = getIntent().getStringExtra("message");

            titleEditText.setText(title);
            messageEditText.setText(message);

        } else{
            Toast.makeText(this, "There's no data to update", Toast.LENGTH_SHORT).show();
        }
    }
}