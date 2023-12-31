package io.github.ololx.samples.geo_points_example.transformation;

import io.github.ololx.moonshine.tuple.Tuple;
import org.locationtech.proj4j.CRSFactory;
import org.locationtech.proj4j.CoordinateReferenceSystem;
import org.locationtech.proj4j.CoordinateTransform;
import org.locationtech.proj4j.CoordinateTransformFactory;
import org.springframework.lang.NonNull;

/**
 * @author Alexander A. Kropotin
 *     project geo-points-example
 *     created 11/10/2023 11:42 am
 */
public abstract class AbstractProjPointTransformation<T extends Tuple> implements PointTransfromation<T> {

    protected static final CRSFactory crsFactory = new CRSFactory();

    protected static final CoordinateTransformFactory ctFactory = new CoordinateTransformFactory();

    protected final CoordinateReferenceSystem fromCrs;

    protected final CoordinateReferenceSystem toCrs;

    protected final CoordinateTransform transform;

    AbstractProjPointTransformation(@NonNull String fromProj4jText, @NonNull String toProj4jText) {
        this.fromCrs = crsFactory.createFromParameters("from-crs", fromProj4jText);
        this.toCrs = crsFactory.createFromParameters("to-crs", toProj4jText);
        this.transform = ctFactory.createTransform(fromCrs, toCrs);
    }
}
