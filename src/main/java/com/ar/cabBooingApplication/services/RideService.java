package com.ar.cabBooingApplication.services;

import com.ar.cabBooingApplication.dto.RideRequestDto;
import com.ar.cabBooingApplication.entities.Driver;
import com.ar.cabBooingApplication.entities.Ride;
import com.ar.cabBooingApplication.entities.RideRequest;
import com.ar.cabBooingApplication.entities.enums.RideStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface RideService {
    Ride getRideById(Long rideId);
    void  matchWithDrivers(RideRequestDto rideRequestDto);
    Ride createNewRide(RideRequest rideRequest, Driver driver);
    Ride updateRideStatus(Ride ride, RideStatus rideStatus);
    Page<Ride> getAllRidesOfRider(Long rideId, PageRequest pageRequest);
    Page<Ride> getAllRidesOfDriver(Long driverId, PageRequest pageRequest);

}
