package com.example.Mypage;

import com.google.gson.annotations.SerializedName;

public class MyResponse {
    @SerializedName("bartender") boolean bartender;
    @SerializedName("baker") boolean baker;

    public boolean isBartender() {
        return bartender;
    }

    public void setBartender(boolean bartender) {
        this.bartender = bartender;
    }

    public boolean isBaker() {
        return baker;
    }

    public void setBaker(boolean baker) {
        this.baker = baker;
    }
}
