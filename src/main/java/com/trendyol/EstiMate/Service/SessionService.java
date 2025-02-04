package com.trendyol.EstiMate.Service;

import com.trendyol.EstiMate.Model.Session;
import com.trendyol.EstiMate.Model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SessionService {
    // FIELD
    private final List<Session> activeSessions = new ArrayList<>();

    // GETTER
    public List<Session> getActiveSessions() {
        return activeSessions;
    }

    // ADD A NEW SESSION TO LIST
    public void addSession(Session session) {
        activeSessions.add(session);
    }


    // SEARCH FOR A SESSION BY ID
    public int getSessionIndex(int sessionId) {
        for (int i = 0; i < activeSessions.size(); i++) {
            if (activeSessions.get(i).getSessionId() == sessionId) {
                return i;
            }
        }
        return -1;
    }

    // ADD A USER TO SESSION
    public boolean addUserToSession(int sessionIndex, User user) {
        Session session = activeSessions.get(sessionIndex);
        if (!findUser(sessionIndex, user.getUsername())) {
            session.getUsers().add(user);
            return true;  // User was successfully added.
        }
        return false;  // User already exists in the session.
    }


    // FIND IF USER ALREADY IN A SESSION
    public boolean findUser(int sessionId, String username) {
        List<User> users = activeSessions.get(sessionId).getUsers();
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return true;
            }
        }

        return false;

    }


    public Session findSession(int sessionId) {
        for (int i = 0; i < activeSessions.size(); i++) {
            if (activeSessions.get(i).getSessionId() == sessionId) {
                return activeSessions.get(i);
            }
        }
        return null;
    }


}
