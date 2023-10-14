package io.github.ololx.samples.geo_points_example
/**
 * @author Alexander A. Kropotin
 * project geo-points-example
 * created 11/10/2023 12" : "17 pm
 */


import io.github.ololx.moonshine.tuple.Couple
import io.github.ololx.samples.geo_points_example.translation.PointTranslationStrategy
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Unroll

class PointTranslationStrategyTest extends Specification {

    @Shared
    PointTranslationStrategy translation = new PointTranslationStrategy(
        [
            "МГС-84:+proj=longlat +ellps=WGS84 +datum=WGS84 +no_defs",
            "WGS-84:+proj=longlat +ellps=WGS84 +datum=WGS84 +no_defs",
            "ПЗ-90:+proj=longlat +a=6378136 +rf=298.257839303 +no_defs",
            "МСК-02 Зона 1:+proj=tmerc +lat_0=0 +lon_0=55.03333333333 +k=1 +x_0=1300000 +y_0=-5409414.70 +ellps=krass +towgs84=23.57,-140.95,-79.8,0,0.35,0.79,-0.22 +units=m +no_defs",
            "МСК-02 Зона 2:+proj=tmerc +lat_0=0 +lon_0=58.03333333333 +k=1 +x_0=2300000 +y_0=-5409414.70 +ellps=krass +towgs84=23.57,-140.95,-79.8,0,0.35,0.79,-0.22 +units=m +no_defs",
            "МСК-16 Зона 1:+proj=tmerc +lat_0=0 +lon_0=49.03333333333 +k=1 +x_0=1300000 +y_0=-5709414.70 +ellps=krass +towgs84=23.57,-140.95,-79.8,0,0.35,0.79,-0.22 +units=m +no_defs",
            "МСК-16 Зона 2:+proj=tmerc +lat_0=0 +lon_0=52.03333333333 +k=1 +x_0=2300000 +y_0=-5709414.70 +ellps=krass +towgs84=23.57,-140.95,-79.8,0,0.35,0.79,-0.22 +units=m +no_defs",
            "МСК-16 Зона 3:+proj=tmerc +lat_0=0 +lon_0=55.03333333333 +k=1 +x_0=3300000 +y_0=-5709414.70 +ellps=krass +towgs84=23.57,-140.95,-79.8,0,0.35,0.79,-0.22 +units=m +no_defs",
            "МСК-23 Зона 1:+proj=tmerc +lat_0=0 +lon_0=37.98333333333 +k=1 +x_0=1300000 +y_0=-4511057.628 +ellps=krass +towgs84=23.57,-140.95,-79.8,0,0.35,0.79,-0.22 +units=m +no_defs",
            "МСК-23 Зона 2:+proj=tmerc +lat_0=0 +lon_0=40.98333333333 +k=1 +x_0=2300000 +y_0=-4511057.628 +ellps=krass +towgs84=23.57,-140.95,-79.8,0,0.35,0.79,-0.22 +units=m +no_defs",
            "МСК-56 Зона 1:+proj=tmerc +lat_0=0 +lon_0=52.03333333333 +k=1 +x_0=1300000 +y_0=-5309414.70 +ellps=krass +towgs84=23.57,-140.95,-79.8,0,0.35,0.79,-0.22 +units=m +no_defs",
            "МСК-56 Зона 2:+proj=tmerc +lat_0=0 +lon_0=55.03333333333 +k=1 +x_0=2300000 +y_0=-5309414.70 +ellps=krass +towgs84=23.57,-140.95,-79.8,0,0.35,0.79,-0.22 +units=m +no_defs",
            "МСК-56 Зона 3:+proj=tmerc +lat_0=0 +lon_0=58.03333333333 +k=1 +x_0=3300000 +y_0=-5309414.70 +ellps=krass +towgs84=23.57,-140.95,-79.8,0,0.35,0.79,-0.22 +units=m +no_defs",
            "МСК-56 Зона 4:+proj=tmerc +lat_0=0 +lon_0=61.03333333333 +k=1 +x_0=4300000 +y_0=-5309414.70 +ellps=krass +towgs84=23.57,-140.95,-79.8,0,0.35,0.79,-0.22 +units=m +no_defs",
            "МСК-59 Зона 1:+proj=tmerc +lat_0=0 +lon_0=53.55 +k=1 +x_0=1250000 +y_0=-5914743.504 +ellps=krass +towgs84=23.57,-140.95,-79.8,0,0.35,0.79,-0.22 +units=m +no_defs",
            "МСК-59 Зона 2:+proj=tmerc +lat_0=0 +lon_0=56.55 +k=1 +x_0=2250000 +y_0=-5914743.504 +ellps=krass +towgs84=23.57,-140.95,-79.8,0,0.35,0.79,-0.22 +units=m +no_defs",
            "МСК-59 Зона 3:+proj=tmerc +lat_0=0 +lon_0=59.55 +k=1 +x_0=3250000 +y_0=-5914743.504 +ellps=krass +towgs84=23.57,-140.95,-79.8,0,0.35,0.79,-0.22 +units=m +no_defs",
            "МСК-63 Зона 1:+proj=tmerc +lat_0=0 +lon_0=49.03333333333 +k=1 +x_0=1300000 +y_0=-5509414.70 +ellps=krass +towgs84=23.57,-140.95,-79.8,0,0.35,0.79,-0.22 +units=m +no_defs",
            "МСК-63 Зона 2:+proj=tmerc +lat_0=0 +lon_0=52.03333333333 +k=1 +x_0=2300000 +y_0=-5509414.70 +ellps=krass +towgs84=23.57,-140.95,-79.8,0,0.35,0.79,-0.22 +units=m +no_defs",
            "МСК-83 Зона 3:+proj=tmerc +lat_0=0 +lon_0=44.03333333333 +k=1 +x_0=3400000 +y_0=-6511057.628 +ellps=krass +towgs84=23.57,-140.95,-79.8,0,0.35,0.79,-0.22 +units=m +no_defs",
            "МСК-83 Зона 4:+proj=tmerc +lat_0=0 +lon_0=50.03333333333 +k=1 +x_0=4400000 +y_0=-6511057.628 +ellps=krass +towgs84=23.57,-140.95,-79.8,0,0.35,0.79,-0.22 +units=m +no_defs",
            "МСК-83 Зона 5:+proj=tmerc +lat_0=0 +lon_0=56.03333333333 +k=1 +x_0=5400000 +y_0=-6511057.628 +ellps=krass +towgs84=23.57,-140.95,-79.8,0,0.35,0.79,-0.22 +units=m +no_defs",
            "МСК-83 Зона 6:+proj=tmerc +lat_0=0 +lon_0=62.03333333333 +k=1 +x_0=6400000 +y_0=-6511057.628 +ellps=krass +towgs84=23.57,-140.95,-79.8,0,0.35,0.79,-0.22 +units=m +no_defs",
            "МСК-83 Зона 7:+proj=tmerc +lat_0=0 +lon_0=68.03333333333 +k=1 +x_0=7400000 +y_0=-6511057.628 +ellps=krass +towgs84=23.57,-140.95,-79.8,0,0.35,0.79,-0.22 +units=m +no_defs",
            "СК-42:+proj=tmerc +lat_0=0 +lon_0=39 +k=1 +x_0=7500000 +y_0=0 +ellps=krass +towgs84=23.57,-140.95,-79.8,0,-0.35,-0.79,-0.22 +units=m +no_defs",
            "СК-95:+proj=tmerc +lat_0=0 +lon_0=21 +k=1 +x_0=4500000 +y_0=0 +ellps=krass +towgs84=24.47,-130.89,-81.56,0,0,-0.13,-0.22 +units=m +no_defs"
        ]
    )

    @Unroll
    def "transform() - when transform <#x, #y> form #fromCrs to #toCrs then return <#expectedX, #expectedY>"() {
        given: "a point with coordinates"
        def point = Couple.of(x as double, y as double)

        when: "the point is translated"
        def translatedPoint = translation.transform(point, fromCrs, toCrs)

        then: "the translated point should have expected coordinates"
        println(translatedPoint)
        translatedPoint.getT0().round() == (expectedX as double).round()
        translatedPoint.getT1().round() == (expectedY as double).round()

        where:
        x  | y  | fromCrs         | toCrs    | expectedX     | expectedY
        21 | 22 | "МГС-84"        | "WGS-84" | 21            | 22
        21 | 22 | "WGS-84"        | "WGS-84" | 21            | 22
        21 | 22 | "ПЗ-90"         | "WGS-84" | 21            | 22
        21 | 22 | "МСК-02 Зона 1" | "WGS-84" | 37.7511284786 | 47.4984841581
        21 | 22 | "МСК-02 Зона 2" | "WGS-84" | 28.7970436799 | 44.9209500740
        21 | 22 | "МСК-16 Зона 1" | "WGS-84" | 30.8159142729 | 50.0677396389
        21 | 22 | "МСК-16 Зона 2" | "WGS-84" | 21.4767578043 | 47.2692936319
        21 | 22 | "МСК-16 Зона 3" | "WGS-84" | 14.5132749924 | 43.5754481550
        21 | 22 | "МСК-23 Зона 1" | "WGS-84" | 22.8499330107 | 39.7326321670
        21 | 22 | "МСК-23 Зона 2" | "WGS-84" | 15.0708938718 | 37.7497049945
        21 | 22 | "МСК-56 Зона 1" | "WGS-84" | 35.0353296176 | 46.6389371289
        21 | 22 | "МСК-56 Зона 2" | "WGS-84" | 26.2317488745 | 44.1345851311
        21 | 22 | "МСК-56 Зона 3" | "WGS-84" | 18.6881910661 | 40.6629052689
        21 | 22 | "МСК-56 Зона 4" | "WGS-84" | 15.5236798202 | 37.2445209614
        21 | 22 | "МСК-59 Зона 1" | "WGS-84" | 35.2978103272 | 51.9300224492
        21 | 22 | "МСК-59 Зона 2" | "WGS-84" | 25.5223392843 | 49.0270555454
        21 | 22 | "МСК-59 Зона 3" | "WGS-84" | 18.4452613070 | 45.1883183282
        21 | 22 | "МСК-63 Зона 1" | "WGS-84" | 31.4533046271 | 48.3564023594
        21 | 22 | "МСК-63 Зона 2" | "WGS-84" | 22.3430718193 | 45.7035826338
        21 | 22 | "МСК-83 Зона 3" | "WGS-84" | -0.1353713831 | 48.6831238689
        21 | 22 | "МСК-83 Зона 4" | "WGS-84" | 17.4521986231 | 47.6123893877
        21 | 22 | "МСК-83 Зона 5" | "WGS-84" | 105.814033644 | 65.0382724938
    }

    @Unroll
    def "transform() - when transform <#x, #y> to itself then return <#x, #y>"() {
        given: "a point with coordinates"
        def point = Couple.of(x as double, y as double)

        when: "the point is translated"
        def translatedPoint = translation.transform(point, fromCrs, fromCrs)

        then: "the translated point should have expected coordinates"
        println(translatedPoint)
        translatedPoint.getT0().round() == (x as double).round()
        translatedPoint.getT1().round() == (y as double).round()

        where:
        x  | y  | fromCrs
        21 | 22 | "МГС-84"
        21 | 22 | "WGS-84"
        21 | 22 | "ПЗ-90"
        21 | 22 | "МСК-02 Зона 1"
        21 | 22 | "МСК-02 Зона 2"
        21 | 22 | "МСК-16 Зона 1"
        21 | 22 | "МСК-16 Зона 2"
        21 | 22 | "МСК-16 Зона 3"
        21 | 22 | "МСК-23 Зона 1"
        21 | 22 | "МСК-23 Зона 2"
        21 | 22 | "МСК-56 Зона 1"
        21 | 22 | "МСК-56 Зона 2"
        21 | 22 | "МСК-56 Зона 3"
        21 | 22 | "МСК-56 Зона 4"
        21 | 22 | "МСК-59 Зона 1"
        21 | 22 | "МСК-59 Зона 2"
        21 | 22 | "МСК-59 Зона 3"
        21 | 22 | "МСК-63 Зона 1"
        21 | 22 | "МСК-63 Зона 2"
        21 | 22 | "МСК-83 Зона 3"
        21 | 22 | "МСК-83 Зона 4"
        21 | 22 | "МСК-83 Зона 5"
    }
}