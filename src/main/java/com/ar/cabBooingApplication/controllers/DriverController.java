package com.ar.cabBooingApplication.controllers;

import com.ar.cabBooingApplication.dto.RideDto;
import com.ar.cabBooingApplication.dto.RideStartDto;
import com.ar.cabBooingApplication.services.DriverService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/drivers")
@RequiredArgsConstructor
public class DriverController {

    private final DriverService driverService;

    @PostMapping("/acceptRide/{rideRequestId}")
    public ResponseEntity<RideDto> acceptRide(@PathVariable Long rideRequestId){
        return ResponseEntity.ok(driverService.acceptRide(rideRequestId));
    }

    @PostMapping("/startRide/{rideRequestId}")
    public ResponseEntity<RideDto> startRide(@PathVariable Long rideRequestId, @RequestBody RideStartDto rideStartDto){
        return ResponseEntity.ok(driverService.startRide(rideRequestId,rideStartDto.getOtp()));
    }
}
