package com.ar.cabBooingApplication.strategies.impl;

import com.ar.cabBooingApplication.entities.Driver;
import com.ar.cabBooingApplication.entities.RideRequest;
import com.ar.cabBooingApplication.repositories.DriverRepository;
import com.ar.cabBooingApplication.strategies.DriverMatchingStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Primary
public class DriverMatchingNearestDriverStrategy implements DriverMatchingStrategy {

    private final DriverRepository driverRepository;

    @Override
    public List<Driver> findMatchDriver(RideRequest rideRequest) {
        return driverRepository.findMatchingDriver(rideRequest.getPickupLocation());
    }
}
