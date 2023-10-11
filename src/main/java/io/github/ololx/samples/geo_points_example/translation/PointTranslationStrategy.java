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

    private static final String WGS84 = "+proj=longlat +ellps=WGS84 +datum=WGS84 +no_defs";

    private static final Map<String, String> proj4jText = Map.of(
        "МСК-16 Зона 1", "+proj=tmerc +lat_0=0 +lon_0=49.03333333333 +k=1 +x_0=1300000 +y_0=-5709414.70 +ellps=krass +towgs84=23.57,-140.95,-79.8,0,0.35,0.79,-0.22 +units=m +no_defs",
        "МСК-16 Зона 2", "+proj=tmerc +lat_0=0 +lon_0=52.03333333333 +k=1 +x_0=2300000 +y_0=-5709414.70 +ellps=krass +towgs84=23.57,-140.95,-79.8,0,0.35,0.79,-0.22 +units=m +no_defs",
        "МСК-16 Зона 3", "+proj=tmerc +lat_0=0 +lon_0=55.03333333333 +k=1 +x_0=3300000 +y_0=-5709414.70 +ellps=krass +towgs84=23.57,-140.95,-79.8,0,0.35,0.79,-0.22 +units=m +no_defs",
        "МСК-23 Зона 1", "+proj=tmerc +lat_0=0 +lon_0=37.98333333333 +k=1 +x_0=1300000 +y_0=-4511057.628 +ellps=krass +towgs84=23.57,-140.95,-79.8,0,0.35,0.79,-0.22 +units=m +no_defs",
        "МСК-23 Зона 2", "+proj=tmerc +lat_0=0 +lon_0=40.98333333333 +k=1 +x_0=2300000 +y_0=-4511057.628 +ellps=krass +towgs84=23.57,-140.95,-79.8,0,0.35,0.79,-0.22 +units=m +no_defs"
    );

    private static final Map<String, XYPointTranslation<Tuple2<Double, Double>>> transforms = new HashMap<>();

    static {
        proj4jText.forEach((name, value) -> {
            transforms.put("WGS-84" + "/" + name, new XYProjPointTranslation(WGS84, value));
            transforms.put(name + "/" + "WGS-84", new XYProjPointTranslation(value, WGS84));
        });

        transforms.put("WGS-84/WGS-84", new XYProjPointTranslation(WGS84, WGS84));
    }

    public Tuple2<Double, Double> translate(Couple<Double, Double> point, String crs) {
        var crss = crs.split("/");
        return transforms.get("WGS-84/" + crss[1]).apply(transforms.get(crss[0] + "/WGS-84").apply(point));
    }
}
