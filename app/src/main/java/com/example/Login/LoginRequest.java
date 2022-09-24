package com.example.Login;

import com.google.gson.annotations.SerializedName;

public class LoginRequest {
    @SerializedName("email") String email;
    @SerializedName("pw") String pw;

    public LoginRequest(String email, String pw){
        this.email = email;
        this.pw = pw;
    }
}
