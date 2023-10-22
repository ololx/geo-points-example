package io.github.ololx.samples.geo_points_example.controller;

import io.github.ololx.moonshine.tuple.Couple;
import io.github.ololx.samples.geo_points_example.configuration.TransformationCrsList;
import io.github.ololx.samples.geo_points_example.model.PointTransformationFromModel;
import io.github.ololx.samples.geo_points_example.transformation.PointTransformationStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * @author Alexander A. Kropotin
 *     project geo-points-example
 *     created 11/10/2023 11:30 am
 */
@RequiredArgsConstructor
@Controller
public class PointTransformationController {

    private final PointTransformationStrategy pointTranslationStrategy;

    @GetMapping({"/", "/index", "/transformation"})
    public CompletableFuture<String> index(Model model) {
        model.addAttribute("pointTransformationModel", PointTransformationFromModel.empty());

        var crsProjections = this.pointTranslationStrategy.getAvailableCrs();
        model.addAttribute("fromCrsOptions", crsProjections);
        model.addAttribute("toCrsOptions", crsProjections);

        return CompletableFuture.completedFuture("transformation");
    }

    @PostMapping("/transformation")
    public CompletableFuture<String> transform(@ModelAttribute PointTransformationFromModel pointTransformationRequest,
                                               Model model) {
        PointTransformationFromModel transformationRequestParams = (PointTransformationFromModel) model.getAttribute(
            "pointTransformationFromModel");

        var crsProjections = this.pointTranslationStrategy.getAvailableCrs();
        model.addAttribute("fromCrsOptions", crsProjections);
        model.addAttribute("toCrsOptions", crsProjections);

        if (transformationRequestParams == null) {
            return CompletableFuture.completedFuture("transformation");
        }

        return CompletableFuture.supplyAsync(() -> {
                var newPointCoords = this.pointTranslationStrategy.transform(
                    Couple.of(transformationRequestParams.getX(), transformationRequestParams.getY()),
                    transformationRequestParams.getFromCrs(),
                    transformationRequestParams.getToCrs()
                );

                model.addAttribute(
                    "pointTransformationModel",
                    new PointTransformationFromModel(
                        transformationRequestParams.getX(),
                        transformationRequestParams.getY(),
                        newPointCoords.getT0(),
                        newPointCoords.getT1(),
                        transformationRequestParams.getFromCrs(),
                        transformationRequestParams.getToCrs()
                    )
                );

                return "transformation";
            })
            .handleAsync(((result, throwable) -> {
                if (throwable != null) {
                    model.addAttribute("pointTransformationModel", PointTransformationFromModel.empty());
                    model.addAttribute("errorMessage", "The specified CRS doesn't exists");
                }

                return result;
            }));
    }
}
