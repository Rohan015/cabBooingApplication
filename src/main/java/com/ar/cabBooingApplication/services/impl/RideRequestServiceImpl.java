package com.ar.cabBooingApplication.services.impl;

import com.ar.cabBooingApplication.entities.RideRequest;
import com.ar.cabBooingApplication.exceptions.ResourceNotFoundException;
import com.ar.cabBooingApplication.repositories.RideRequestRepository;
import com.ar.cabBooingApplication.services.RideRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RideRequestServiceImpl implements RideRequestService {

    private final RideRequestRepository rideRequestRepository;

    @Override
    public RideRequest findRideRequestById(Long rideRequestId) {
        return rideRequestRepository.findById(rideRequestId).orElseThrow(()
        -> new ResourceNotFoundException("RideRequest not found with id: "+ rideRequestId));
    }

    @Override
    public void update(RideRequest rideRequest) {
    rideRequestRepository.findById(rideRequest.getId()).orElseThrow(()
    -> new ResourceNotFoundException("RideRequest not found with id: "+ rideRequest.getId()));
        rideRequestRepository.save(rideRequest);

    }
}
