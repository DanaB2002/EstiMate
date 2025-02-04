package com.trendyol.EstiMate.Model;

import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Session {
    private int sessionId;
    private String sessionName;
    private Instant createdAt;
    private List<User> users = new ArrayList<>();
    private Map<String, String> votes = new HashMap<>(); // Store username -> vote


    public Session() {
    }

    // TODO: add argument constructor

    // when creating a session ADD the user that created the session
    public Session(int sessionId, String sessionName, User user) {
        this.sessionId = sessionId;
        this.sessionName = sessionName;
        this.users.add(user);
        this.createdAt = Instant.now();
    }


    // getters and setters


    public int getSessionId() {
        return sessionId;
    }

    public void setSessionId(int sessionId) {
        this.sessionId = sessionId;
    }

    public String getSessionName() {
        return sessionName;
    }

    public void setSessionName(String sessionName) {
        this.sessionName = sessionName;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public Map<String, String> getVotes() {
        return votes;
    }

    // for votes Map
    public void addVote(String username, String vote) {
        // prevent multiple votes for the user
        if (votes.containsKey(username)) {
            throw new IllegalArgumentException("User can only have one vote");
        }
        votes.put(username, vote);
    }

    public void clearVotes() {
        votes.clear();
    }

}
