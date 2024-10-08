package com.ar.cabBooingApplication.advices;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class ApiResponse<T> {
    private LocalDateTime time;
    private T data;
    private ApiError error;

    public ApiResponse() {
        this.time = LocalDateTime.now();
    }

    public ApiResponse(T data) {
        this();
        this.data = data;
    }

    public ApiResponse(ApiError error) {
        this();
        this.error = error;
    }
}
