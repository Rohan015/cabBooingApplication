package com.ar.cabBooingApplication.services;

import com.ar.cabBooingApplication.dto.DriverDto;
import com.ar.cabBooingApplication.dto.RideDto;
import com.ar.cabBooingApplication.dto.RideRequestDto;
import com.ar.cabBooingApplication.dto.RiderDto;
import com.ar.cabBooingApplication.entities.Rider;
import com.ar.cabBooingApplication.entities.User;
import com.ar.cabBooingApplication.entities.enums.RideRequestStatus;

import java.util.List;

public interface RiderService {
    RideRequestDto requestRide(RideRequestDto rideRequestDto);

    RideDto cancelRide(Long rideId);

    DriverDto rateDriver(Long rideId, Integer rating);

    RiderDto getMyProfile();

    List<RideDto> getAllMyRides();

    Rider createNewRider(User user);

    Rider getCurrentRider();
}
