package com.ar.cabBooingApplication.services;

import com.ar.cabBooingApplication.entities.RideRequest;

public interface RideRequestService {
    RideRequest findRideRequestById(Long rideRequest);

    void update(RideRequest rideRequest);
}
