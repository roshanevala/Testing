package com.mobile.test.network.response;

import com.google.gson.annotations.SerializedName;
import com.mobile.test.model.User;

import java.util.List;

public class UsersResponse {

    @SerializedName("user")
    private List<User> users;

}
