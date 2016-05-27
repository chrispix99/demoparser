package com.christopherpick.demoparser.rest;


import com.christopherpick.demoparser.models.Category;
import retrofit.Callback;
import retrofit.http.GET;

import java.util.List;

public interface DemoRestServer {
    //FIXME: change this URL
    @GET("/task-list.json")
    void getDemoData(Callback<List<Category>> categories);
}
