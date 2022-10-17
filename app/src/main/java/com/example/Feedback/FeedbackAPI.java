package com.example.Feedback;

import com.example.Rank.RankingResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface FeedbackAPI {
    @POST("/app/feedback")
    Call<FeedbackResponse> PostFeedback(
            @Body FeedbackResponse posts
    );
}
