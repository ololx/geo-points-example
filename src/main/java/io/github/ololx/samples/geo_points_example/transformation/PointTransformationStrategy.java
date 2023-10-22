package io.github.ololx.samples.geo_points_example.transformation;

import io.github.ololx.moonshine.stopwatch.SimpleStopwatch;
import io.github.ololx.moonshine.tuple.Couple;
import io.github.ololx.moonshine.tuple.Tuple2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.BiFunction;

/**
 * @author Alexander A. Kropotin
 *     project geo-points-example
 *     created 11/10/2023 11:42 am
 */
@Slf4j
public class PointTransformationStrategy {

    private static final BiFunction<String, String, String> transformNameFactory = (from, to) -> from + "/" + to;

    private final Map<String, XYPointTransformation<Tuple2<Double, Double>>> transforms = new HashMap<>();

    private final Set<String> crs = new HashSet<>();

    public PointTransformationStrategy(@NonNull List<String> crsProjectionsProj4jText) {
       Map<String, String> crsProjectionsProj4jTextMap = new HashMap<>();
        crsProjectionsProj4jText.forEach(proj4jText -> {
            var proj4jTextArray = proj4jText.split(":");
            crsProjectionsProj4jTextMap.put(proj4jTextArray[0], proj4jTextArray[1]);
        });

        crsProjectionsProj4jTextMap.forEach((name, value) -> {
            crsProjectionsProj4jTextMap.forEach((nameOther, valueOther) -> {
                this.transforms.put(
                    transformNameFactory.apply(name, nameOther),
                    new XYProjPointTransformation(value, valueOther)
                );
            });

            crs.add(name);
        });
    }

    public Tuple2<Double, Double> transform(final Couple<Double, Double> point,
                                            final String fromCrs,
                                            final String toCrs) {
        var stopwatch = new SimpleStopwatch().start();
        Tuple2<Double, Double> translated = point.convert(p -> Couple.of(p.get(0), p.get(1)));

        if (!fromCrs.equalsIgnoreCase(toCrs)) {
            translated = this.transforms.get(transformNameFactory.apply(fromCrs, toCrs))
                .apply(translated);
        }

        log.info("Complete transformation from {} to {}. Elapsed time = {}", fromCrs, toCrs, stopwatch.elapsed());

        return translated;
    }

    public Set<String> getAvailableCrs() {
        return this.crs;
    }
}
