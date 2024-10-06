package com.ar.cabBooingApplication.services;

import com.ar.cabBooingApplication.dto.DriverDto;
import com.ar.cabBooingApplication.dto.RideDto;
import com.ar.cabBooingApplication.dto.RiderDto;
import com.ar.cabBooingApplication.entities.Driver;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface DriverService {
    RideDto acceptRide(Long rideRequestId);

    RideDto cancelRide(Long rideId);

    RideDto startRide(Long rideId, String otp);

    RideDto endRide(Long rideId);

    RiderDto rateRider(Long rideId, Integer rating);

    DriverDto getMyProfile();

    Page<RideDto> getAllMyRides(PageRequest pageRequest);

    Driver getCurrentDriver();
}
