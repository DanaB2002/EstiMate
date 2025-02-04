package com.trendyol.EstiMate.Model;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class SessionResponse {
    // creation
    private int sessionId;
    private String sessionName;
    private Instant createdAt;
    private List<User> users = new ArrayList<>();

    // joining
    private boolean success;
    private String message;

    // list active sessions
    private int usersCount;


    public SessionResponse() {
    }

    // TODO: add argument constructor


    // pass in the users from the session to construct the response, this class doesn't ADD a user.
    public SessionResponse(int sessionId, String name, List<User> users) {
        this.sessionId = sessionId;
        this.sessionName = name;
        this.createdAt = Instant.now();
        this.users = users;
        this.usersCount = users.size();
    }

    public SessionResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public int getSessionId() {
        return sessionId;
    }

    public void setSessionId(int sessionId) {
        this.sessionId = sessionId;
    }

    public String getSessionName() {
        return sessionName;
    }

    public void setSessionName(String name) {
        this.sessionName = name;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
    public int getUsersCount() {
        return usersCount;
    }

    public void setUsersCount(int usersCount) {
        this.usersCount = usersCount;
    }

    //[{ "name": "string", "createdAt": 0L, "usersCount": 0 }]
    @Override
    public String toString() {
        return "SessionResponse{" +
                "name='" + sessionName + '\'' +
                ", createdAt=" + createdAt +
                ", usersCount=" + usersCount +
                '}';
    }
}
