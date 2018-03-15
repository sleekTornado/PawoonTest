package com.yow.pawoonandroiddevtest;

import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

public class ToDoActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ToDoListAdapter toDoListAdapter;
    private List<ToDoModel> toDoList;
    private DatabaseManager databaseManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do);

        databaseManager = new DatabaseManager(this);
        toDoList =  databaseManager.getTodoList();

        recyclerView = (RecyclerView) findViewById(R.id.recylerView);
        toDoListAdapter = new ToDoListAdapter(this, toDoList);
        recyclerView.setAdapter(toDoListAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
