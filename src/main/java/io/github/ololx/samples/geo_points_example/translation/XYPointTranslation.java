package io.github.ololx.samples.geo_points_example.translation;

import io.github.ololx.moonshine.tuple.Tuple;
import io.github.ololx.moonshine.tuple.Tuple2;

/**
 * @author Alexander A. Kropotin
 *     project geo-points-example
 *     created 11/10/2023 11:42 am
 */
public interface XYPointTranslation<T extends Tuple2<? extends Number, ? extends Number>> extends PointTranslation<T> {}
