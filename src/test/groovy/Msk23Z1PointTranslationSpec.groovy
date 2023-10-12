/**
 * @author Alexander A. Kropotin
 * project geo-points-example
 * created 11/10/2023 12:17 pm
 */


import io.github.ololx.moonshine.tuple.Couple
import io.github.ololx.moonshine.tuple.Triple
import io.github.ololx.samples.geo_points_example.translation.PointTranslationStrategy
import spock.lang.Specification

class Msk23Z1PointTranslationSpec extends Specification {

    PointTranslationStrategy translation = new PointTranslationStrategy(
        Couple.of("WGS-84", "+proj=longlat +ellps=WGS84 +datum=WGS84 +no_defs"),
        Map.of(
            "МСК-16 Зона 1",
            "+proj=tmerc +lat_0=0 +lon_0=49.03333333333 +k=1 +x_0=1300000 +y_0=-5709414.70 +ellps=krass +towgs84=23.57,-140.95,-79.8,0,0.35,0.79,-0.22 +units=m +no_defs",
            "МСК-16 Зона 2",
            "+proj=tmerc +lat_0=0 +lon_0=52.03333333333 +k=1 +x_0=2300000 +y_0=-5709414.70 +ellps=krass +towgs84=23.57,-140.95,-79.8,0,0.35,0.79,-0.22 +units=m +no_defs",
            "МСК-16 Зона 3",
            "+proj=tmerc +lat_0=0 +lon_0=55.03333333333 +k=1 +x_0=3300000 +y_0=-5709414.70 +ellps=krass +towgs84=23.57,-140.95,-79.8,0,0.35,0.79,-0.22 +units=m +no_defs",
            "МСК-23 Зона 1",
            "+proj=tmerc +lat_0=0 +lon_0=37.98333333333 +k=1 +x_0=1300000 +y_0=-4511057.628 +ellps=krass +towgs84=23.57,-140.95,-79.8,0,0.35,0.79,-0.22 +units=m +no_defs",
            "МСК-23 Зона 2",
            "+proj=tmerc +lat_0=0 +lon_0=40.98333333333 +k=1 +x_0=2300000 +y_0=-4511057.628 +ellps=krass +towgs84=23.57,-140.95,-79.8,0,0.35,0.79,-0.22 +units=m +no_defs"
        )
    )

    def "should translate point coordinates correctly"() {
        given: "a point with coordinates"
        def point = Triple.of(12 as double, 21 as double, 0 as double)

        when: "the point is translated"
        def translatedPoint = translation.translate(point.convert {Couple.of(it.get(0), it.get(1))}, "МСК-23 Зона 1", "WGS-84")

        then: "the translated point should have expected coordinates"

        println(point)
        println(translatedPoint)

        def translatedPoints = translation.translate(translatedPoint, "WGS-84", "МСК-16 Зона 1")
        def translatedPoints2 = translation.translate(translatedPoints, "МСК-16 Зона 1", "WGS-84")
        def translatedPoints3 = translation.translate(translatedPoints2, "WGS-84", "МСК-23 Зона 1")

        println(translatedPoints)
        println(translatedPoints3)
    }
}
