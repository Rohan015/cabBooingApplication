package com.ar.cabBooingApplication.strategies;

import com.ar.cabBooingApplication.entities.RideRequest;

public interface RideFareCaluclationStrategy {

    double RIDE_FARE_MULTIPLIER= 10;

    double caluclateFare(RideRequest rideRequest);

}
