package com.sis.onboarding;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.boot.builder.SpringApplicationBuilder;
//import org.springframework.context.ConfigurableApplicationContext;
//import org.springframework.core.env.ConfigurableEnvironment;

@SpringBootApplication
public class SisOnboardingApplication {

	public static void main(String[] args) {
		SpringApplication.run(SisOnboardingApplication.class, args);
		
		System.out.println("Path....."+System.getProperty("user.dir"));
        
	}
}
