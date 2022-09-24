package com.example.Login;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface SignUpApi {
    @POST("/register")
    Call<SignUpRequest> SignUpPost(
            @Body SignUpRequest posts
    );
}
