package com.trendyol.EstiMate.Controller;

import com.trendyol.EstiMate.Exception.InvalidVoteException;
import com.trendyol.EstiMate.Model.VoteMessage;
import com.trendyol.EstiMate.Service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.*;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.web.bind.annotation.RestController;
import com.trendyol.EstiMate.Model.Session;

import java.util.Arrays;
import java.util.Map;

@RestController
public class WebSocketController {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @Autowired
    private SessionService sessionService;

    @MessageMapping("/vote/{sessionId}")
    public void handleVote(@Payload VoteMessage voteMessage, @DestinationVariable int sessionId) {
        Session session = sessionService.findSession(sessionId);

        if(session == null) {
            throw new InvalidVoteException("Session not found");
        }

        if (!isValidVote(voteMessage.getVote())) {
            throw new InvalidVoteException("Invalid vote, must be a Fibonacci number");
        }

        try{
            // add the username and vote to that session.
            session.addVote(voteMessage.getUsername(), voteMessage.getVote());

            // Broadcast ALL votes to the session's topic, not just the updated vote.
            messagingTemplate.convertAndSend("/topic/session/" + sessionId, session.getVotes());


        } catch (InvalidVoteException e) {
            throw new InvalidVoteException(e.getMessage());
        }


    }

    // Handle WebSocket exceptions
    @MessageExceptionHandler(InvalidVoteException.class)
    @SendToUser("/queue/errors") // sends the response only to the user who made the request.
    public Map<String, String> handleWebSocketInvalidVoteException(InvalidVoteException ex) {
        return Map.of("error", ex.getMessage());
    }



    // avoid hard coded Fibonacci sequesnce. TODO: A METHOD TO GENERATE FIBONACCI SEQUENCE GIVING A MAX NUM.
    private boolean isValidVote(String vote) {
        try {
            int voteNumber = Integer.parseInt(vote);
            return Arrays.asList(0, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89).contains(voteNumber);
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
