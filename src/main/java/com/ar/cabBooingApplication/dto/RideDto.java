package com.ar.cabBooingApplication.dto;

import com.ar.cabBooingApplication.entities.enums.PaymentMethod;
import com.ar.cabBooingApplication.entities.enums.RideRequestStatus;
import com.ar.cabBooingApplication.entities.enums.RideStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RideDto {
    public Long id;
    private PointDto pickupLocation; //it is provided by// frontend only
    private PointDto dropOffLocation;
    private LocalDateTime createdTime;

    private RiderDto rider; // 1 rider has multiple ride requests

    private DriverDto driver;

    private PaymentMethod paymentMethod;

    private RideStatus rideStatus;

    private String otp;
    private Double fare;
    private LocalDateTime startedAt;
    private LocalDateTime endedAt;
}
