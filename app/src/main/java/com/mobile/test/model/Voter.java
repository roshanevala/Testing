package com.mobile.test.model;

import com.google.gson.annotations.SerializedName;

public class Voter {
    @SerializedName("name")
    private String name;
    @SerializedName("votes")
    private long votes;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getVotes() {
        return votes;
    }

    public void setVotes(long votes) {
        this.votes = votes;
    }
}
