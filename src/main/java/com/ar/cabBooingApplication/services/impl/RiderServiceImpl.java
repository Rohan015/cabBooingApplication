package com.ar.cabBooingApplication.services.impl;

import com.ar.cabBooingApplication.dto.DriverDto;
import com.ar.cabBooingApplication.dto.RideDto;
import com.ar.cabBooingApplication.dto.RideRequestDto;
import com.ar.cabBooingApplication.dto.RiderDto;
import com.ar.cabBooingApplication.entities.RideRequest;
import com.ar.cabBooingApplication.entities.Rider;
import com.ar.cabBooingApplication.entities.User;
import com.ar.cabBooingApplication.entities.enums.RideRequestStatus;
import com.ar.cabBooingApplication.exceptions.ResourceNotFoundException;
import com.ar.cabBooingApplication.repositories.RideRequestRepository;
import com.ar.cabBooingApplication.repositories.RiderRepository;
import com.ar.cabBooingApplication.services.RiderService;
import com.ar.cabBooingApplication.strategies.DriverMatchingStrategy;
import com.ar.cabBooingApplication.strategies.RideFareCaluclationStrategy;
import com.ar.cabBooingApplication.strategies.RideStrategyManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class RiderServiceImpl implements RiderService {

    private final ModelMapper mapper;
    private final RideStrategyManager rideStrategyManager;
//    private final RideFareCaluclationStrategy rideFareCaluclationStrategy;
//    private final DriverMatchingStrategy driverMatchingStrategy;
    private final RideRequestRepository rideRequestRepository;
    private final RiderRepository riderRepository;

    @Override
    public RideRequestDto requestRide(RideRequestDto rideRequestDto) {
        Rider rider = getCurrentRider();
        RideRequest rideRequest = mapper.map(rideRequestDto, RideRequest.class);
        rideRequest.setRideRequestStatus(RideRequestStatus.PENDING);
        rideRequest.setRider(rider);

        Double fare = rideStrategyManager.rideFareCaluclationStrategy().caluclateFare(rideRequest);
        rideRequest.setFare(fare);

        RideRequest saveRideRequest = rideRequestRepository.save(rideRequest);

//        log.info(rideRequest.toString());
        rideStrategyManager.driverMatchingStrategy(rider.getRating()).findMatchDriver(rideRequest);


        return mapper.map(saveRideRequest, RideRequestDto.class);
    }

    @Override
    public RideDto cancelRide(Long rideId) {
        return null;
    }

    @Override
    public DriverDto rateDriver(Long rideId, Integer rating) {
        return null;
    }

    @Override
    public RiderDto getMyProfile() {
        return null;
    }

    @Override
    public List<RideDto> getAllMyRides() {
        return List.of();
    }

    @Override
    public Rider createNewRider(User user) {
        Rider rider = Rider.builder().user(user).rating(0.0).build();
        return riderRepository.save(rider);
    }

    @Override
    public Rider getCurrentRider() {
        //TODO implement spring security
        return riderRepository.findById(1L).orElseThrow(() -> new ResourceNotFoundException(
                "Rider not found with id:" + 1));
    }
}