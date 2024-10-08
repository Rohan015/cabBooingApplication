package com.ar.cabBooingApplication.dto;

import com.ar.cabBooingApplication.entities.enums.PaymentMethod;
import com.ar.cabBooingApplication.entities.enums.RideRequestStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RideRequestDto {
    public Long id;
    private PointDto pickupLocation; //it is provided by// frontend only
    private PointDto dropOffLocation;
    private LocalDateTime requestedTime;

    private RiderDto rider; // 1 rider has multiple ride requests

    private PaymentMethod paymentMethod;
    private Double fare;

    private RideRequestStatus rideRequestStatus;

}
