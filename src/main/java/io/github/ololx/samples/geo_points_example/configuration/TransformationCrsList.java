package io.github.ololx.samples.geo_points_example.configuration;

import lombok.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Alexander A. Kropotin
 *     project geo-points-example
 *     created 15/10/2023 12:05 pm
 */
@Value
@Component
@ConfigurationProperties(prefix = "transformation")
public class TransformationCrsList {

    List<String> crsProjections;
}
