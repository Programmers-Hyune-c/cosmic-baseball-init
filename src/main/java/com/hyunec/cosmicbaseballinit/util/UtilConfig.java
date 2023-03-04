package com.hyunec.cosmicbaseballinit.util;

import java.util.Random;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UtilConfig {
    @Bean
    public Random random() {
        return new Random();
    }
}
