package com.example.Feedback;

import com.google.gson.annotations.SerializedName;

public class FeedbackResponse {
    @SerializedName("name") String name;
    @SerializedName("kind") String kind;
    @SerializedName("score") int score;

    public FeedbackResponse(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
