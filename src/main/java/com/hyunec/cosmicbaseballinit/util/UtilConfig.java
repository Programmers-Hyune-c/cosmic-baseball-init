package com.hyunec.cosmicbaseballinit.util;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UtilConfig {

    @Bean
    public RandomGenerator randomGenerator() {
        return new RandomGeneratorImpl();
    }
}
