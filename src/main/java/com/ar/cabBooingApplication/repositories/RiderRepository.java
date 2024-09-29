package com.ar.cabBooingApplication.repositories;

import com.ar.cabBooingApplication.entities.Ride;
import com.ar.cabBooingApplication.entities.Rider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RiderRepository extends JpaRepository<Rider,Long> {
}
