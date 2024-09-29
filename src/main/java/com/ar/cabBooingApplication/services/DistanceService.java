package com.ar.cabBooingApplication.services;

import org.locationtech.jts.geom.Point;

public interface DistanceService {
    double caluclateDistance(Point src, Point dest);
}
