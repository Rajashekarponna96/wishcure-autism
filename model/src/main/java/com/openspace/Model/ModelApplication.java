package com.openspace.Model;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EntityScan(basePackages={"com.openspace.Model.*"})
@ComponentScan(basePackages={"com.openspace.Model.*"})
public class ModelApplication {
	
	
	public static void main(String[] args) {
		SpringApplication.run(ModelApplication.class, args);
	}
}
