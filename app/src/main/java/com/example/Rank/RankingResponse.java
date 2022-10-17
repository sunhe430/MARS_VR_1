package com.example.Rank;

public class RankingResponse {
    private int ranking;
    private String name;
    private String kind;
    private int score;
    private String play_at;

    public String getPlaying_time() {
        return playing_time;
    }

    public void setPlaying_time(String playing_time) {
        this.playing_time = playing_time;
    }

    private String playing_time;

    public int getRanking() {
        return ranking;
    }

    public void setRanking(Integer ranking) {
        this.ranking = ranking;
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

    public String getPlay_at() {
        return play_at;
    }

    public void setPlay_at(String play_at) {
        this.play_at = play_at;
    }
}
