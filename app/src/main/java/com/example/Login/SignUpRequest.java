package com.example.Login;

import com.google.gson.annotations.SerializedName;

public class SignUpRequest {
    @SerializedName("name")
    private String name;
    @SerializedName("email")
    private String email;
    @SerializedName("pw")
    private String pw;

    public SignUpRequest(String name, String email, String pw){
        this.name = name;
        this.email = email;
        this.pw = pw;
    }
}
