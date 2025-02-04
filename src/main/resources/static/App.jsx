import { useState } from "react";


const App = () => {
    const [sessionName, setSessionName] = useState("");
    const [sessionId, setSessionId] = useState("");
    const [username, setUsername] = useState("");
    const [createdSessionId, setCreatedSessionId] = useState(null); // Store created session ID


    const createSession = async () => {
        const response = await fetch("http://localhost:8080/session", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify({ sessionName, username }),
        });

        const data = await response.json();
        console.log("Session Created:", data);

        if (data.sessionId) {  // Now, sessionId should be present in data
            setCreatedSessionId(data.sessionId); // Store session ID
        }
    };

    return (
        <div>
            <h1>Estimation Poker</h1>

            <h2>Create a Session</h2>
            <input
                type="text"
                placeholder="Session Name"
                value={sessionName}
                onChange={(e) => setSessionName(e.target.value)}
            />
            <input
                type="text"
                placeholder="Username"
                value={username}
                onChange={(e) => setUsername(e.target.value)}
            />
            <button onClick={createSession}>Create Session</button>

            {createdSessionId && (
                <p>Session Created! Share this ID: <strong>{createdSessionId}</strong></p>
            )}

            <h2>Join a Session</h2>
            <input
                type="text"
                placeholder="Session ID"
                value={sessionId}
                onChange={(e) => setSessionId(e.target.value)}
            />
            <button onClick={joinSession}>Join Session</button>
        </div>
    );
}

export default App;