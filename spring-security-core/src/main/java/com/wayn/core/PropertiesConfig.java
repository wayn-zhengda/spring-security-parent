package com.wayn.core;

import com.wayn.core.properties.WaynSecurityProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(WaynSecurityProperties.class)
public class PropertiesConfig {
}
