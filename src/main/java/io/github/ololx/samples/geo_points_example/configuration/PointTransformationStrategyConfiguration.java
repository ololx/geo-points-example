package io.github.ololx.samples.geo_points_example.configuration;

import io.github.ololx.samples.geo_points_example.transformation.PointTransformationStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;

import java.util.List;

/**
 * @author Alexander A. Kropotin
 *     project geo-points-example
 *     created 15/10/2023 11:47 am
 */
@Configuration
public class PointTransformationStrategyConfiguration {

    private final List<String> crsProjectionsProj4jText;

    @Autowired
    public PointTransformationStrategyConfiguration(@NonNull TransformationCrsList crsProjectionsProj4jText) {
        this.crsProjectionsProj4jText = crsProjectionsProj4jText.getCrsProjections();
    }

    @Bean
    public PointTransformationStrategy pointTransformationStrategy() {
        return new PointTransformationStrategy(this.crsProjectionsProj4jText);
    }
}
