package com.ar.cabBooingApplication.entities;

//import com.ar.cabBooingApplication.dto.PointDto;
import com.ar.cabBooingApplication.entities.enums.PaymentMethod;
import com.ar.cabBooingApplication.entities.enums.RideRequestStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.locationtech.jts.geom.Point;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class RideRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    @Column(columnDefinition = "Geometry(Point,4326)")
    private Point pickupLocation; //it is provided by// frontend only
    @Column(columnDefinition = "Geometry(Point,4326)")
    private Point dropOffLocation;
    @CreationTimestamp
    private LocalDateTime requestedTime;

    @ManyToOne(fetch = FetchType.LAZY)// when we get the Riderequest from the database it doesn't call the rider until unless we call it tider
    private Rider rider; // 1 rider has multiple ride requests

    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

    @Enumerated(EnumType.STRING)
    private RideRequestStatus rideRequestStatus;

    private Double fare;
}
