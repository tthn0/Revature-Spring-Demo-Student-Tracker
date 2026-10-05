package io.github.tthn0.demo.hibernate.exceptions;

import org.springframework.http.ResponseEntity;

public class StudentErrorResponse {
    private final int status;
    private final String message;
    private final long timestamp;

    public StudentErrorResponse(int status, String message) {
        this.status = status;
        this.message = message;
        this.timestamp = System.currentTimeMillis();
    }

    public ResponseEntity<StudentErrorResponse> toResponseEntity() {
        return ResponseEntity.status(this.status).body(this);
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public long getTimestamp() {
        return timestamp;
    }
}
