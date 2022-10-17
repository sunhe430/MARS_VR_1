package com.example.Rank;

public class Rank {
    private String ranking;
    private String name;
    private String kind;
    private String score;
    private String play_at;

    public Rank(String ranking, String name, String score, String play_at){
        this.ranking = ranking;
        this.name = name;
        this.score = score;
        this.play_at = play_at;
    }

    public String getRanking() {
        return ranking;
    }

    public void setRanking(String ranking) {
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

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getPlay_at() {
        return play_at;
    }

    public void setPlay_at(String play_at) {
        this.play_at = play_at;
    }
}
