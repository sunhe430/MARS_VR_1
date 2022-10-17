package com.example.Rank;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RankingApi {
    @GET("/ranking")
    Call<List<RankingResponse>> GetRanking(
            @Query("name") String name
    );
}
