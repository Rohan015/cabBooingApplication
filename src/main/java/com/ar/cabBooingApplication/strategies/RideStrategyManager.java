package com.ar.cabBooingApplication.strategies;

import com.ar.cabBooingApplication.strategies.impl.DriverMatchingHighestRatedDriverStrategy;
import com.ar.cabBooingApplication.strategies.impl.DriverMatchingNearestDriverStrategy;
import com.ar.cabBooingApplication.strategies.impl.RideFareSurgePricingFareCalculationStrategy;
import com.ar.cabBooingApplication.strategies.impl.RiderFareDefaultFareCalculationStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalTime;

@Component
@RequiredArgsConstructor
public class RideStrategyManager {

    private final DriverMatchingHighestRatedDriverStrategy driverMatchingHighestRatedDriverStrategy;
    private final DriverMatchingNearestDriverStrategy driverMatchingNearestDriverStrategy;
    private final RideFareSurgePricingFareCalculationStrategy rideFareSurgePricingFareCalculationStrategy;
    private final RiderFareDefaultFareCalculationStrategy riderFareDefaultFareCalculationStrategy;

    public DriverMatchingStrategy driverMatchingStrategy(Double rideRating){
        if ((rideRating >= 4.8)) return driverMatchingHighestRatedDriverStrategy;
        else return driverMatchingNearestDriverStrategy;

    }

    public RideFareCaluclationStrategy rideFareCaluclationStrategy(){
    //6PM to 9PM
        LocalTime surgeStartTime = LocalTime.of(18,0);
        LocalTime surgeEndTime = LocalTime.of(21,0);
        LocalTime currentTime = LocalTime.now();
        boolean isSurgeTime = currentTime.isAfter(surgeStartTime) && currentTime.isBefore(surgeEndTime);

        if(isSurgeTime) return rideFareSurgePricingFareCalculationStrategy;
        else return riderFareDefaultFareCalculationStrategy;
    }

}
