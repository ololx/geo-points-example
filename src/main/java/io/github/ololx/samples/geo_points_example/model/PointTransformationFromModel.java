package io.github.ololx.samples.geo_points_example.model;

import lombok.Value;

/**
 * @author Alexander A. Kropotin
 * project geo-points-example
 * created 15/10/2023 2:24 pm
 */
@Value
public class PointTransformationFromModel {

    double x;

    double y;

    double z;

    double newX;

    double newY;

    double newZ;

    String fromCrs;

    String toCrs;

    public static PointTransformationFromModel empty() {
        return new PointTransformationFromModel(0, 0, 0, 0, 0, 0, "МГС-84", "WGS-84");
    }
}
