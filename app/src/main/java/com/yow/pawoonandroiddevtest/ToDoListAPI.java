package com.yow.pawoonandroiddevtest;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by rayhan on 15/03/18.
 */

public interface ToDoListAPI {
    @GET("todos")
    Call<List<ToDoModel>> loadToDos();
}
