package io.github.ololx.samples.geo_points_example.controller;

import io.github.ololx.moonshine.tuple.Couple;
import io.github.ololx.samples.geo_points_example.model.PointTransformationFromModel;
import io.github.ololx.samples.geo_points_example.translation.PointTransformationStrategy;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.threads.VirtualThreadExecutor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;

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

        return CompletableFuture.completedFuture("transformation");
    }

    @PostMapping("/transformation")
    public CompletableFuture<String> transform(@ModelAttribute PointTransformationFromModel pointTransformationRequest,
                                               Model model) {
        PointTransformationFromModel transformationRequestParams = (PointTransformationFromModel) model.getAttribute(
            "pointTransformationFromModel");

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
                        transformationRequestParams.getZ(),
                        newPointCoords.getT0(),
                        newPointCoords.getT1(),
                        transformationRequestParams.getZ(),
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
