package com.ar.cabBooingApplication.strategies.impl;

import com.ar.cabBooingApplication.dto.RideRequestDto;
import com.ar.cabBooingApplication.entities.RideRequest;
import com.ar.cabBooingApplication.services.DistanceService;
import com.ar.cabBooingApplication.strategies.RideFareCaluclationStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RideFareSurgePricingFareCalculationStrategy  implements RideFareCaluclationStrategy {

    private final DistanceService distanceService;
    private static final double SURGE_FACTOR = 2;

    @Override
    public double caluclateFare(RideRequest rideRequest) {
        Double distance = distanceService.caluclateDistance(rideRequest.getPickupLocation(), rideRequest.getDropOffLocation());
        return distance*RIDE_FARE_MULTIPLIER * SURGE_FACTOR;
    }
}
