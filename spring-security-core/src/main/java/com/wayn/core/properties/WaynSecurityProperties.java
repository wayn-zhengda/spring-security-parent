package com.wayn.core.properties;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "wayn.security")
public class WaynSecurityProperties {

    private BrowserProperties browser = new BrowserProperties();
}
