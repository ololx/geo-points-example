package io.github.ololx.samples.geo_points_example.configuration;

import io.github.ololx.moonshine.tuple.Couple;
import io.github.ololx.samples.geo_points_example.translation.PointTranslationStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Alexander A. Kropotin
 *     project geo-points-example
 *     created 12/10/2023 12:30 pm
 */
@Configuration
public class PointTranslationStrategyConfiguration {

    private final String generalProj4jText;

    private final List<String> othersProj4jText;

    @Autowired
    public PointTranslationStrategyConfiguration(@NonNull @Value("${translation.general}") String generalProj4jText,
                                                 @NonNull @Value("${others}") List<String> othersProj4jText) {
        this.generalProj4jText = generalProj4jText;
        this.othersProj4jText = othersProj4jText;
    }

    @Bean
    public PointTranslationStrategy pointTranslationStrategy() {
        var generalProj4jTextArray = this.generalProj4jText.split(":");
        Map<String, String> othersProj4jTextMap = new HashMap<>();
        this.othersProj4jText.forEach(proj4jText -> {
            var proj4jTextArray = proj4jText.split(":");
            othersProj4jTextMap.put(proj4jTextArray[0], proj4jTextArray[1]);
        });

        return new PointTranslationStrategy(
            Couple.of(generalProj4jTextArray[0], generalProj4jTextArray[1]),
            othersProj4jTextMap
        );
    }
}
