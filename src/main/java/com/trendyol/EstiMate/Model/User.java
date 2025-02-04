package com.trendyol.EstiMate.Model;

import java.time.Instant;

public class User {
    private String username;
    private Instant createdAt;


    public User() {
    }

    public User(String username) {
        this.username = username;
        this.createdAt = Instant.now();
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }
    @Override
    public String toString() {
        return "User{username='" + username + "', createdAt=" + createdAt + "}";
    }

}
