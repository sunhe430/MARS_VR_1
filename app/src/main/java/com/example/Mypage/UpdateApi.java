package com.example.Mypage;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UpdateApi {
    @POST("/app/my/update")
    Call<UpdateResponse> PostUpdate(
            @Body UpdateRequest posts
    );
}
