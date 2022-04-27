package com.planet.develop;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableBatchProcessing
@SpringBootApplication
public class DevelopApplication {

	public static void main(String[] args) {

		SpringApplication.run(DevelopApplication.class, args);
	}


}
