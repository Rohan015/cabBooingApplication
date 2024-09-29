package com.ar.cabBooingApplication.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PointDto {
    private double[] coordinates;
    private String type = "Point"; //point to geospatial we make it hardcord but it can be 2d, 3d also

    public PointDto(double[] coordinates) {
        this.coordinates = coordinates;
    }
}
