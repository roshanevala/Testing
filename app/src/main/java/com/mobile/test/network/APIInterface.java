package com.mobile.test.network;

import com.google.gson.JsonArray;
import com.mobile.test.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface APIInterface {

    // Get Hotels API Call
    @Headers({
            "Content-Type: application/json",
            "X-Requested-With: XMLHttpRequest"
    })
    @GET("users")
    Call<List<User>> getUsers();

}
