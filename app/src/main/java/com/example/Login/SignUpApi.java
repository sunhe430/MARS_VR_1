package com.example.Login;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface SignUpApi {
    @POST("/register")
    Call<SignUpResponse> SignUpPost(
            @Body SignUpRequest posts
    );
}
