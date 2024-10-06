package com.ar.cabBooingApplication.services.impl;

import com.ar.cabBooingApplication.dto.DriverDto;
import com.ar.cabBooingApplication.dto.SignupDto;
import com.ar.cabBooingApplication.dto.UserDto;
import com.ar.cabBooingApplication.entities.User;
import com.ar.cabBooingApplication.entities.enums.Role;
import com.ar.cabBooingApplication.exceptions.RuntimeConflictException;
import com.ar.cabBooingApplication.repositories.UserRepository;
import com.ar.cabBooingApplication.services.AuthService;
import com.ar.cabBooingApplication.services.RiderService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final RiderService riderService;
    private final UserRepository userRepository;
    private final ModelMapper mapper;

    @Override
    public String login(String email, String password) {
        return "";
    }

    @Override
    @Transactional
    public UserDto signup(SignupDto signupDto) {
        User user = userRepository.findByEmail(signupDto.getEmail()).orElse(null);
        if (user != null) {
            throw new RuntimeConflictException(
                    "cannot signup, User already exists with email "
                            + signupDto.getEmail());
        }

        User mappedUser = mapper.map(signupDto, User.class);
        mappedUser.setRoles(Set.of(Role.RIDER));
        User saveUser = userRepository.save(mappedUser);
        riderService.createNewRider(saveUser);
//TODO add wallet related service here
        return mapper.map(saveUser, UserDto.class);
    }

    @Override
    public DriverDto onboardNewDriver(Long userId) {
        return null;
    }
}
