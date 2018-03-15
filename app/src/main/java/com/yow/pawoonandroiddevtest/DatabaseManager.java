package com.yow.pawoonandroiddevtest;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rayhan on 15/03/18.
 */

public class DatabaseManager extends SQLiteOpenHelper {

    public DatabaseManager(Context context) {
        super(context, "todo" , null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE todo_list (id INTEGER PRIMARY KEY, " +
                "userId INTEGER, " +
                "title TEXT, " +
                "completed INTEGER " +
                ")"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void insertToDoList(ToDoModel toDoModel) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("id", toDoModel.getId());
        contentValues.put("userId", toDoModel.getUserId());
        contentValues.put("title", toDoModel.getTitle());
        contentValues.put("completed", toDoModel.isCompleted() ? 1 : 0);
        db.insert("todo_list", null, contentValues);
    }

    public ToDoModel getTodoModel(Cursor cursor){
        try
        {
            ToDoModel toDoModel = new ToDoModel();

            int idIndex = cursor.getColumnIndex("id");
            int userIdIndex = cursor.getColumnIndex("userId");
            int titleIndex = cursor.getColumnIndex("title");
            int completedIndex = cursor.getColumnIndex("completed");

            toDoModel.setId(cursor.getInt(idIndex));
            toDoModel.setUserId(cursor.getInt(userIdIndex));
            toDoModel.setTitle(cursor.getString(titleIndex));
            toDoModel.setCompleted(cursor.getInt(completedIndex) == 1);

            return toDoModel;
        }
        catch (Exception e)
        {
            return null;
        }
    }

    public List<ToDoModel> getTodoList(){
        List<ToDoModel> items = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM todo_list", new String[]{});

        for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext()){
            items.add(getTodoModel(c));
        }

        return items;
    }
}
