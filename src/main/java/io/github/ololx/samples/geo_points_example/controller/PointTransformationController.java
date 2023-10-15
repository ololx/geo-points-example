package io.github.ololx.samples.geo_points_example.controller;

import io.github.ololx.moonshine.tuple.Couple;
import io.github.ololx.samples.geo_points_example.model.PointTransformationFromModel;
import io.github.ololx.samples.geo_points_example.translation.PointTransformationStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author Alexander A. Kropotin
 * project geo-points-example
 * created 11/10/2023 11:30 am
 */
@RequiredArgsConstructor
@Controller
public class PointTransformationController {

    private final PointTransformationStrategy pointTranslationStrategy;

    @GetMapping({"/", "/index", "/transformation"})
    public String index(Model model) {
        model.addAttribute("pointTransformationRequest", PointTransformationFromModel.empty());

        return "transformation";
    }

    @PostMapping("/transformation")
    public String transform(@ModelAttribute PointTransformationFromModel pointTransformationRequest, Model model) {
        PointTransformationFromModel transformationRequesrParams = (PointTransformationFromModel) model.getAttribute("pointTransformationFromModel");

        var newPointCoords = this.pointTranslationStrategy.transform(
            Couple.of(transformationRequesrParams.getX(), transformationRequesrParams.getY()),
            transformationRequesrParams.getFromCrs(),
            transformationRequesrParams.getToCrs()
        );

        model.addAttribute(
            "pointTransformationRequest",
            new PointTransformationFromModel(
                transformationRequesrParams.getX(),
                transformationRequesrParams.getY(),
                transformationRequesrParams.getZ(),
                newPointCoords.getT0(),
                newPointCoords.getT1(),
                transformationRequesrParams.getZ(),
                transformationRequesrParams.getFromCrs(),
                transformationRequesrParams.getToCrs()
            )
        );

        return "transformation";
    }
}
