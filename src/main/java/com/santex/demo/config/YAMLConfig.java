package com.santex.demo.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "application")
@Getter
@Setter
public class YAMLConfig {
    private String footballapikey;
    private String competitionsurl;
    private String teamsurl;
}
