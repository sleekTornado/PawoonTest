package com.yow.pawoonandroiddevtest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GetToDoList getToDoList = new GetToDoList();
        getToDoList.start(this);
    }


    public void openToDoList(View view) {
        Intent intent = new Intent(this, ToDoActivity.class);
        startActivity(intent);
    }
}
