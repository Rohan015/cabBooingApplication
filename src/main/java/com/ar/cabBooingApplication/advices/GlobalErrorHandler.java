package com.ar.cabBooingApplication.advices;

import com.ar.cabBooingApplication.exceptions.ResourceNotFoundException;
import com.ar.cabBooingApplication.exceptions.RuntimeConflictException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalErrorHandler {
    @ExceptionHandler(RuntimeConflictException.class)
    public ResponseEntity<ApiResponse<?>> handleRuntimeConflict(RuntimeConflictException exception){
        ApiError error = ApiError.builder()
                .message(exception.getMessage())
                .status(HttpStatus.CONFLICT)
                .build();
        return builderProgram(error);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse<?>> handleResourceNotFoundException(ResourceNotFoundException exception){
        ApiError error = ApiError.builder().message("Resource not found exception")
                .status(HttpStatus.NOT_FOUND).build();
        return builderProgram(error);
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<?>> handleInternalServerError(Exception exception) {
        ApiError apiError = ApiError.builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .message(exception.getMessage())
                .build();
        return builderProgram(apiError);
    }

    private ResponseEntity<ApiResponse<?>> builderProgram(ApiError apiError) {
        return new ResponseEntity<>(new ApiResponse<>(apiError),apiError.getStatus());
    }
}
