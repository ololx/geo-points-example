package io.github.ololx.samples.geo_points_example.translation;

import io.github.ololx.moonshine.tuple.Couple;
import io.github.ololx.moonshine.tuple.Tuple2;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author Alexander A. Kropotin
 *     project geo-points-example
 *     created 11/10/2023 11:42 am
 */
public class PointTranslationStrategy {

    private static final Map<String, XYPointTranslation> transforms = new HashMap<>();

    static {
        transforms.put(
            "WGS-84/МСК-23-1",
            new XYPointTranslation(
                "+proj=longlat +ellps=WGS84 +datum=WGS84 +no_defs",
                "+proj=tmerc +lat_0=0 +lon_0=37.98333333333 +k=1 +x_0=1300000 +y_0=-4511057.628 +ellps=krass +towgs84=23.57,-140.95,-79.8,0,0.35,0.79,-0.22 +units=m +no_defs"
            )
        );
        transforms.put(
            "МСК-23-1/WGS-84",
            new XYPointTranslation(
                "+proj=tmerc +lat_0=0 +lon_0=37.98333333333 +k=1 +x_0=1300000 +y_0=-4511057.628 +ellps=krass +towgs84=23.57,-140.95,-79.8,0,0.35,0.79,-0.22 +units=m +no_defs",
                "+proj=longlat +ellps=WGS84 +datum=WGS84 +no_defs"
            )
        );
    }
    public Tuple2<Double, Double> translate(Couple<Double, Double> point, String crs) {
        return Objects.requireNonNull(transforms.get(crs)).apply(point);
    }
}
