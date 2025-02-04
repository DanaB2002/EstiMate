package com.trendyol.EstiMate.Controller;

import com.trendyol.EstiMate.Model.Session;
import com.trendyol.EstiMate.Model.SessionRequest;
import com.trendyol.EstiMate.Model.SessionResponse;
import com.trendyol.EstiMate.Model.User;
import com.trendyol.EstiMate.Service.SessionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SessionControllerTest {

    private SessionController sessionController;
    private SessionService sessionService;

    @BeforeEach
    void setUp() {
        sessionService = new SessionService();
        sessionController = new SessionController(sessionService);
    }

    @Test
    void testCreateSession() {
        String sessionName = "First Session";
        String username = "user1";
        SessionRequest request = new SessionRequest();
        request.setSessionName(sessionName);
        request.setUsername(username);

        ResponseEntity<SessionResponse> response = sessionController.createSession(request);

        // Check response status code = Created
        assertEquals(201, response.getStatusCodeValue());

        // Check the response body
        SessionResponse responseBody = response.getBody();
        assertEquals(sessionName, responseBody.getSessionName());

        // Check if the user created the session is added
        assertEquals(1, responseBody.getUsers().size());
        // Check the username of that user
        assertEquals(username, responseBody.getUsers().get(0).getUsername());
    }

    @Test
    void testJoinSession() {
        String sessionName = "First Session";
        String username1 = "user1";
        String username2 = "user2";

        // Create a session
        SessionRequest createRequest = new SessionRequest();
        createRequest.setSessionName(sessionName);
        createRequest.setUsername(username1);
        sessionController.createSession(createRequest);

        // Join the created session
        SessionRequest joinRequest = new SessionRequest();
        joinRequest.setSessionId(1);
        joinRequest.setUsername(username2);

        ResponseEntity<SessionResponse> response = sessionController.joinSession(joinRequest);

        // Check response status code = Ok
        assertEquals(200, response.getStatusCodeValue());

        // Check the response body
        SessionResponse responseBody = response.getBody();
        assertEquals(true, responseBody.isSuccess());
        assertEquals("User joined successfully", responseBody.getMessage());

        // Check if the user was added to the session
        List<Session> sessions = sessionService.getActiveSessions();
        assertEquals(2, sessions.get(0).getUsers().size());
    }

    @Test
    void testJoinSession_UserAlreadyExists() {
        String sessionName = "First Session";
        String username = "user1";

        // Create a session
        SessionRequest createRequest = new SessionRequest();
        createRequest.setSessionName(sessionName);
        createRequest.setUsername(username);
        sessionController.createSession(createRequest);

        // Join the same session with the same user
        SessionRequest joinRequest = new SessionRequest();
        joinRequest.setSessionId(4);
        joinRequest.setUsername(username);

        ResponseEntity<SessionResponse> response = sessionController.joinSession(joinRequest);

        // Check response status code = Ok
        assertEquals(400, response.getStatusCodeValue());

        // Check the response body
        SessionResponse responseBody = response.getBody();
        assertEquals(false, responseBody.isSuccess());
        assertEquals("User already joined", responseBody.getMessage());
    }

    @Test
    void testJoinSession_SessionNotFound() {
        // Request to join a session with a non-existent ID
        SessionRequest request = new SessionRequest();
        request.setSessionId(99);
        request.setUsername("new_user");

        ResponseEntity<SessionResponse> response = sessionController.joinSession(request);

        // Check response status code = Ok
        assertEquals(404, response.getStatusCodeValue());

        // Check the response body
        SessionResponse responseBody = response.getBody();
        assertEquals(false, responseBody.isSuccess());
        assertEquals("Session not found", responseBody.getMessage());
    }

    @Test
    void testListAllActiveSessions() {
        // Create two sessions
        SessionRequest request1 = new SessionRequest();
        request1.setSessionName("First Session");
        request1.setUsername("user1");
        sessionController.createSession(request1);

        SessionRequest request2 = new SessionRequest();
        request2.setSessionName("Second Session");
        request2.setUsername("user2");
        sessionController.createSession(request2);

        // Get all active sessions
        ResponseEntity<List<SessionResponse>> response = sessionController.listActiveSessions();
        System.out.println("List of active sessions: " + response.toString());
        // Check response status code = Ok
        assertEquals(200, response.getStatusCodeValue());

        // Check the response body
        List<SessionResponse> sessionResponses = response.getBody();
        assertEquals(2, sessionResponses.size());

        // Verify session details
        assertEquals("First Session", sessionResponses.get(0).getSessionName());
        assertEquals("Second Session", sessionResponses.get(1).getSessionName());
    }
}
