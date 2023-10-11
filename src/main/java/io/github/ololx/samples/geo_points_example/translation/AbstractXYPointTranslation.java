package io.github.ololx.samples.geo_points_example.translation;

import io.github.ololx.moonshine.tuple.Couple;
import io.github.ololx.moonshine.tuple.Tuple;
import org.locationtech.proj4j.CRSFactory;
import org.locationtech.proj4j.CoordinateReferenceSystem;
import org.locationtech.proj4j.CoordinateTransform;
import org.locationtech.proj4j.CoordinateTransformFactory;

import java.util.Objects;

/**
 * @author Alexander A. Kropotin
 *     project geo-points-example
 *     created 11/10/2023 11:42 am
 */
public abstract class AbstractXYPointTranslation<T extends Tuple> implements PointTranslation<T> {

    protected static final CRSFactory crsFactory = new CRSFactory();
    
    protected static final CoordinateTransformFactory ctFactory = new CoordinateTransformFactory();

    protected final CoordinateReferenceSystem fromCrs;

    protected final CoordinateReferenceSystem toCrs;

    protected final CoordinateTransform transform;

    AbstractXYPointTranslation(String defaultProj4jText, String convertibleProj4jText) {
        this.fromCrs = crsFactory.createFromParameters("default-crs", Objects.requireNonNull(defaultProj4jText));
        this.toCrs = crsFactory.createFromParameters("convertible-crs", Objects.requireNonNull(convertibleProj4jText));
        this.transform = ctFactory.createTransform(fromCrs, toCrs);
    }
}
