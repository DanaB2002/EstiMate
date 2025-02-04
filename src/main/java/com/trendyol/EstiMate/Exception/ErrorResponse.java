package com.trendyol.EstiMate.Exception;

public class ErrorResponse {
    private String errorCode; // For HTTP status or custom error code
    private String message;   // The error message
    private String key;       // Unique key for tracking the error instance

    // Constructor to initialize all fields
    public ErrorResponse(String errorCode, String message, String key) {
        this.errorCode = errorCode;
        this.message = message;
        this.key = key;
    }

    // Getters and Setters
    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
