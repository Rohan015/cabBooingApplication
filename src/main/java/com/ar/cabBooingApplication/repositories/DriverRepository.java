package com.ar.cabBooingApplication.repositories;

import com.ar.cabBooingApplication.entities.Driver;
import org.locationtech.jts.geom.Point;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DriverRepository extends JpaRepository<Driver,Long> {
    @Query(value = "SELECT d.*, ST_Distance(d.current_location,:pickupLocation) AS distance " +
            "FROM driver d " +
            "WHERE d.available = true AND ST_DWithin(d.current_location, :pickupLocation, 10000) " +
            "ORDER BY distance " +
            "LIMIT 10",nativeQuery = true)
//@Query(value = "SELECT * FROM driver",nativeQuery = true )
    List<Driver> findMatchingDriver(Point pickupLocation);

    @Query(value = "SELECT d.* " +
            "FROM driver d " +
            "WHERE d.available = true AND ST_DWithin(d.current_location, :pickupLocation, 15000) " +
            "ORDER BY d.rating DESC " +
            "LIMIT 10",nativeQuery = true )

    List<Driver> findTenNearbyTopRatedDrivers(Point pickupLocation);
}
