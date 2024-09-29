package com.ar.cabBooingApplication.services.impl;

import com.ar.cabBooingApplication.services.DistanceService;
import lombok.Data;
import org.locationtech.jts.geom.Point;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
public class DistanceServiceOSRMImpl implements DistanceService {

    private static  final String OSRM_API= "http://router.project-osrm.org/route/v1/driving/";


    @Override
    public double caluclateDistance(Point src, Point dest) {
        // call the third party api call the OSRM
        String uri = src.getX()+","+src.getY()+";"+dest.getX()+","+dest.getY();
        try {
            OSRMResponseDto responseDto = RestClient.builder()
                    .baseUrl(OSRM_API)
                    .build().get()
                    .uri(uri)
                    .retrieve().body(OSRMResponseDto.class);
            return responseDto.getRoutes().get(0).getDistance() / 1000.0;
        }catch (Exception e){
            throw new RuntimeException("Error getting data from OSRM "+e.getMessage());
        }
    }
}
@Data
class OSRMResponseDto{
    private List<OSRMRoute> routes;
}
@Data
class OSRMRoute{
    private Double distance;
}
