package com.example.Mypage;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UpdateOkApi {
    @POST("/app/my/updateok")
    Call<UpdateResponse> PostUpdateOk(
            @Body UpdateRequest posts
    );
}
