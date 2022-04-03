package com.mobile.test.network;


import androidx.lifecycle.MutableLiveData;

import com.mobile.test.di.AppModule;
import com.mobile.test.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RetroRepository {

    public RetroRepository() {
    }

    // Get Users API Call
    public void getUsersAPICall(MutableLiveData<List<User>> liveData) {
        Call<List<User>> call = AppModule.getApiInterface().getUsers();
        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                if (response.isSuccessful()) {
                    liveData.postValue(response.body());
                } else {
                    liveData.postValue(null);
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                if (t.getMessage().isEmpty()) {

                }
            }
        });
    }


}
