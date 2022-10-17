package com.example.Login;

import com.example.Mypage.UpdateResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface LoginApi {
    @POST("/login")
    Call<UpdateResponse> LoginPost(
            @Body LoginRequest posts
    );
}