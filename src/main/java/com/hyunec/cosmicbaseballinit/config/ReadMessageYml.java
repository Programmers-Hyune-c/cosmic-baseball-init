package com.hyunec.cosmicbaseballinit.config;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.ConstructorBinding;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;


@PropertySource(value = "classpath:message.yml", factory = YamlPropertySourceFactory.class)
@ConfigurationProperties(prefix = "game")
@Component
@Getter
@Setter
public class ReadMessageYml {
    private String settingFirst;
    private String scoreInit;
}
