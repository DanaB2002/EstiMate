package com.trendyol.EstiMate.Controller;

import com.trendyol.EstiMate.Model.Session;
import com.trendyol.EstiMate.Model.SessionRequest;
import com.trendyol.EstiMate.Model.SessionResponse;
import com.trendyol.EstiMate.Model.User;
import com.trendyol.EstiMate.Service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/session")
public class SessionController {

    private static int sessionCounter = 0;
    private final SessionService sessionService;

    @Autowired
    public SessionController(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    // CREATE SESSION
    @PostMapping
    public ResponseEntity<SessionResponse> createSession(@RequestBody SessionRequest request) {
        sessionCounter++;
        // create a session
        Session session = new Session(sessionCounter, request.getSessionName(), new User(request.getUsername()));
        System.out.println("Session created: " + sessionCounter);
        // add session to active sessions
        sessionService.addSession(session);
        // create a response
        SessionResponse response = new SessionResponse(request.getSessionId(), request.getSessionName(), session.getUsers());
        // return the response
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // JOIN A SESSION
    @PostMapping("/join")
    public ResponseEntity<SessionResponse> joinSession(@RequestBody SessionRequest request) {
        int sessionIndex = sessionService.getSessionIndex(request.getSessionId());
        if (sessionIndex != -1) {
            // Add the user to the session and handle the logic inside the service method.
            boolean userAdded = sessionService.addUserToSession(sessionIndex, new User(request.getUsername()));

            if (!userAdded) {
                System.out.println("User already joined: " + request.getUsername());
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(createResponse(false, "User already joined"));
            }

            System.out.println("User added successfully: " + request.getUsername());
            return ResponseEntity.ok(createResponse(true, "User joined successfully"));
        }

        System.out.println("Session not found: " + request.getSessionId());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(createResponse(false, "Session not found"));
    }


    // LIST ACTIVE SESSIONS
    @GetMapping("/all-sessions")
    public ResponseEntity<List<SessionResponse>> listActiveSessions() {
        List<Session> activeSessions = sessionService.getActiveSessions();
        List<SessionResponse> responseList = new ArrayList<>();

        for (Session session : activeSessions) {
            SessionResponse response = new SessionResponse(session.getSessionId(),session.getSessionName(), session.getUsers());
            responseList.add(response);
        }

        return ResponseEntity.ok(responseList);
    }


    // CREATE JOIN A SESSION RESPONSE
    private SessionResponse createResponse(boolean success, String message) {
        SessionResponse response = new SessionResponse();
        response.setSuccess(success);
        response.setMessage(message);
        return response;
    }


}
