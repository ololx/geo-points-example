package io.github.ololx.samples.geo_points_example.translation;

import io.github.ololx.moonshine.tuple.Couple;
import io.github.ololx.moonshine.tuple.Tuple2;
import org.locationtech.proj4j.ProjCoordinate;

/**
 * @author Alexander A. Kropotin
 *     project geo-points-example
 *     created 11/10/2023 11:42 am
 */
public class XYProjPointTranslation extends AbstractProjPointTranslation<Tuple2<Double, Double>> implements XYPointTranslation<Tuple2<Double, Double>> {

    public XYProjPointTranslation(String fromProj4jText, String toProj4jText) {
        super(fromProj4jText, toProj4jText);
    }

    @Override
    public Tuple2<Double, Double> apply(final Tuple2<Double, Double> point) {
        ProjCoordinate sourcePoint = new ProjCoordinate(point.getT0(), point.getT1());
        ProjCoordinate targetPoint = new ProjCoordinate();
        this.transform.transform(sourcePoint, targetPoint);

        return Couple.of(targetPoint.x, targetPoint.y);
    }
}
