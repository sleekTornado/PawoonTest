package com.yow.pawoonandroiddevtest;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by rayhan on 15/03/18.
 */

public class GetToDoList implements Callback<List<ToDoModel>> {
    static final String URL = "http://jsonplaceholder.typicode.com/";
    DatabaseManager databaseManager;

    public void start(Context context){
        databaseManager = new DatabaseManager(context);


        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        ToDoListAPI toDoListAPI = retrofit.create(ToDoListAPI.class);

        Call<List<ToDoModel>> call = toDoListAPI.loadToDos();
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<List<ToDoModel>> call, Response<List<ToDoModel>> response) {
        if(response.isSuccessful()) {
            List<ToDoModel> toDoModelList = response.body();
            for(ToDoModel toDoModel : toDoModelList ){
                databaseManager.insertToDoList(toDoModel);
            }
        } else {
            System.out.println(response.errorBody());
        }
    }

    @Override
    public void onFailure(Call<List<ToDoModel>> call, Throwable t) {
        t.printStackTrace();
    }

}
