package com.ar.cabBooingApplication.strategies;

import com.ar.cabBooingApplication.dto.RideRequestDto;
import com.ar.cabBooingApplication.entities.Driver;
import com.ar.cabBooingApplication.entities.RideRequest;

import java.util.List;

public interface DriverMatchingStrategy {
    List<Driver> findMatchDriver(RideRequest rideRequest);
}
