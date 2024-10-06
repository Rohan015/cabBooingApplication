package com.ar.cabBooingApplication.services.impl;

import com.ar.cabBooingApplication.dto.DriverDto;
import com.ar.cabBooingApplication.dto.RideDto;
import com.ar.cabBooingApplication.dto.RiderDto;
import com.ar.cabBooingApplication.entities.Driver;
import com.ar.cabBooingApplication.entities.Ride;
import com.ar.cabBooingApplication.entities.RideRequest;
import com.ar.cabBooingApplication.entities.enums.RideRequestStatus;
import com.ar.cabBooingApplication.entities.enums.RideStatus;
import com.ar.cabBooingApplication.exceptions.ResourceNotFoundException;
import com.ar.cabBooingApplication.repositories.DriverRepository;
import com.ar.cabBooingApplication.services.DriverService;
import com.ar.cabBooingApplication.services.RideRequestService;
import com.ar.cabBooingApplication.services.RideService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
@Service
@RequiredArgsConstructor
public class DriverServiceImpl implements DriverService {

    private final RideRequestService rideRequestService;
    private final DriverRepository driverRepository;
    private final RideService rideService;
    private final ModelMapper modelMapper;

    @Override
    @Transactional
    public RideDto acceptRide(Long rideRequestId) {
        RideRequest rideRequest = rideRequestService.findRideRequestById(rideRequestId);
        if(!rideRequest.getRideRequestStatus().equals(RideRequestStatus.PENDING)){
            throw new RuntimeException("RideRequest cannot be accepted, status is "+
                    rideRequest.getRideRequestStatus());
        }
        Driver currentDriver  = getCurrentDriver();
        if(!currentDriver.getAvailable()){
            throw new RuntimeException("Driver cannot accept ride due to unavailability");
        }
        currentDriver.setAvailable(false);
        Driver saveDriver = driverRepository.save(currentDriver);
        Ride ride = rideService.createNewRide(rideRequest,saveDriver);

        return modelMapper.map(ride, RideDto.class);
    }

    @Override
    public RideDto cancelRide(Long rideId) {
        Ride ride = rideService.getRideById(rideId);

        Driver driver = getCurrentDriver();
        if(!driver.equals(ride.getDriver())){
            throw new ResourceNotFoundException("Driver cannot start a ride as he has not accepted it earlier");
        }
        if(!ride.getRideStatus().equals(RideStatus.CONFIRMED)){
            throw new RuntimeException("Ride cannot be cancelled, invalid status: "+ride.getRideStatus());
        }
        rideService.updateRideStatus(ride, RideStatus.CANCELLED);
        driver.setAvailable(true);
        driverRepository.save(driver);

        return modelMapper.map(ride,RideDto.class);
    }

    @Override
    public RideDto startRide(Long rideId, String otp) {
        Ride ride = rideService.getRideById(rideId);
        Driver driver = getCurrentDriver();
        if(!driver.equals(ride.getDriver())){
            throw new RuntimeException("Driver cannot start a ride as he has not accept it earlier");
        }
        if(!ride.getRideStatus().equals(RideStatus.CONFIRMED)){
            throw new RuntimeException("Ride status is not CONFIRMED hence cannot be started, status: "+ ride.getRideStatus());
        }
        if(!otp.equals(ride.getOtp())){
            throw new RuntimeException("Otp is not valid, otp"+otp);
        }
        ride.setStartedAt(LocalDateTime.now());
        Ride saveRide = rideService.updateRideStatus(ride,RideStatus.ONGOING);
        return modelMapper.map(saveRide,RideDto.class);
    }

    @Override
    public RideDto endRide(Long rideId) {
        return null;
    }

    @Override
    public RiderDto rateRider(Long rideId, Integer rating) {
        return null;
    }

    @Override
    public DriverDto getMyProfile() {
        Driver currentDriver = getCurrentDriver();
        return modelMapper.map(currentDriver, DriverDto.class);
    }

    @Override
    public Page<RideDto> getAllMyRides(PageRequest pageRequest) {
        Driver currentDriver = getCurrentDriver();
        return rideService.getAllRidesOfDriver(currentDriver.getId(),pageRequest).map(
                ride -> modelMapper.map(ride,RideDto.class)
        );
    }

    @Override
    public Driver getCurrentDriver() {
        return driverRepository.findById(2L).orElseThrow(()-> new ResourceNotFoundException(
                "Driver not found by id: " +2
        ));
    }
}
