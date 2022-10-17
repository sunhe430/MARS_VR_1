package com.example.Mypage;

import com.example.Feedback.FeedbackResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface MypageAPI {
    @POST("/web/license")
    Call<MyResponse> PostMy(
            @Body MyRequest posts
    );
}
