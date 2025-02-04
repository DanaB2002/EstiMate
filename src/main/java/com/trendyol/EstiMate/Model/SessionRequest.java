package com.trendyol.EstiMate.Model;

public class SessionRequest {
    // creation
    private String sessionName;
    // joining
    private int sessionId;

    private String username;



    public SessionRequest() {
    }
    // TODO: add argument constructor


    // getters and setters
    public String getSessionName() {
        return sessionName;
    }

    public void setSessionName(String name) {
        this.sessionName = name;
    }

    public int getSessionId() {
        return sessionId;
    }

    public void setSessionId(int sessionId) {
        this.sessionId = sessionId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
