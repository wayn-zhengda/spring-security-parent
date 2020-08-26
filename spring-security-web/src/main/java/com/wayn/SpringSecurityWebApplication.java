package com.wayn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class SpringSecurityWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityWebApplication.class, args);
	}

}
