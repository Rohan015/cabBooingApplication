package com.ar.cabBooingApplication.services;

import com.ar.cabBooingApplication.dto.DriverDto;
import com.ar.cabBooingApplication.dto.SignupDto;
import com.ar.cabBooingApplication.dto.UserDto;

public interface AuthService {
    String login(String email, String password);

    UserDto signup(SignupDto signupDto);

    DriverDto onboardNewDriver(Long userId);
}
