package io.github.ololx.samples.geo_points_example.controller;

import io.github.ololx.moonshine.tuple.Couple;
import io.github.ololx.moonshine.tuple.Triple;
import io.github.ololx.samples.geo_points_example.translation.PointTranslationStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Alexander A. Kropotin
 *     project geo-points-example
 *     created 11/10/2023 11:30 am
 */
@RequiredArgsConstructor
@RestController("translate")
public class PointTranslationController {

    private final PointTranslationStrategy pointTranslationStrategy;

    private final List<TranslationResponse> responseHistory = new ArrayList<>();

    @GetMapping
    public List<TranslationResponse> translate(@RequestParam("x") double x,
                                               @RequestParam("y") double y,
                                               @RequestParam("z") double z,
                                               @RequestParam("from") String fromCrs,
                                               @RequestParam("to") String toCrs) {
        var response = new TranslationResponse(
            fromCrs,
            toCrs,
            Triple.of(x, y, z),
            pointTranslationStrategy.transform(Couple.of(x, y), fromCrs, toCrs)
                .convert(xy -> Triple.of(xy.get(0), xy.get(1), z))
        );
        responseHistory.add(response);

        return responseHistory;
    }

    static class TranslationResponse {

        public final String from;

        public final String to;

        public final Triple<Double, Double, Double> before;

        public final Triple<Double, Double, Double> after;

        public TranslationResponse(String from,
                                   String to,
                                   Triple<Double, Double, Double> before,
                                   Triple<Double, Double, Double> after) {
            this.from = from;
            this.to = to;
            this.before = before;
            this.after = after;
        }
    }
}
