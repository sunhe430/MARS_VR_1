package com.example.Mypage;

import com.google.gson.annotations.SerializedName;

public class MyRequest {
    @SerializedName("name") String name;

    public MyRequest(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
