package com.ar.cabBooingApplication.services.impl;

import com.ar.cabBooingApplication.dto.RideRequestDto;
import com.ar.cabBooingApplication.entities.Driver;
import com.ar.cabBooingApplication.entities.Ride;
import com.ar.cabBooingApplication.entities.RideRequest;
import com.ar.cabBooingApplication.entities.enums.RideRequestStatus;
import com.ar.cabBooingApplication.entities.enums.RideStatus;
import com.ar.cabBooingApplication.exceptions.ResourceNotFoundException;
import com.ar.cabBooingApplication.repositories.RideRepository;
import com.ar.cabBooingApplication.services.RideRequestService;
import com.ar.cabBooingApplication.services.RideService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@RequiredArgsConstructor
public class RideServiceImpl implements RideService {
    private final RideRepository rideRepository;
    private final RideRequestService rideRequestService;
    private final ModelMapper modelMapper;

    @Override
    public Ride getRideById(Long rideId) {
        return rideRepository.findById(rideId).orElseThrow(() -> new ResourceNotFoundException("Ride not found by id: "+rideId));
    }

    @Override
    public void matchWithDrivers(RideRequestDto rideRequestDto) {

    }
    @Override
    public Ride createNewRide(RideRequest rideRequest, Driver driver) {
        rideRequest.setRideRequestStatus(RideRequestStatus.CONFIRMED);

        Ride ride = modelMapper.map(rideRequest, Ride.class);
        ride.setRideStatus(RideStatus.CONFIRMED);
        ride.setDriver(driver);
        ride.setOtp(generateRandomOtp());
        ride.setId(null);

        rideRequestService.update(rideRequest);
        return rideRepository.save(ride);
    }

    @Override
    public Ride updateRideStatus(Ride ride, RideStatus rideStatus) {
        ride.setRideStatus(rideStatus);
        return rideRepository.save(ride);
    }

    @Override
    public Page<Ride> getAllRidesOfRider(Long rideId, PageRequest pageRequest) {
        return null;
    }

    @Override
    public Page<Ride> getAllRidesOfDriver(Long driverId, PageRequest pageRequest) {
        return null;
    }
    private String generateRandomOtp(){
        Random random = new Random();
        int optInt = random.nextInt(10000);// 0 to 9999
        return String.format("%04d",optInt);
    }
}
