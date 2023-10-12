package io.github.ololx.samples.geo_points_example.translation;

import io.github.ololx.moonshine.stopwatch.SimpleStopwatch;
import io.github.ololx.moonshine.tuple.Couple;
import io.github.ololx.moonshine.tuple.Tuple2;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

/**
 * @author Alexander A. Kropotin
 *     project geo-points-example
 *     created 11/10/2023 11:42 am
 */
@Slf4j
public class PointTranslationStrategy {

    private static final BiFunction<String, String, String> transformNameFactory = (from, to) -> from + "/" + to;

    private final Tuple2<String, String> generalProj4jText;

    private final Map<String, XYPointTranslation<Tuple2<Double, Double>>> transforms = new HashMap<>();

    public PointTranslationStrategy(Tuple2<String, String> generalProj4jText, Map<String, String> othersProj4jText) {
        this.generalProj4jText = generalProj4jText;

        othersProj4jText.forEach((name, value) -> {
            this.transforms.put(
                transformNameFactory.apply(generalProj4jText.getT0(), name),
                new XYProjPointTranslation(generalProj4jText.getT1(), value)
            );
            this.transforms.put(
                transformNameFactory.apply(name, generalProj4jText.getT0()),
                new XYProjPointTranslation(value, generalProj4jText.getT1())
            );
        });

        this.transforms.put(
            transformNameFactory.apply(generalProj4jText.getT0(), generalProj4jText.getT0()),
            new XYProjPointTranslation(generalProj4jText.getT1(), generalProj4jText.getT1())
        );
    }

    public Tuple2<Double, Double> translate(final Couple<Double, Double> point,
                                            final String fromCrs,
                                            final String toCrs) {
        var stopwatch = new SimpleStopwatch().start();
        Tuple2<Double, Double> translated = point.convert(p -> Couple.of(p.get(0), p.get(1)));

        if (!fromCrs.equalsIgnoreCase(toCrs)) {
            translated = this.transforms.get(transformNameFactory.apply(fromCrs, generalProj4jText.getT0()))
                .apply(translated);
            translated = this.transforms.get(transformNameFactory.apply(generalProj4jText.getT0(), toCrs))
                .apply(translated);
        }

        log.info("Complete transformation from {} to {}. Elapsed time = {}", fromCrs, toCrs, stopwatch.elapsed());

        return translated;
    }
}
