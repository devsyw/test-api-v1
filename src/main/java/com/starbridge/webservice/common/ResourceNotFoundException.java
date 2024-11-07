package com.starbridge.webservice.common;

public class ResourceNotFoundException extends RuntimeException {
    // 경로 못찾으면
    public ResourceNotFoundException(String message) {
        super(message);
    }

    public ResourceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}