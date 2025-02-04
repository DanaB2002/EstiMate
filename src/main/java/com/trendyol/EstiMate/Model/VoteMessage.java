package com.trendyol.EstiMate.Model;

public class VoteMessage {

    private String username;
    private String vote;

    public VoteMessage() {
    }
    public VoteMessage(String username, String vote) {
        this.username = username;
        this.vote = vote;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getVote() {
        return vote;
    }

    public void setVote(String vote) {
        this.vote = vote;
    }
}
