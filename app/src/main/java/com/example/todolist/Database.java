package com.example.todolist;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class Database extends SQLiteOpenHelper {

    private final Context context;
    private static final String DATABASE_NAME = "todoListDataBase";
    private static final int DATABASE_VERSION = 1;

    private static final String Notes = "my_todolist";
    private static final String Id = "id";
    private static final String Title = "title";
    private static final String Message = "message";

    public Database(@Nullable Context context) {
        super(context, "ToDoListDB", null, 1);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String query = "CREATE TABLE " + Notes + " (" + Id + " INTEGER PRIMARY KEY AUTOINCREMENT, " + Title + " TEXT, " + Message + " TEXT);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + Notes);
        onCreate(db);
    }

    void addNote(String title, String message){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(Title, title);
        contentValues.put(Message, message);
        long result = db.insert(Notes, null, contentValues);
        if (result == -1){
            Toast.makeText(context, "Failed to add note", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Note added successfully!", Toast.LENGTH_SHORT).show();
        }
    }

    Cursor getAllNotes(){
        String query = "SELECT * FROM " + Notes;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if (db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    void updateData(String rowId, String title, String message){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(Title, title);
        contentValues.put(Message, message);

        long result = db.update(Notes, contentValues, "id=?", new String[]{rowId});
        if (result == -1){
            Toast.makeText(context, "Failed to update note", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Note update successfully!", Toast.LENGTH_SHORT).show();
        }
    }

    void deleteData(String rowId){
        SQLiteDatabase db = this.getWritableDatabase();

        long result = db.delete(Notes, "id=?", new String[]{rowId});
        if (result == -1){
            Toast.makeText(context, "Failed to delete note", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Note deleted successfully!", Toast.LENGTH_SHORT).show();
        }
    }
}
