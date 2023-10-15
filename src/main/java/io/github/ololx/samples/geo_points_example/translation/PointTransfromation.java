package io.github.ololx.samples.geo_points_example.translation;

import io.github.ololx.moonshine.tuple.Tuple;

/**
 * @author Alexander A. Kropotin
 *     project geo-points-example
 *     created 11/10/2023 11:42 am
 */
public interface PointTransfromation<T extends Tuple> {

    T apply(T point);
}
