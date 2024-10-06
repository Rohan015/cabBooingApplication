package com.ar.cabBooingApplication.strategies.impl;

import com.ar.cabBooingApplication.entities.RideRequest;
import com.ar.cabBooingApplication.services.DistanceService;
import com.ar.cabBooingApplication.strategies.RideFareCaluclationStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
//@Primary
public class RiderFareDefaultFareCalculationStrategy implements RideFareCaluclationStrategy {

    private final DistanceService distanceService;

    @Override
    public double caluclateFare(RideRequest rideRequest) {
        Double distance = distanceService.caluclateDistance(rideRequest.getPickupLocation(), rideRequest.getDropOffLocation());
        return distance*RIDE_FARE_MULTIPLIER;
    }

}
