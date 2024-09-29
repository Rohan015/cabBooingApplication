package com.ar.cabBooingApplication.exceptions;

public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException() {
    }

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
