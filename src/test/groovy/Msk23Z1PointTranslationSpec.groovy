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

    PointTranslationStrategy translation = new PointTranslationStrategy()

    def "should translate point coordinates correctly"() {
        given: "a point with coordinates"
        def point = Triple.of(12 as double, 21 as double, 0 as double)

        when: "the point is translated"
        def translatedPoint = translation.translate(point.convert {Couple.of(it.get(0), it.get(1))}, "МСК-23 Зона 1/WGS-84")

        then: "the translated point should have expected coordinates"

        println(point)
        println(translatedPoint)

        def translatedPoints = translation.translate(translatedPoint, "WGS-84/МСК-23 Зона 1")

        println(translatedPoints)
    }
}
